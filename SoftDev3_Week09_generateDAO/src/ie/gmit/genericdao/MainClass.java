package ie.gmit.genericdao;

import ie.gmit.book.Book;

public class MainClass {

	public static void main(String[] args) {
		GenerateDAO.generateDBTable(Book.class);
		GenerateDAO.generateDAO(Book.class);

	}

}
