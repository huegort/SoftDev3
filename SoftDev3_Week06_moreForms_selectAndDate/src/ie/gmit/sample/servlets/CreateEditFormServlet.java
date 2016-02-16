package ie.gmit.sample.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sample.model.Entity;

/**
 * Servlet implementation class CreateEditFormServlet
 */
@WebServlet("/CreateEditFormServlet")
public class CreateEditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEditFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *	in this code I assume that if an id is passed in then we are doing an edit
	 *	otherwise this is a create
	 *	
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if id exists we are doing an edit
		boolean isEdit = false;
		String idString = request.getParameter("id");
		long id = -1;
		Entity entity = null;
		if (idString != null){
			try{
				id = Long.parseLong(idString);
				// get enity from DB
				entity = new Entity(id,"Fiction");
				request.setAttribute("entity", entity);
				isEdit = true;
			}catch(NumberFormatException nfe){
				throw new IOException(nfe);
			}
		}
		// populate the genres list for the options
		// this could be done by a service
		List<String> genres = new ArrayList<String>();
		genres.add("Crime");
		genres.add("Comedy");
		genres.add("Fiction");
		request.setAttribute("isEdit", isEdit);
		request.setAttribute("genres", genres);
		
		String view = "CreateEditEntity.jsp";
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
