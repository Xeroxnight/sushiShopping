

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
 * Servlet implementation class DeleteSushiServlet
 */
@WebServlet("/DeleteSushiServlet")
public class DeleteSushiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSushiServlet() {
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
		doGet(request, response);
		
		try {
            int id = Integer.parseInt(request.getParameter("id"));
            GlobalCLass global = new GlobalCLass();
            if (global != null) {
            	Sushi s = new Sushi();
                s.setId(id);
                global.deleteInCatalog(s);
            }
            response.sendRedirect("CatalogueSushi");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
		
	}

}
