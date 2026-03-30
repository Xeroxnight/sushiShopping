

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.Formule;
import metier.GlobalCLass;
import metier.Produit;
import metier.XMLStorage;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CatalogueFormule
 */
@WebServlet("/CatalogueFormule")
public class CatalogueFormule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueFormule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = AppConfig.getPath();
        GlobalCLass loaded = XMLStorage.decoder(path);
        
        if(loaded == null)
		{
			//loaded = new GlobalCLass();
			response.sendRedirect("/AdminPage");
		}
        // récupérer les formules
        List<Formule> formules = loaded.getFormules();
        // récupérer tous les produits pour le formulaire de création
        List<Produit> tousLesProduits = new java.util.ArrayList<>(loaded.getCatalogue().values());
        
        // envoyer à la JSP
        request.setAttribute("listeFormules", formules);
        request.setAttribute("tousLesProduits", tousLesProduits);
        
        // redirection vers la JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalogFormule.jsp");
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
