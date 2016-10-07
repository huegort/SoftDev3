package ie.gmit.test;

import java.util.Date;
import java.util.List;

import ie.gmit.dao.BookDAO;
import ie.gmit.dao.impl.BookDAOListImpl;
import ie.gmit.dao.impl.BookDAOSimpleImpl;
import ie.gmit.domain.Book;

public class TestDAO {
//test
	public static void main(String[] args) {
		System.out.println("test");
		
		BookDAO bookDAO = new BookDAOSimpleImpl();
		
		Book book1 = new Book(1,"title1","author1",new Date());
		Book book2 = new Book(1,"title2","author2",new Date());
		Book book3 = new Book(1,"title3","author3",new Date());
		
		bookDAO.insert(book1);
		bookDAO.insert(book2);
		bookDAO.insert(book3);
		
		// test get one
		long bookId = book1.getId();
		Book testBook = bookDAO.findById(bookId);
		assert book1.equals(testBook);
		
		// test get all
		List<Book> list = bookDAO.getAll();
		assert list.size() ==3;
		
		// test update
		book1.setTitle("test");
		bookDAO.update(book1);
		testBook = bookDAO.findById(bookId);
		assert "test".equals(testBook.getTitle());
		
		// test delete
		bookDAO.delete(book1);
		assert bookDAO.findById(bookId) == null;
		list = bookDAO.getAll();
		assert list.size() == 2;
		System.out.println("Yipppeee it all works");
	}

}
