

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.GlobalCLass;
import metier.XMLStorage;

import java.io.IOException;

/**
 * Servlet implementation class CatalgogueUser
 */
@WebServlet("/CatalogueUtilisateur")
public class CatalogueUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// recuperation de la BD 
		String path = AppConfig.getPath();
		GlobalCLass loaded = XMLStorage.decoder(path);
		//
		if(loaded == null)
		{
			//loaded = new GlobalCLass();
			response.sendRedirect("/AdminPage");
		}
		request.setAttribute("listUser", loaded.getUsers());
		RequestDispatcher dispatcher = request.getRequestDispatcher("CatalogueUtilisateur.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
