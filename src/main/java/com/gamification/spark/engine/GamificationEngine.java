package com.gamification.spark.engine;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
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
	private static final String TBL_GAME_PURSUIT_LEVEL = "GAME_PURSUIT_LEVEL";
	private static final String TBL_PURSUIT_LEVEL_PARTICIPANTS = "GAME_LEVEL_PARTICIPANT";
	private static final String TBL_USER_DATA = "USER_DATA";
	private static final String TBL_OBJECTIVE = "GAME_OBJECTIVE";
	private static final String TBL_GAME_OBJECTIVE_COMPLETION = "GAME_OBJECTIVE_COMPLETION";
	private static final String TBL_GAME_LEVEL_PRTCPNT_COMPLETION = "GAME_LEVEL_PRTCPNT_COMPLETION";
	private static final String CONDN_IS_DELETED = "IS_DELETED = '0'";
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
		Map<String, String> optionPUR = new HashMap<String, String>();
		optionPUR.put("url", ORACLE_CONNECTION_URL);
		optionPUR.put("dbtable", TBL_GAME_PURSUIT);

		DataFrame PURSUITS_DF = sqlContext.read().format("jdbc").options(optionPUR).load();
		PURSUITS_DF = PURSUITS_DF.filter(CONDN_IS_DELETED);
		PURSUITS_DF = PURSUITS_DF.withColumnRenamed("ID", "ID_0").withColumnRenamed("START_DATE", "PURSUIT_START_DATE").withColumnRenamed("END_DATE", "PURSUIT_END_DATE");
//		PURSUITS_DF.show();
		
		Map<String, String> optionsPURLVL = new HashMap<String, String>();
		optionsPURLVL.put("url", ORACLE_CONNECTION_URL);
		optionsPURLVL.put("dbtable", TBL_GAME_PURSUIT_LEVEL);
		
		DataFrame PURLVL_DF = sqlContext.read().format("jdbc").options(optionsPURLVL).load();
		PURLVL_DF = PURLVL_DF.filter(CONDN_IS_DELETED);
		PURLVL_DF = PURLVL_DF.withColumnRenamed("ID", "ID_1").withColumnRenamed("VERSION", "VERSION_1").withColumnRenamed("IS_DELETED", "IS_DELETED_1");
//		PURLVL_DF.show();
		
		Map<String, String> optionObj = new HashMap<String, String>();
		optionObj.put("url", ORACLE_CONNECTION_URL);
		optionObj.put("dbtable", TBL_OBJECTIVE);
		
		DataFrame OBJ_DF = sqlContext.read().format("jdbc").options(optionObj).load();
		OBJ_DF = OBJ_DF.filter(CONDN_IS_DELETED);
		OBJ_DF = OBJ_DF.withColumnRenamed("ID", "ID_2");
		OBJ_DF = OBJ_DF.withColumnRenamed("PURSUIT_LEVEL_ID", "OBJ_PURSUIT_LEVEL_ID");
//		OBJ_DF.show();
		
		Map<String, String> optionParticipants = new HashMap<String, String>();
		optionParticipants.put("url", ORACLE_CONNECTION_URL);
		optionParticipants.put("dbtable", TBL_PURSUIT_LEVEL_PARTICIPANTS);
		
		DataFrame PARTCPNTS_DF = sqlContext.read().format("jdbc").options(optionParticipants).load();
		PARTCPNTS_DF = PARTCPNTS_DF.filter(CONDN_IS_DELETED);
		PARTCPNTS_DF = PARTCPNTS_DF.withColumnRenamed("ID", "ID_3");
		PARTCPNTS_DF = PARTCPNTS_DF.withColumnRenamed("PURSUIT_LEVEL_ID", "PRTCPNT_PURSUIT_LEVEL_ID");
//		PARTCPNTS_DF.show();
		
		Map<String, String> optionUsrData = new HashMap<String, String>();
		optionUsrData.put("url", ORACLE_CONNECTION_URL);
		optionUsrData.put("dbtable", TBL_USER_DATA);
		DataFrame USRDATA_DF = sqlContext.read().format("jdbc").options(optionUsrData).load();
//		USRDATA_DF.show();
		
		DataFrame PUR_AND_PURLVL_DF = PURSUITS_DF.join(PURLVL_DF, col("ID_0").equalTo(col("PURSUIT_ID")));
//		PUR_AND_PURLVL_DF.show();
		
		DataFrame PUR_AND_PURLVL_AND_OBJ_DF = PUR_AND_PURLVL_DF.join(OBJ_DF, col("ID_1").equalTo(col("OBJ_PURSUIT_LEVEL_ID")));
//		PUR_AND_PURLVL_AND_OBJ_DF.show();
		
		DataFrame JND_PARTCPNTS_DF = PUR_AND_PURLVL_AND_OBJ_DF.join(PARTCPNTS_DF, col("ID_1").equalTo(col("PRTCPNT_PURSUIT_LEVEL_ID")));
//		JND_PARTCPNTS_DF.show();
		
		DataFrame PARTCPNTS_USER_DATA_DF = JND_PARTCPNTS_DF.join(USRDATA_DF, col("PARTICIPANT_ID").equalTo(col("USER_ID")));
