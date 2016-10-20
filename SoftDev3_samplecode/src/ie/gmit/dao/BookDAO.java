package ie.gmit.dao;

import java.util.List;

import ie.gmit.domain.Book;

public interface BookDAO {
	public void insert(Book book);
	public void update(Book book);
	public void delete(long id);
	public void delete(Book book);
	
	public Book findById(long id);
	public List<Book> getAll();

}
