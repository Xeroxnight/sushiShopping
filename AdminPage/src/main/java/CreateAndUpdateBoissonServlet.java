

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Boisson;
import metier.GlobalCLass;

import java.io.IOException;

/**
 * Servlet implementation class CreateAndUpdateBoissonServlet
 */
@WebServlet("/CreateAndUpdateBoissonServlet")
public class CreateAndUpdateBoissonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAndUpdateBoissonServlet() {
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
		//doGet(request, response);
		try {
            // récupération des données
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            
            // Création de l'objet Boisson
            Boisson b = new Boisson();
            b.setId(id);
            b.setNom(nom);
            b.setPrix(prix);
            b.setDescription(description);
            b.setImage(image);
            
            // Appel à la méthode REST pour sauvegarder
            GlobalCLass global = new GlobalCLass(); 
            global.putBoissonInCatalogRest(b);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirection vers le catalogue des boissons
        response.sendRedirect("CatalogueBoisson");
    }
	

}
