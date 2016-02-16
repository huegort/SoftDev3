package ie.gmit.its.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ie.gmit.its.book.Book;
import ie.gmit.its.dao.GenericDAO;

public class BookDAO extends GenericDAO{
	private Book populate(ResultSet rs) throws SQLException{
		Book current = new Book();
		current.setId(rs.getLong("id"));
		current.setTitle(rs.getString("title"));
		current.setAuthor(rs.getString("author"));
		current.setPrice(rs.getInt("price"));
		current.setDatePublished(rs.getDate("datePublished"));
		current.setGenre(rs.getString("genre"));
		return current;
		
	}
	private List<Book> executeQuery(String sql) throws SQLException{
		List<Book> returnSet = new LinkedList<Book>();
		Connection connection= null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
				Book current = populate(rs);
				
				returnSet.add(current);
			}
		}finally{
			if (connection != null){
				connection.close();
			}
		}
		return returnSet;
	}
	
	public void save(Book book) throws SQLException{
		String sql = "insert into Book (title,author,price,datePublished,genre) values ("
				+ "'"+book.getTitle()+"',"
				+ "'"+book.getAuthor()+"',"
				+ ""+book.getPrice()+","
				+ "'"+book.getDatePublishedAsSQLString()+"',"
				+ "'"+book.getGenre()+"');";
		executeUpdate(sql);	
	}
	public void update(Book book) throws SQLException{
		String sql = "update book set title = '"+book.getTitle()+"', "
				+ "author='"+book.getAuthor()+"', "
				+ "price="+book.getPrice()+","
				+ "datePublished='"+book.getDatePublishedAsSQLString()+"',"
				+ " genre='"+book.getGenre()+"' "
				+ "where id = "+book.getId()+";";
		System.out.println("about to edit \n"+sql);
		executeUpdate(sql);
	}
	public void delete(Book book) throws SQLException{
		delete(book.getId());
	}
	public void delete(long bookId) throws SQLException{
		String sql = "delete from book where id = "+bookId+";";
		executeUpdate(sql);
	}
	public List<Book> getAll() throws SQLException{
		String sql ="select * from Book;";
		return executeQuery(sql);
	}
	public List<Book> getAllByAuthor(String author) throws SQLException{
		String sql ="select * from Book where author ='"+author+"';";
		return executeQuery(sql);
	}
	public Book findById(long id) throws SQLException{
		String sql = "select * from Book where id ="+id+";";
		List<Book> list = executeQuery(sql);
		if (list.size()>=1){
			return list.get(0);
		}else{
			return null;
		}
	}
	// lots of other methods
}
