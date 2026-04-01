

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Boisson;
import metier.Ensemble;
import metier.Formule;
import metier.GlobalCLass;
import metier.Sushi;
import metier.XMLStorage;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CatalogueServlet
 */
@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = metier.AppConfig.getPath();
        GlobalCLass loaded = XMLStorage.decoder(path);
        
        if (loaded != null) {
            List<Sushi> sushis = loaded.getSushis();
            List<Boisson> boissons = loaded.getBoissons();
            List<Ensemble> ensembles = loaded.getEnsembles();
            List<Formule> formules = loaded.getFormules();
            
            request.setAttribute("sushis", sushis);
            request.setAttribute("boissons", boissons);
            request.setAttribute("ensembles", ensembles);
            request.setAttribute("formules", formules);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalogue.jsp");
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
