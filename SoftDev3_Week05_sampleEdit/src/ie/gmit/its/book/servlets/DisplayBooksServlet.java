package ie.gmit.its.book.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.its.book.Book;
import ie.gmit.its.book.dao.BookDAO;

/**
 * Servlet implementation class DisplayBooksServlet
 */
@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		List<Book> books;
		
		try {
			books = bookDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		request.setAttribute("books", books);
		
		String view="DisplayBooks.jsp"; // this is a servlet
		request.getRequestDispatcher(view).forward(request, response);
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
