package ie.gmit.sample.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sample.model.Entity;

/**
 * Servlet implementation class CreateEditEnityServlet
 */
@WebServlet("/CreateEditEnityServlet")
public class CreateEditEnityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEditEnityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *	deals with processing the form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the parameters from the form
		String genre = request.getParameter("genre");
		
		Entity entity = new Entity(-1,genre);
		
		// if id is set then edit otherwise create
		String idString = request.getParameter("id");
		if (idString == null || idString.equals("")){
			// create
			// data base create
		}else{
			long id = Long.parseLong(idString);
			entity.setId(id);
			// database edit
		}
		// redirect to where you want
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
