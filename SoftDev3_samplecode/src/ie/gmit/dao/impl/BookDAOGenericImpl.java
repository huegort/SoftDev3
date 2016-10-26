package ie.gmit.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ie.gmit.dao.BookDAO;
import ie.gmit.dao.GenericDAO;
import ie.gmit.domain.Book;
import ie.gmit.domain.Entity;

public class BookDAOGenericImpl extends GenericDAO<Book> implements BookDAO{

	@Override
	public Class getDAOClass() {
		return Book.class;
	}
	/*@Override
	protected void populatePS(PreparedStatement ps, Book book) throws SQLException {
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setTimestamp(3, new java.sql.Timestamp(book.getDatePublished().getTime()));
		
		
	}
*/
	@Override
	protected Book bindRSToObject(ResultSet rs) throws SQLException {
		Book b = new Book();
		b.setId(rs.getLong("id"));
		b.setTitle(rs.getString("title"));
		b.setAuthor(rs.getString("author"));
		b.setDatePublished(new Date(rs.getTimestamp("datePublished").getTime()));
		
		return b;
	}

	
	
	
	

}
