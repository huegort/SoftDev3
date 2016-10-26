package ie.gmit.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ie.gmit.domain.Book;
import ie.gmit.domain.Entity;

public abstract class GenericDAO<T extends Entity> {
	String dbHost="localhost";
	String dbUser= "root";
	String dbPassword="";
	String dbDatabase="softdev3";
	
	
	String dbUrl ="jdbc:mysql://"+dbHost+"/"+dbDatabase;
	
	protected abstract Class getDAOClass();
	
	protected  String getTableName(){
		return getDAOClass().getSimpleName().toLowerCase();
	}
	
	protected String[] getColumnNames(){
		Class tClass = getDAOClass();
		int num = tClass.getDeclaredFields().length;
		
		String[] columnNames = new String[num];
		int count= 0;
		for (Field field : tClass.getDeclaredFields()){
			System.out.println("field : "+field.getName() + " type: "+field.getType().getName());
			columnNames[count] = field.getName().toLowerCase();
			count++;
		}
		return columnNames;
	}
	protected void populatePS(PreparedStatement ps, T entity) throws SQLException{
		Class tClass = getDAOClass();
		
		int count= 1;
		for (Field field : tClass.getDeclaredFields()){
			String fieldName= field.getName();
			String getMethodName ="get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Class fieldType = field.getType();
			try {
				Method getMethod = entity.getClass().getMethod(getMethodName);
				if (fieldType.equals(String.class)){
						ps.setString(count, (String) getMethod.invoke(entity));
				}
				if (fieldType.equals(Date.class)){
						ps.setTimestamp(count, new java.sql.Timestamp(((Date) getMethod.invoke(entity)).getTime()));
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			count++;
		}
	};
	protected abstract T bindRSToObject(ResultSet rs) throws SQLException;

	
	String getInsertColumnNames(){
		StringBuilder sb = new StringBuilder();
		String[] colNames = getColumnNames();
		sb.append( colNames[0]);
		for (int i =1 ; i< colNames.length; i++){
			sb.append( ",");
			sb.append(colNames[i]);
		}
		return sb.toString();
	}
	String getQuestionMarks(){
		StringBuilder sb = new StringBuilder();
		String[] colNames = getColumnNames();
		sb.append( " ? ");
		for (int i =1 ; i< colNames.length; i++){
			sb.append( ", ? ");
		}
		return sb.toString();
		
	}
	String getUpdateColumns(){
		StringBuilder sb = new StringBuilder();
		String[] colNames = getColumnNames();
		sb.append( colNames[0]+" = ?");
		for (int i =1 ; i< colNames.length; i++){
			sb.append( ",");
			sb.append(colNames[i]+ "=?");
		}
		return sb.toString();
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
	public void update(T entity){
		Connection connection = null;
		try{
			String sql = "update "+getTableName() +" set "+getUpdateColumns()
					+ "where id = "+entity.getId();
			PreparedStatement ps = getPreparedStatement(connection,sql);
			populatePS(ps, entity);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		
	}
	public void delete(long id){
		Connection connection = null;
		try{
			String sql = "delete from "+getTableName() 
					+ " where id = "+id;
			PreparedStatement ps = getPreparedStatement(connection,sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
		
	}
	public void delete(T entity){
		delete(entity.getId());
		
	}
	
	public T findById(long id){
		T entity = null;
		String sql = "select * from "+getTableName()+" where id = ?";
		Connection connection = null;
		try{
			PreparedStatement ps = this.getPreparedStatement(connection, sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				entity = this.bindRSToObject(rs);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			this.closeConnection(connection);
			
		}
		
		return entity;
	}
	public List<T> getAll(){
		List<T> entitys = new LinkedList<T>();
		String sql = "select * from "+getTableName()+";";
		
		Connection connection = null;
		try{
			PreparedStatement ps = this.getPreparedStatement(connection, sql);
			// no population of ps
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				T  entity = this.bindRSToObject(rs);
				entitys.add(entity);
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if (connection != null){
				this.closeConnection(connection);
			}
		}
		
		return entitys;
	
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
