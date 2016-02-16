package ie.gmit.its.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDAO {
	
	protected Connection getConnection() throws SQLException{
		Connection connection = null;
		Statement statement;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost/its?user=root&password=";
			connection = DriverManager.getConnection(connectionUrl);
		}catch(ClassNotFoundException cnfe){
			System.out.println("could not load driver "+cnfe.getMessage());
			throw new RuntimeException();
		}
		return connection;
	}
	protected void executeUpdate(String sql) throws SQLException{
		Connection connection = null;
		Statement statement;
		
		try{
			
			connection = getConnection();
			
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}finally{
			if (connection != null){
				connection.close();
			}
		}
	}

}
