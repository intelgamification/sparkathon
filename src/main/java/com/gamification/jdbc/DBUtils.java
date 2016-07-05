package com.gamification.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

	private static Connection connection = JdbcConnection.getJdbcConnection();

	private static Connection getConnection(){
		if(connection == null){
			connection = JdbcConnection.getJdbcConnection();
		}
		return connection;
	}
	
	public static int updateDB(String query, String... parameters){
		int result = -1;
		//assert (connection != null) : "Connection is null";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(parameters[0]));
			stmt.setString(2, parameters[1]);
			stmt.setDate(3, Date.valueOf(parameters[2]));
			stmt.setString(4, parameters[3]);

			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			/*try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		return result;
	} 
	
}
