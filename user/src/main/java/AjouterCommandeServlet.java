

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GlobalCLass;
import metier.HistoriqueCommande;
import metier.StatutCommande;
import metier.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AjouterCommandeServlet
 */
@WebServlet("/ajouterPanier")
public class AjouterCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterCommandeServlet() {
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
        
        // Vérifier si l'utilisateur est connecté
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            String type = request.getParameter("type");
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            
            // Créer une commande temporaire avec le produit
            HistoriqueCommande commande = new HistoriqueCommande();
            commande.setUtilisateur(user);
            commande.setStatut(StatutCommande.EN_ATTENTE);
            
            // Ajouter le produit dans la bonne liste
            switch (type) {
                case "sushi":
                    List<Integer> sushis = new ArrayList<>();
                    sushis.add(produitId);
                    commande.setSushis(sushis);
                    break;
                case "boisson":
                    List<Integer> boissons = new ArrayList<>();
                    boissons.add(produitId);
                    commande.setBoissons(boissons);
                    break;
                case "ensemble":
                    List<Integer> ensembles = new ArrayList<>();
                    ensembles.add(produitId);
                    commande.setEnsembles(ensembles);
                    break;
                case "formule":
                    List<Integer> formules = new ArrayList<>();
                    formules.add(produitId);
                    commande.setFormules(formules);
                    break;
            }
            
            // Appeler la méthode GlobalClass
            GlobalCLass global = new GlobalCLass();
            global.ajouterAuPanier(commande);
            
            // Rediriger vers le catalogue avec un message de succès
            session.setAttribute("message", "Produit ajouté au panier !");
            response.sendRedirect("catalogue");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("catalogue?erreur=Impossible d'ajouter au panier");
        }
	}

}
