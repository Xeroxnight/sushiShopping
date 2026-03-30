

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.Boisson;
import metier.Ensemble;
import metier.Formule;
import metier.GlobalCLass;
import metier.Produit;
import metier.Sushi;
import metier.XMLStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CreateAndUpdateFormuleServlet
 */
@WebServlet("/CreateAndUpdateFormuleServlet")
public class CreateAndUpdateFormuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAndUpdateFormuleServlet() {
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
            
            // Récupération des IDs par type
            String[] sushiIds = request.getParameterValues("sushiIds");
            String[] boissonIds = request.getParameterValues("boissonIds");
            String[] ensembleIds = request.getParameterValues("ensembleIds");
            
            // Stocker uniquement les IDs (int)
            List<Integer> sushisList = new ArrayList<>();
            if (sushiIds != null) {
                for (String idStr : sushiIds) {
                    if (idStr != null && !idStr.isEmpty()) {
                        try {
                            sushisList.add(Integer.parseInt(idStr));
                        } catch(NumberFormatException e) {}
                    }
                }
            }
            
            List<Integer> boissonsList = new ArrayList<>();
            if (boissonIds != null) {
                for (String idStr : boissonIds) {
                    if (idStr != null && !idStr.isEmpty()) {
                        try {
                            boissonsList.add(Integer.parseInt(idStr));
                        } catch(NumberFormatException e) {}
                    }
                }
            }
            
            List<Integer> ensemblesList = new ArrayList<>();
            if (ensembleIds != null) {
                for (String idStr : ensembleIds) {
                    if (idStr != null && !idStr.isEmpty()) {
                        try {
                            ensemblesList.add(Integer.parseInt(idStr));
                        } catch(NumberFormatException e) {}
                    }
                }
            }
            
            // Création de l'objet Formule avec uniquement les IDs
            Formule formule = new Formule();
            formule.setId(id);
            formule.setNom(nom);
            formule.setPrix(prix);
            formule.setDescription(description);
            formule.setImage(image);
            formule.setSushis(sushisList);
            formule.setBoissons(boissonsList);
            formule.setEnsembles(ensemblesList);
            
            // Appel à la méthode REST pour sauvegarder
            GlobalCLass global = new GlobalCLass();
            
            if ("update".equals(action)) {
                String originalId = request.getParameter("originalId");
                if (originalId != null && !originalId.equals(String.valueOf(id))) {
                    Formule oldFormule = new Formule();
                    oldFormule.setId(Integer.parseInt(originalId));
                    global.deleteFormuleInCatalogRest(oldFormule);
                }
            }
            
            global.putFormuleInCatalogRest(formule);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CatalogueFormule");
	}

}
