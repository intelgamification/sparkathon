package com.gamification.streaming;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import kafka.serializer.StringDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;

import com.databricks.apps.logs.Functions;
import com.gamification.domain.UserData;
import com.gamification.jdbc.DBUtils;
import com.gamification.jdbc.JdbcConnection;

public class SparkStreamingUserDataAnalyzer {

	private static final Log LOGGER = LogFactory.getLog(SparkStreamingUserDataAnalyzer.class);

	// Stats will be computed for the last window length of time.
	private static final Duration WINDOW_LENGTH = new Duration(30 * 1000);

	// Stats will be computed every slide interval time.
	private static final Duration SLIDE_INTERVAL = new Duration(10 * 1000);

	private static final String UPDATE_AHT_QUERY = "UPDATE AHT_STREAM_DATA SET MINIMUM_AHT = ?, AGENT_NAME = ?,  ACHIEVED_DATE = ? WHERE ID = ?";
	
	public static void main(String[] args) {
		final String appName = "Spark Streaming User Data Analyzer";
		
		SparkConf sc = new SparkConf();
		sc.setAppName(appName).setMaster("local[*]").set("spark.executor.memory", "1g");
		
		JavaSparkContext jsc = new JavaSparkContext(sc);
		
		JavaStreamingContext jssc = new JavaStreamingContext(jsc, SLIDE_INTERVAL);
		
		String zkQuorum = "172.30.16.178:2181";
		String group = "spark-streaming-sample-groupid";//+new Date().getTime();
		String strTopics = "spark-streaming-sample-topic,test-topic1,test-topic2";
		int numThreads = 2;
		
		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		
		Set<String> topicsSet = new HashSet<>(Arrays.asList(strTopics.split(",")));
		
        String[] topics = strTopics.split(",");
        for (String topic: topics) {
            topicMap.put(topic, numThreads);
        }
        
        //JavaPairReceiverInputDStream<String, String> userDataStream = KafkaUtils.createStream(jssc, zkQuorum, group, topicMap);
        String brokers = "localhost:9092";
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", brokers);

		// Create direct kafka stream with brokers and topics
		JavaPairInputDStream<String, String> userDataStream = KafkaUtils.createDirectStream(
			jssc,
			String.class,
			String.class,
			StringDecoder.class,
			StringDecoder.class,
			kafkaParams,
			topicsSet
		);
        
        
        JavaDStream<UserData> userDStream = userDataStream.map(
                new Function<Tuple2<String, String>, UserData>() {
                    public UserData call(Tuple2<String, String> message) {
                        String strLogMsg = message._2();
                        LOGGER.debug("strLogMsg: "+ strLogMsg);
                        return UserData.parseFromStream(strLogMsg);
                    }
                }
            );
        
        userDStream.print();
        
        JavaDStream<UserData> windowDStream = userDStream.window(WINDOW_LENGTH, SLIDE_INTERVAL);
        
        windowDStream.foreachRDD(new Function<JavaRDD<UserData>, Void>(){

			@Override
			public Void call(JavaRDD<UserData> userData) throws Exception {
				if(userData.count() == 0){
					LOGGER.debug("No user data in this time interval...");
					return null;
				}
				
				JavaRDD<UserData> processedUserData = userData.map(Functions.GET_HANDLING_TIME).cache();
				UserData userDataWithMinAht = processedUserData.min(Functions.USER_DATA_NATURAL_ORDER_COMPARATOR);
				
				LOGGER.debug("minimum value of AHT in the window interval (30 seconds) is : "
						+ userDataWithMinAht.getAht()
						+ " achieved by "
						+ userDataWithMinAht.getFirstName()
						+ " "
						+ userDataWithMinAht.getLastName()
						+ " on "
						+ userDataWithMinAht.getMetricDate());
				
				System.out
						.println("minimum value of AHT in the window interval (30 seconds) is : "
								+ userDataWithMinAht.getAht()
								+ " achieved by "
								+ userDataWithMinAht.getFirstName()
								+ " "
								+ userDataWithMinAht.getLastName()
								+ " on "
								+ userDataWithMinAht.getMetricDate());
				
				int count = DBUtils.updateDB(UPDATE_AHT_QUERY, ""
						+ userDataWithMinAht.getAht(),
						userDataWithMinAht.getFirstName() + " "
								+ userDataWithMinAht.getLastName(), ""
								+ userDataWithMinAht.getMetricDate(), "minAht");
				
				if(count > 0)
					System.out.println("AHT Record Updated Successfully...");
				else
					System.out.println("No records updated...");
				
				return null;
			}
        	
        });
        
        jssc.start();
        jssc.awaitTermination();
	}


}
