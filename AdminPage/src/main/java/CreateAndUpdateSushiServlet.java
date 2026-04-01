

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.GlobalCLass;
import metier.Sushi;
import metier.XMLStorage;

import java.io.IOException;

/**
 * Servlet implementation class CreateSushiServlet
 */
@WebServlet("/CreateAndUpdateSushiServlet")
public class CreateAndUpdateSushiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAndUpdateSushiServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		try {
            String action = request.getParameter("action");
            
            // récupération des données
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            String image = request.getParameter("image");
            
            Sushi sushi = new Sushi();
            sushi.setId(id);
            sushi.setNom(nom);
            sushi.setPrix(prix);
            sushi.setImage(image != null ? image : "");
            
            GlobalCLass global = new GlobalCLass();
            
            // Si c'est une modification et que l'ID a changé, supprimer l'ancien
            if ("update".equals(action)) {
                String originalId = request.getParameter("originalId");
                if (originalId != null && !originalId.equals(String.valueOf(id))) {
                    Sushi oldSushi = new Sushi();
                    oldSushi.setId(Integer.parseInt(originalId));
                    global.deleteInCatalog(oldSushi);
                }
            }
            
            global.putInCatalogRest(sushi);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CatalogueSushi");
    }

}
