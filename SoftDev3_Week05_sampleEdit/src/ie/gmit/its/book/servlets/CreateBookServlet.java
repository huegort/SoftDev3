package ie.gmit.its.book.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.its.book.Book;
import ie.gmit.its.book.dao.BookDAO;

/**
 * Servlet implementation class CreateBookServlet
 */
@WebServlet("/CreateBookServlet")
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book(request);
		BookDAO bookDAO = new BookDAO();
		try {
			bookDAO.save(book);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		request.setAttribute("message", "the book has been created");
		
		String view="DisplayBooksServlet"; // this is a servlet
		request.getRequestDispatcher(view).forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
