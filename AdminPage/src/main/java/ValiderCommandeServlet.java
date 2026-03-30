

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.GlobalCLass;
import metier.HistoriqueCommande;

import java.io.IOException;

/**
 * Servlet implementation class ValiderCommandeServlet
 */
@WebServlet("/ValiderCommandeServlet")
public class ValiderCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderCommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            
	            HistoriqueCommande commande = new HistoriqueCommande();
	            commande.setIdCommande(id);
	            
	            GlobalCLass global = new GlobalCLass();
	            global.validerCommandeInCatalogRest(commande);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        response.sendRedirect("CatalogueCommande");
	}

}
