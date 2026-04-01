

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GlobalCLass;
import metier.HistoriqueCommande;
import metier.Utilisateur;
import metier.XMLStorage;

import java.io.IOException;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String path = metier.AppConfig.getPath();
        GlobalCLass loaded = XMLStorage.decoder(path);
        
        if (loaded != null) {
            Utilisateur userActuel = loaded.getListUtilisateur().get(String.valueOf(user.getId()));
            HistoriqueCommande panier = userActuel.getPanier();
            
            if (panier == null) {
                panier = new HistoriqueCommande();
                panier.setUtilisateur(userActuel);
                userActuel.setPanier(panier);
            }
            
            // Recalculer le total
            panier.setTotal(panier.calculerTotal(loaded));
            
            request.setAttribute("panier", panier);
            request.setAttribute("catalogue", loaded.getCatalogue());
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("panier.jsp");
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
