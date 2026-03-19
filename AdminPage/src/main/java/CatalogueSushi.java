

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.GlobalCLass;
import metier.Sushi;
import metier.XMLStorage;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Catalogue
 */
@WebServlet("/CatalogueSushi")
public class CatalogueSushi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueSushi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = AppConfig.getInstance().getXmlPath();
		GlobalCLass loaded = XMLStorage.decoder(path);
		// System.out.println("absolute =>"+"C:\\Users\\timeo\\Desktop\\Nouveau dossier\\bdd.xml");
		// System.out.println("===+>"+AppConfig.getInstance().getXmlPath());
	    // récupérer les sushis
		if(loaded == null)
		{
			response.sendRedirect("/AdminPage");
		}
		else
		{
			List<Sushi> sushis = loaded.getSushis();

		    // envoyer à la JSP
		    request.setAttribute("listeSushis", sushis);

		    // redirection vers la JSP
		    RequestDispatcher dispatcher = request.getRequestDispatcher("catalogSushiJSP.jsp");
		    dispatcher.forward(request, response);
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
