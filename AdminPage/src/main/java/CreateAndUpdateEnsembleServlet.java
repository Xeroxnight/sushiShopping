

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
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class CreateAndUpdateEnsembleServlet
 */
@WebServlet("/CreateAndUpdateEnsembleServlet")
public class CreateAndUpdateEnsembleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAndUpdateEnsembleServlet() {
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
            
            // récupération des données de base
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            
            // Récupération des sushis et quantités
            String[] sushiIds = request.getParameterValues("sushiIds");
            String[] quantites = request.getParameterValues("quantites");
            
            Map<Integer, Integer> sushisMap = new HashMap<>();
            
            if (sushiIds != null && quantites != null) {
                for (int i = 0; i < sushiIds.length; i++) {
                    if (sushiIds[i] != null && !sushiIds[i].isEmpty()) {
                        int sushiId = Integer.parseInt(sushiIds[i]);
                        int quantite = Integer.parseInt(quantites[i]);
                        sushisMap.put(sushiId, quantite);
                    }
                }
            }
            
            // Création de l'objet Ensemble
            Ensemble ensemble = new Ensemble();
            ensemble.setId(id);
            ensemble.setNom(nom);
            ensemble.setPrix(prix);
            ensemble.setDescription(description);
            ensemble.setImage(image);
            ensemble.setSushis(sushisMap);
            
            // Appel à la méthode REST pour sauvegarder
            GlobalCLass global = new GlobalCLass();
            
            // Si c'est une modification et que l'ID a changé, supprimer l'ancien
            if ("update".equals(action)) {
                String originalId = request.getParameter("originalId");
                if (originalId != null && !originalId.equals(String.valueOf(id))) {
                    Ensemble oldEnsemble = new Ensemble();
                    oldEnsemble.setId(Integer.parseInt(originalId));
                    global.deleteEnsembleInCatalogRest(oldEnsemble);
                }
            }
            
            global.putEnsembleInCatalogRest(ensemble);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirection vers le catalogue des ensembles
        response.sendRedirect("CatalogueEnsemble");
    }
    
	

}
