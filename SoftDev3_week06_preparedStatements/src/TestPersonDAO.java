import java.sql.SQLException;
import java.util.Date;

import ie.gmit.person.dao.PersonDAO;
import ie.gmit.person.dao.SimplePersonDAOImpl;
import ie.gmit.person.model.Person;

public class TestPersonDAO {

	public static void main(String[] args) {
		PersonDAO dao = new SimplePersonDAOImpl();
		Person p = new Person(1,"joe", new Date());
		
		try {
			dao.save(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
		

	}

}
