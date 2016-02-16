package ie.gmit.cdao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO extends GenericDAO<Book>{

	@Override
	public String getTableName(){
		return "book";
	}
	@Override
	Book populateObject(ResultSet rs) throws SQLException{
		Book book = new Book();
		book.setId				(rs.getLong		("id"));
		book.setTitle			(rs.getString	("title"));
		book.setAuthor			(rs.getString	("author"));
		book.setPrice			(rs.getInt		("price"));
		book.setDatePublished	(rs.getDate		("datePublished"));
		book.setGenre			(rs.getString	("genre"));
		
		return book;
	}
	@Override
	String getColumnNamesNoId() {
		
		return "title, author,price,datePublished, genre";
	}
	@Override
	String getQuestionMarks() {
		return "?,?,?,?,?";
	}
	@Override
	PreparedStatement populatePreparedStatement(PreparedStatement ps, Book book) throws SQLException {
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setInt(3, book.getPrice());
		ps.setDate(4, new Date(book.getDatePublished().getTime()));
		ps.setString(5, book.getGenre());
		
		return ps;
	}
	
	
}
