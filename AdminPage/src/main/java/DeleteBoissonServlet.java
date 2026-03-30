

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Boisson;
import metier.GlobalCLass;

import java.io.IOException;

/**
 * Servlet implementation class DeleteBoissonServlet
 */
@WebServlet("/DeleteBoissonServlet")
public class DeleteBoissonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoissonServlet() {
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
            // récupération de l'id
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Création d'un objet Boisson temporaire avec seulement l'id
            Boisson b = new Boisson();
            b.setId(id);
            
            // Appel à la méthode REST pour supprimer
            GlobalCLass global = new GlobalCLass();
            global.deleteBoissonInCatalogRest(b);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirection vers le catalogue des boissons
        response.sendRedirect("CatalogueBoisson");
	}

}
