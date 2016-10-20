package ie.gmit.test;

import java.util.Date;

import ie.gmit.dao.BookDAO;
import ie.gmit.dao.impl.BookDAOSimpleImpl;
import ie.gmit.domain.Book;

public class TestDAOUpdate {

	public static void main(String[] args) {
		Book b = new Book(11,"updated","updated", new Date());
		
		BookDAO dao = new BookDAOSimpleImpl();
		
		dao.update(b);
		
		

	}

}
