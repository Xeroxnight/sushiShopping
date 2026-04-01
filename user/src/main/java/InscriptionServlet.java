

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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("inscription.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupération des données
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        
        // Charger la BDD
        String path = metier.AppConfig.getPath();
        GlobalCLass loaded = XMLStorage.decoder(path);
        
        // Vérifier si l'email existe déjà
        boolean emailExiste = false;
        if (loaded != null && loaded.getListUtilisateur() != null) {
            for (Utilisateur u : loaded.getListUtilisateur().values()) {
                if (u.getEmail() != null && u.getEmail().equals(email)) {
                    emailExiste = true;
                    break;
                }
            }
        }
        
        if (emailExiste) {
            request.setAttribute("erreur", "Cet email est déjà utilisé");
            request.getRequestDispatcher("inscription.jsp").forward(request, response);
            return;
        }
        
        // Générer un ID unique <= a revoire car peut être vite gourmant 
        int newId = 1;
        if (loaded != null && loaded.getListUtilisateur() != null && !loaded.getListUtilisateur().isEmpty()) {
            // Trouver l'ID max
            for (Utilisateur u : loaded.getListUtilisateur().values()) {
                if (u.getId() >= newId) {
                    newId = u.getId() + 1;
                }
            }
        }
        
        // Créer l'utilisateur
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setId(newId);
        nouvelUtilisateur.setNom(nom);
        nouvelUtilisateur.setEmail(email);
        nouvelUtilisateur.setMotDePasse(motDePasse);
        nouvelUtilisateur.setAdresse(adresse != null ? adresse : "");
        nouvelUtilisateur.setTelephone(telephone != null ? telephone : "");
        nouvelUtilisateur.setPointsFidelite(0);
        nouvelUtilisateur.setPanier(new HistoriqueCommande()); // Panier vide
        
        // Sauvegarder dans la BDD
        if (loaded == null) {
            loaded = new GlobalCLass();
        }
        loaded.getListUtilisateur().put(String.valueOf(newId), nouvelUtilisateur);
        XMLStorage.encoder(loaded, path);
        
        // Connecter automatiquement
        HttpSession session = request.getSession();
        session.setAttribute("utilisateur", nouvelUtilisateur);
        
        // Rediriger vers l'accueil
        response.sendRedirect("accueil");
	}

}
