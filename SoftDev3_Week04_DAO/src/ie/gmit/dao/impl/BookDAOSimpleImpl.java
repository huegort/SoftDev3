package ie.gmit.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import ie.gmit.dao.BookDAO;
import ie.gmit.domain.Book;

public class BookDAOSimpleImpl implements BookDAO {
	String dbHost="localhost";
	String dbUser= "root";
	String dbPassword="";
	String dbDatabase="softdev3";
	
	
	String dbUrl ="jdbc:mysql://"+dbHost+"/"+dbDatabase;
	
	@Override
	public void insert(Book book) {
		Connection connection = null;
		try{
			String sql ="insert into book (title, author, datepublished) values (?,?,?);";
		
			PreparedStatement ps = getPreparedStatement(connection,sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setDate(3, new java.sql.Date(book.getDatePublished().getTime()));
			
			System.out.println(ps.executeUpdate());
			ResultSet rs = ps.getGeneratedKeys();
		    rs.next();
		   book.setId( rs.getLong(1));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
		}
	}

	
	public void badInsert(Book book) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			// statement! we should be using preparedStatement
			Statement statement = connection.createStatement();
			
			String sql = "insert into Book (title,author,datepublished) "
					+ "values ( "
					+ "'"+book.getTitle()+"', "
					+ "'"+book.getAuthor()+"',"
					+ "NULL" 
					+ " );";
			
			
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(connection);
			
		}
	
		

	}

	@Override
	public void update(Book book) {
		Connection connection = null;
		String sql = "update book set title=?, author = ? , datepublished = ? where id = ?";
		try{
			 PreparedStatement ps = getPreparedStatement(connection,sql);
			 
			 ps.setString(1, book.getTitle());
			 ps.setString(2, book.getAuthor());
			 ps.setDate(3, new java.sql.Date(book.getDatePublished().getTime()));
			 ps.setLong(4, book.getId());
			 
			 ps.executeUpdate();
			 	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(connection);
		}
		

	}

	@Override
	public void delete(long id) {
		String sql = "delete from book where id = ?";
		Connection connection = null;
		try{
			PreparedStatement ps = getPreparedStatement(connection, sql);
			ps .setLong(1, id);
			ps.executeUpdate();	
		}catch (SQLException e){
			throw new RuntimeException(e);
		}finally{
			closeConnection (connection);
		}

	}

	@Override
	public void delete(Book book) {
		delete (book.getId());

	}

	@Override
	public Book findById(long id) {
		Book book = new Book();
		String sql = "select * from book where id = ?";
		Connection connection = null;
		try{
			PreparedStatement ps = this.getPreparedStatement(connection, sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDatePublished(new Date(rs.getDate("datePublished").getTime()));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}finally{
			this.closeConnection(connection);
			
		}
		
		return book;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
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
