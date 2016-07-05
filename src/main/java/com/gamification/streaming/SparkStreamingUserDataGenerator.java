package com.gamification.streaming;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gamification.jdbc.JdbcConnection;

public class SparkStreamingUserDataGenerator {

	private static final Log LOGGER = LogFactory.getLog(SparkStreamingUserDataGenerator.class);
	//private static final String QUERY = "select pu. ud.* from USER_DATA ud, PLATFORM_USER pu where pu.entityId = ud.id";
	private static final String QUERY = "select pu.FIRST_NAME, pu.LAST_NAME, ud.* from user_data ud, platform_user pu where pu.ENTITY_ID = to_char(ud.user_id) order by ud.metric_date";
	
	private static final String DELIMITER = " ";
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Invalid arguments passed. Usage: SparkStreamingKafkaLogGenerator spark-streaming-sample-groupid spark-streaming-sample-topic 50 1000");
			System.exit(-1);
		}
		//
		// Get log generator run time arguments. 
		//
        String group = args[0];
		String topic = args[1];
		int iterations = new Integer(args[2]).intValue();
		long millisToSleep = new Long(args[3]).longValue();
		SparkStreamingUserDataGenerator logGenerator = new SparkStreamingUserDataGenerator();
		logGenerator.generateLogMessages(group, topic, iterations, millisToSleep);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void generateLogMessages(String group, String topic, int iterations, long millisToSleep) {

		Connection con = null;
		Properties props = new Properties();
		props.put("metadata.broker.list", "localhost:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);

		Producer producer = new Producer(config);

        // Get current system time
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
		Date currDate = new Date();
		String strDate = df.format(currDate);
		LOGGER.debug("strDate: " + strDate);

		try {
			con = JdbcConnection.getJdbcConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(QUERY);

			while (result.next()) {
				StringBuilder dataStream = new StringBuilder();
				
				dataStream.append(result.getString("FIRST_NAME"));
				dataStream.append(DELIMITER);
				dataStream.append(result.getString("LAST_NAME"));
				dataStream.append(DELIMITER);				
				dataStream.append(result.getString("USER_ID"));
				dataStream.append(DELIMITER);
				dataStream.append(result.getDate("METRIC_DATE"));
				dataStream.append(DELIMITER);
				dataStream.append(result.getInt("AHT"));
				dataStream.append(DELIMITER);
				dataStream.append(result.getInt("INTERACTIONS"));
				dataStream.append(DELIMITER);
				dataStream.append(result.getInt("PRODUCTION_EFFICIENCY"));
				dataStream.append(DELIMITER);
				
				KeyedMessage data = new KeyedMessage(topic, dataStream.toString());
				producer.send(data);
				
				System.out.println("sending " + dataStream.toString());
				//LOGGER.debug("sending " + dataStream.toString());
				
				try {
					Thread.sleep(millisToSleep);
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage());
				}
				
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally{
			producer.close();
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


