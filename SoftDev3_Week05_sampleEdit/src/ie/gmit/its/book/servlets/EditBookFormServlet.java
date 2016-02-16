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
 * Servlet implementation class EditBookFormServlet
 */
@WebServlet("/EditBookFormServlet")
public class EditBookFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		String view ="CreateEditBook.jsp";
		long id = -1;
		try{
			id = Long.parseLong(idString);
		}catch(NumberFormatException nfe){
			System.out.println("error with id "+nfe);
		}
		BookDAO bookDAO = new BookDAO();
		Book book = null;
		try {
			book = bookDAO.findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(e);
		}
		request.setAttribute("book", book);
		request.setAttribute("isEdit", true);
		
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
