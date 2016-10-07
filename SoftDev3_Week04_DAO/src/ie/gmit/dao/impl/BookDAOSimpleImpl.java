package ie.gmit.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			// statement! we should be using preparedStatement
			String sql ="insert into book (title, author, datepublished) values (?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setDate(3, new java.sql.Date(book.getDatePublished().getTime()));
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
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
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
		
		

	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
