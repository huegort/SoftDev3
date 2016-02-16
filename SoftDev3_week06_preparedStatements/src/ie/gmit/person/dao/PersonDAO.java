package ie.gmit.person.dao;

import java.sql.SQLException;
import java.util.List;

import ie.gmit.person.model.Person;

public interface PersonDAO {
	public void save(Person p) throws SQLException;
	public void update(Person p);
	public void delete(long id);
	
	public List<Person> getAll();
	
	public Person findById(long id);

}
