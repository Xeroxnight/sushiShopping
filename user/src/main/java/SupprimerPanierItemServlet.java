

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
 * Servlet implementation class SupprimerPanierItemServlet
 */
@WebServlet("/supprimerPanierItem")
public class SupprimerPanierItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPanierItemServlet() {
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
		HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            String type = request.getParameter("type");
            int index = Integer.parseInt(request.getParameter("index"));
            
            String path = metier.AppConfig.getPath();
            GlobalCLass loaded = XMLStorage.decoder(path);
            
            Utilisateur userActuel = loaded.getListUtilisateur().get(String.valueOf(user.getId()));
            HistoriqueCommande panier = userActuel.getPanier();
            
            if (panier != null) {
                switch (type) {
                    case "sushi":
                        if (panier.getSushis() != null && index < panier.getSushis().size()) {
                            panier.getSushis().remove(index);
                        }
                        break;
                    case "boisson":
                        if (panier.getBoissons() != null && index < panier.getBoissons().size()) {
                            panier.getBoissons().remove(index);
                        }
                        break;
                    case "ensemble":
                        if (panier.getEnsembles() != null && index < panier.getEnsembles().size()) {
                            panier.getEnsembles().remove(index);
                        }
                        break;
                    case "formule":
                        if (panier.getFormules() != null && index < panier.getFormules().size()) {
                            panier.getFormules().remove(index);
                        }
                        break;
                }
                
                // Recalculer le total
                panier.setTotal(panier.calculerTotal(loaded));
                
                // Sauvegarder
                XMLStorage.encoder(loaded, path);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("panier");
	}

}
