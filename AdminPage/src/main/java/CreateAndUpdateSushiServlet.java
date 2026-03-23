

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
            // récupération données
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            GlobalCLass global = new GlobalCLass();
            Sushi s = new Sushi();
            s.setId(id);
            s.setNom(nom);
            s.setPrix(prix);
            global.putInCatalogRest(s);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirection vers catalogue
        response.sendRedirect("CatalogueSushi");
    }

}
