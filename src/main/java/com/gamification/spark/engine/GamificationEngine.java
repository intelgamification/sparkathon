package com.gamification.spark.engine;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.stereotype.Component;

import com.gamification.spark.dialech.RegisterJDBCDialect;

@Component
public class GamificationEngine {
	
	private static final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("GamificationEngine").setMaster("spark://172.30.16.178:7077"));
	private static final SQLContext sqlContext = new SQLContext(sc);
	
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	//private static final String ORACLE_USERNAME = "mule_sw_65";
	private static final String ORACLE_USERNAME = "sw_pl_65";
	private static final String ORACLE_PWD = "uklever";
	private static final String ORACLE_CONNECTION_URL = "jdbc:oracle:thin:sw_pl_65/uklever@172.30.16.31:1521:PUNDEV11";
	private static final String TBL_GAME_PURSUIT = "GAME_PURSUIT";
	private static final String TBL_USER_DATA = "USER_DATA";
	private static final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	
	@PostConstruct
	public void initialize(){
		RegisterJDBCDialect.register();
		try{
			Class.forName(ORACLE_DRIVER);
		}catch(Exception ex){
			//Do nothing;
		}
		
	}
	
	public void invokeEngine(){
		// TODO Auto-generated method stub
		Map<String, String> options = new HashMap<String, String>();
		options.put("url", ORACLE_CONNECTION_URL);
		options.put("dbtable", TBL_GAME_PURSUIT);

		DataFrame jdbcDF = sqlContext.read().format("jdbc").options(options).load();
		
		jdbcDF.show();
	}
}
