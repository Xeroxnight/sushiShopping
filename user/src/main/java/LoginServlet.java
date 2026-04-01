

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GlobalCLass;
import metier.Utilisateur;
import metier.XMLStorage;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String email = request.getParameter("email");
	        String motDePasse = request.getParameter("motDePasse");
	        
	        String path = metier.AppConfig.getPath();
	        GlobalCLass loaded = XMLStorage.decoder(path);
	        
	        Utilisateur utilisateurTrouve = null;
	        
	        if (loaded != null && loaded.getListUtilisateur() != null) {
	            for (Utilisateur u : loaded.getListUtilisateur().values()) {
	                if (u.getEmail() != null && u.getEmail().equals(email)) {
	                    utilisateurTrouve = u;
	                    break;
	                }
	            }
	        }
	        
	        if (utilisateurTrouve != null && utilisateurTrouve.getMotDePasse().equals(motDePasse)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("utilisateur", utilisateurTrouve);
	            response.sendRedirect("accueil");
	        } else {
	            request.setAttribute("erreur", "Email ou mot de passe incorrect");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	}

}
