

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.GlobalCLass;
import metier.HistoriqueCommande;
import metier.XMLStorage;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CatalogueCommande
 */
@WebServlet("/CatalogueCommande")
public class CatalogueCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueCommande() {
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
        
        if (loaded == null) {
            response.sendRedirect("AdminPage");
        } else {
            List<HistoriqueCommande> commandes = loaded.getCommandes();
            request.setAttribute("listeCommandes", commandes);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogCommande.jsp");
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
