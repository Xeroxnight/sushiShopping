

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.GlobalCLass;
import metier.Utilisateur;
import metier.XMLStorage;

import java.io.IOException;

/**
 * Servlet implementation class CreateAndUpdateUtilisateurServlet
 */
@WebServlet("/CreateAndUpdateUtilisateurServlet")
public class CreateAndUpdateUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAndUpdateUtilisateurServlet() {
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
            String action = request.getParameter("action");
            
            // récupération des données
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            int pointsFidelite = Integer.parseInt(request.getParameter("pointsFidelite"));
            
            // Création de l'objet Utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(id);
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setAdresse(adresse);
            utilisateur.setTelephone(telephone);
            utilisateur.setPointsFidelite(pointsFidelite);
            
            // Si c'est une modification et que le mot de passe est vide, on garde l'ancien
            if ("update".equals(action) && (motDePasse == null || motDePasse.trim().isEmpty())) {
                String path = AppConfig.getPath();
                GlobalCLass loaded = XMLStorage.decoder(path);
                Utilisateur existing = loaded.getListUtilisateur().get(String.valueOf(id));
                if (existing != null) {
                    utilisateur.setMotDePasse(existing.getMotDePasse());
                }
            } else {
                utilisateur.setMotDePasse(motDePasse);
            }
            
            // Appel à la méthode REST pour sauvegarder
            GlobalCLass global = new GlobalCLass();
            
            // Si c'est une modification et que l'ID a changé, supprimer l'ancien
            if ("update".equals(action)) {
                String originalId = request.getParameter("originalId");
                if (originalId != null && !originalId.equals(String.valueOf(id))) {
                    Utilisateur oldUser = new Utilisateur();
                    oldUser.setId(Integer.parseInt(originalId));
                    global.deleteUtilisateurInCatalogRest(oldUser);
                }
            }
            
            global.putUtilisateurInCatalogRest(utilisateur);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CatalogueUtilisateur");
	}

}
