package ie.gmit.cdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {
	final String db="its";
	final String user="root";
	final String password ="";
	
	abstract String getTableName();
	abstract T populateObject(ResultSet rs) throws SQLException;
	abstract String getColumnNamesNoId();
	abstract String getQuestionMarks();
	abstract PreparedStatement populatePreparedStatement(PreparedStatement ps ,T entity) throws SQLException;
	
	
	protected Connection getConnection() throws SQLException{
		Connection connection = null;
		Statement statement;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost/"+db+"?user="+user+"&password="+password+"";
			connection = DriverManager.getConnection(connectionUrl);
		}catch(ClassNotFoundException cnfe){
			System.out.println("could not load driver "+cnfe.getMessage());
			throw new RuntimeException();
		}
		return connection;
	}
	protected void closeConnection(Connection connection){
		try {
			if (connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<T> getAll() throws SQLException{
		String sql = "select * from "+getTableName()+";";
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<T> returnList = new ArrayList<T>();
		while (rs.next()){
			T entity = populateObject(rs);
			returnList.add(entity);
		}
		closeConnection(connection); 
		return returnList;
	}
	public T findById(long id) throws SQLException{
		Connection connection = null;
		try{
			String sql = "select * from "+getTableName()+" where id = "+id+" ;";
			connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
		
			if (rs.next()){
				T entity = populateObject(rs);
				return entity;
			}
			return null;
		}finally{
			closeConnection(connection);
		}
	}
	public void create(T entity) throws SQLException{
		Connection connection = null;
		try{
			String sql = "insert into "+getTableName()+" ( "+getColumnNamesNoId()+")"
					+ " values ("+getQuestionMarks()+") ;";
			System.out.println(sql);
			connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps = populatePreparedStatement(ps, entity);
			
			ps.executeUpdate();
		
		}finally{
			closeConnection(connection);
		}
		
	}
	public void edit(T entity){
		
	}
	public void delete(T entity){
		
	}
	

}
