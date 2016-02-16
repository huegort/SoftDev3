package ie.gmit.person.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ie.gmit.person.model.Person;

public class SimplePersonDAOImpl implements PersonDAO {

	protected Connection getConnection() throws SQLException{
		Connection connection = null;
		Statement statement;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://localhost/its?user=root&password=";
			connection = DriverManager.getConnection(connectionUrl);
		}catch(ClassNotFoundException cnfe){
			System.out.println("could not load driver "+cnfe.getMessage());
			throw new RuntimeException();
		}
		return connection;
	}
	@Override
	public void save(Person p) throws SQLException {
		Connection connection = getConnection();
		String sql = "insert into person (name,dob) values(?,?);";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, p.getName());
		ps.setDate(2,  new java.sql.Date(p.getDob().getTime()));
		
		ps.executeUpdate();
		
		

	}

	@Override
	public void update(Person p) {
		 

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
