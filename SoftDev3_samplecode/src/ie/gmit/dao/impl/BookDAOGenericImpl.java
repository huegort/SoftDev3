package ie.gmit.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ie.gmit.dao.GenericDAO;
import ie.gmit.domain.Book;
import ie.gmit.domain.Entity;

public class BookDAOGenericImpl extends GenericDAO<Book>{

	@Override
	protected String getTableName() {
		
		return "book";
	}

	@Override
	protected String[] getColumnNames() {
		String[] bookCol = {"title", "author", "datepublished"};
		return bookCol;
	}

	@Override
	protected void populatePS(PreparedStatement ps, Book book) throws SQLException {
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setTimestamp(3, new java.sql.Timestamp(book.getDatePublished().getTime()));
		
		
	}

	@Override
	protected Book bindRSToObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
