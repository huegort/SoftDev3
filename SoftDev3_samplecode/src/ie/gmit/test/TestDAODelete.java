package ie.gmit.test;

import ie.gmit.dao.BookDAO;
import ie.gmit.dao.impl.BookDAOSimpleImpl;

public class TestDAODelete {

	public static void main(String[] args) {
		BookDAO dao = new BookDAOSimpleImpl();
		dao.delete(11);

	}

}
