

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
            String action = request.getParameter("action");
            
            // récupération des données
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            
            Boisson boisson = new Boisson();
            boisson.setId(id);
            boisson.setNom(nom);
            boisson.setPrix(prix);
            boisson.setDescription(description != null ? description : "");
            boisson.setImage(image != null ? image : "");
            
            GlobalCLass global = new GlobalCLass();
            
            // Si c'est une modification et que l'ID a changé, supprimer l'ancien
            if ("update".equals(action)) {
                String originalId = request.getParameter("originalId");
                if (originalId != null && !originalId.equals(String.valueOf(id))) {
                    Boisson oldBoisson = new Boisson();
                    oldBoisson.setId(Integer.parseInt(originalId));
                    global.deleteBoissonInCatalogRest(oldBoisson);
                }
            }
            
            global.putBoissonInCatalogRest(boisson);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CatalogueBoisson");
    }
	

}
