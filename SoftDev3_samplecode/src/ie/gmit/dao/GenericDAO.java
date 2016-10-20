package ie.gmit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import ie.gmit.domain.Book;
import ie.gmit.domain.Entity;

public abstract class GenericDAO<T extends Entity> {
	String dbHost="localhost";
	String dbUser= "root";
	String dbPassword="";
	String dbDatabase="softdev3";
	
	
	String dbUrl ="jdbc:mysql://"+dbHost+"/"+dbDatabase;
	
	
	protected abstract String getTableName();
	
	protected abstract String[] getColumnNames();
	protected abstract void populatePS(PreparedStatement ps, T entity) throws SQLException;
	protected abstract Book bindRSToObject(ResultSet rs) throws SQLException;

	
	String getInsertColumnNames(){
		String returnString = "";
		String[] colNames = getColumnNames();
		returnString += colNames[0];
		for (int i =1 ; i< colNames.length; i++){
			returnString += "," +colNames[i];
		}
		return returnString;
	}
	String getQuestionMarks(){
		String returnString = "";
		String[] colNames = getColumnNames();
		returnString += " ? ";
		for (int i =1 ; i< colNames.length; i++){
			returnString += ", ? ";
		}
		return returnString;
		
	}
	
	public void insert(T entity){
		Connection connection = null;
		try{
			String sql ="insert into "+getTableName()+" ("+getInsertColumnNames()+") values ("+getQuestionMarks()+");";
			System.out.println(sql);
			
			PreparedStatement ps = getPreparedStatement(connection,sql);
			populatePS(ps, entity);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
		    rs.next();
		    entity.setId( rs.getLong(1));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
	}
	public void update(T book){
		
	}
	public void delete(long id){
		
	}
	public void delete(T book){
		
	}
	
	public T findById(long id){
		return null;
	}
	public List<T> getAll(){
		return null;	
	}
	private void closeConnection(Connection connection){
		if (connection!= null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		// statement! we should be using preparedStatement
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		return ps;
	}

	
	
}
