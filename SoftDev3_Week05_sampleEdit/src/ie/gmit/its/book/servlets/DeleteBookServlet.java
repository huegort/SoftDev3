package ie.gmit.its.book.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.its.book.dao.BookDAO;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long bookId = -1;
		try{
			bookId = Long.parseLong(request.getParameter("id"));
		}catch(NumberFormatException nfe){
			throw new IOException(nfe);
		}
		
		
		BookDAO bookDAO = new BookDAO();
		try {
			bookDAO.delete(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		request.setAttribute("message", "the book has been deleted");
		
		String view="DisplayBooksServlet"; // this is a servlet
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
