package ie.gmit.cdao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MainClass {
	public static void main(String[] args) throws SQLException {
		System.out.println("in c");
		Book book = new Book(-1, "asdf","szfdg",232,new Date(), "comedy");
		
		
		
		BookDAO dao = new BookDAO();
		dao.create(book);
		List<Book> list = dao.getAll();
		
		for(Book b : list){
			System.out.println(b);
		}
		
	}
}