//		PARTCPNTS_USER_DATA_DF.show();
		
		DataFrame INTERACTIONS_DF = PARTCPNTS_USER_DATA_DF.groupBy(col("USER_ID")).agg(sum(col("INTERACTIONS")));
		INTERACTIONS_DF = INTERACTIONS_DF.withColumnRenamed("USER_ID", "AGG_USER_ID");
		INTERACTIONS_DF = INTERACTIONS_DF.withColumnRenamed("sum(INTERACTIONS)", "INTERACTIONS_SUM");
//		INTERACTIONS_DF.show();
		
		DataFrame CALCULATED_USER_DATA_DF = PARTCPNTS_USER_DATA_DF.join(INTERACTIONS_DF, col("USER_ID").equalTo(col("AGG_USER_ID")));
//		CALCULATED_USER_DATA_DF.show(50);
		
		Date date = null;
		Long dateLong = null;
		try{
			date = fmt.parse("26-06-2016");
			dateLong = date.getTime();
		}catch(Exception objE){
			//Do nothing.
		}
		
		DataFrame AHT_COMPLETION_DF = CALCULATED_USER_DATA_DF.where("BEHAVIOR_KEY = 'OLAPService.AHT'").where("METRIC_MS >= " + dateLong.toString()).where(col("AHT").leq(col("CRITERIA_COMPARATOR_VALUE")));
		DataFrame INTERACTION_COMPLETION_DF = CALCULATED_USER_DATA_DF.where("BEHAVIOR_KEY = 'OLAPService.Interactions'").where("METRIC_MS >= " + dateLong.toString()).where(col("INTERACTIONS_SUM").gt(col("CRITERIA_COMPARATOR_VALUE")));
		
		DataFrame USR_AHT_DF = AHT_COMPLETION_DF.select(col("USER_ID")).distinct();
		DataFrame USR_INTERACTION_DF = INTERACTION_COMPLETION_DF.select(col("USER_ID")).distinct();
//		DataFrame PURSUIT_COMPLETION_DF = USR_AHT_DF.intersect(USR_INTERACTION_DF);
//		USR_AHT_DF.show();
//		USR_INTERACTION_DF.show();
//		PURSUIT_COMPLETION_DF.show();
		//jdbcDF.show();
		
		Properties props = new Properties();
		props.setProperty("user", ORACLE_USERNAME);
		props.setProperty("password", ORACLE_PWD);
		
		DataFrame USR_AHT_OBJ_COMPLN_DF = AHT_COMPLETION_DF.select(col("ID_0"), col("ID_2"), col("ID_3"), col("VERSION_1"), col("IS_DELETED_1")).withColumnRenamed("VERSION_1", "VERSION").withColumnRenamed("IS_DELETED_1", "IS_DELETED").withColumnRenamed("ID_0", "ID").withColumnRenamed("ID_2", "OBJECTIVE_ID").withColumnRenamed("ID_3", "LEVEL_PARTICIPANT_ID").distinct();
		USR_AHT_OBJ_COMPLN_DF.write().mode(SaveMode.Append).jdbc(ORACLE_CONNECTION_URL, TBL_GAME_OBJECTIVE_COMPLETION, props);

		DataFrame USR_INTERACTIVE_OBJ_COMPLN_DF = INTERACTION_COMPLETION_DF.select(col("ID_0"), col("ID_2"), col("ID_3"), col("VERSION_1"), col("IS_DELETED_1")).withColumnRenamed("VERSION_1", "VERSION").withColumnRenamed("IS_DELETED_1", "IS_DELETED").withColumnRenamed("ID_0", "ID").withColumnRenamed("ID_2", "OBJECTIVE_ID").withColumnRenamed("ID_3", "LEVEL_PARTICIPANT_ID").distinct();
		USR_INTERACTIVE_OBJ_COMPLN_DF.write().mode(SaveMode.Append).jdbc(ORACLE_CONNECTION_URL, TBL_GAME_OBJECTIVE_COMPLETION, props);
		//USR_AHT_OBJ_COMPLN_DF.show();
		
		DataFrame USR_AHT_LVL_COMPLN_DF = AHT_COMPLETION_DF.select(col("ID_0"), col("ID_3"), col("VERSION_1"), col("IS_DELETED_1")).withColumnRenamed("VERSION_1", "VERSION").withColumnRenamed("IS_DELETED_1", "IS_DELETED").withColumnRenamed("ID_0", "ID").withColumnRenamed("ID_3", "LEVEL_PARTICIPANT_ID").distinct();
		DataFrame USR_INTERACTIVE_LVL_COMPLN_DF = INTERACTION_COMPLETION_DF.select(col("ID_0"), col("ID_3"), col("VERSION_1"), col("IS_DELETED_1")).withColumnRenamed("VERSION_1", "VERSION").withColumnRenamed("IS_DELETED_1", "IS_DELETED").withColumnRenamed("ID_0", "ID").withColumnRenamed("ID_3", "LEVEL_PARTICIPANT_ID").distinct();
		DataFrame USR_LVL_COMPLN_DF = USR_AHT_LVL_COMPLN_DF.intersect(USR_INTERACTIVE_LVL_COMPLN_DF);
		USR_LVL_COMPLN_DF.write().mode(SaveMode.Append).jdbc(ORACLE_CONNECTION_URL, TBL_GAME_LEVEL_PRTCPNT_COMPLETION, props);
		
	}
}
