package com.gamification.spark.dialech;

import java.sql.Types;

import org.apache.spark.sql.jdbc.JdbcDialect;
import org.apache.spark.sql.jdbc.JdbcDialects;
import org.apache.spark.sql.jdbc.JdbcType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import scala.Option;

public class RegisterJDBCDialect {
	public static void register(){
		
			JdbcDialect oracleDialect = new JdbcDialect() {
				@Override
				public boolean canHandle(String url) {
					// TODO Auto-generated method stub
					return url !=null && (url.startsWith("jdbc:oracle") || url.contains("oracle"));
				}
				@Override
				public Option<JdbcType> getJDBCType(DataType dt){
					if(DataTypes.StringType.sameType(dt)) {
	                    return Option.apply(new JdbcType("VARCHAR2(255)", java.sql.Types.VARCHAR));
	                } else if(DataTypes.BooleanType.sameType(dt)){
	                    return Option.apply(new JdbcType("NUMBER(1)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.IntegerType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(10)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.LongType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(19)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.DoubleType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(19,4)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.FloatType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(19,4)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.ShortType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(5)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.ByteType.sameType(dt)) {
	                    return Option.apply(new JdbcType("NUMBER(3)", java.sql.Types.NUMERIC));
	                } else if(DataTypes.BinaryType.sameType(dt)) {
	                    return Option.apply(new JdbcType("BLOB", java.sql.Types.BLOB));
	                } else if(DataTypes.TimestampType.sameType(dt)) {
	                    return Option.apply(new JdbcType("DATE", Types.DATE));
	                } else if(DataTypes.DateType.sameType(dt)) {
	                    return Option.apply(new JdbcType("DATE", Types.DATE));
	                } else if(DataTypes.createDecimalType().sameType(dt)) { //unlimited
	                	//      return  DecimalType.Fixed(precision, scale) => Some(JdbcType("NUMBER(" + precision + "," + scale + ")", java.sql.Types.NUMERIC))
	                    return Option.apply(new JdbcType("NUMBER(38,4)", Types.NUMERIC));
	                }
	                return Option.empty();
				}
			};
		JdbcDialects.registerDialect(oracleDialect);
	}
}
