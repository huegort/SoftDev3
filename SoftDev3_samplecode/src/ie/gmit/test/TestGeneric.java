package ie.gmit.test;

import java.util.Date;

import ie.gmit.dao.BookDAO;
import ie.gmit.dao.GenericDAO;
import ie.gmit.dao.impl.BookDAOGenericImpl;
import ie.gmit.domain.Book;

public class TestGeneric {

	public static void main(String[] args) {
		BookDAO dao = new BookDAOGenericImpl();
		
		Book b = new Book(56,"test pdate","other", new Date());
		
		dao.insert(b);
		//dao.update(b);
		//dao.delete(b);

	}

}
