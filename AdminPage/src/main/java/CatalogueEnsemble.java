

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.Ensemble;
import metier.GlobalCLass;
import metier.Sushi;
import metier.XMLStorage;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CatalogueEnsemble
 */
@WebServlet("/CatalogueEnsemble")
public class CatalogueEnsemble extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueEnsemble() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 String path = AppConfig.getPath();
	        GlobalCLass loaded = XMLStorage.decoder(path);
	        
	       
	        
	        if(loaded == null)
			{
				//loaded = new GlobalCLass();
				response.sendRedirect("/AdminPage");
			}
            // récupérer les ensembles
            List<Ensemble> ensembles = loaded.getEnsembles();
            // récupérer les sushis pour le formulaire de création
            
            System.out.println("=== CatalogueEnsemble doGet ===");
	        List<Sushi> sushis = loaded.getSushis();
	        System.out.println("Nombre de sushis trouvés : " + (sushis != null ? sushis.size() : 0));
	        
	        
            //List<Sushi> sushis = loaded.getSushis();
            
            // envoyer à la JSP
            request.setAttribute("listeEnsembles", ensembles);
            request.setAttribute("listeSushis", sushis);
            
            // redirection vers la JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogEnsemble.jsp");
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
