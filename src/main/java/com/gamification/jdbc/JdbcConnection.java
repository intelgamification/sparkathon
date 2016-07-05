package com.gamification.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JdbcConnection {
	
	private static final Logger LOG = Logger.getLogger(JdbcConnection.class);
	private static final String JDBC_URL = "jdbc:oracle:thin:@172.30.16.30:1522:PUNDEV11";
	private static final String USER = "mule_snigam_65_test"; //"MULE_65_KSHANTANU";
	private static final String PASSWORD = "uklever";
	private static Connection con = null;
	
	public static Connection getJdbcConnection(){
		if (con == null) {
			try {
				System.out.println("loading driver...");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
				System.out.println("oracle driver loaded...");
			} catch (ClassNotFoundException | SQLException e) {
				LOG.error(e.getMessage());
			}
		}
		
		return con;
	}
}
