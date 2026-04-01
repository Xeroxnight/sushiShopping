

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.AppConfig;
import metier.XMLStorage;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(value = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
        super.init();  // Maintenant pas d'erreur
        
        //System.out.println("=== InitServlet démarre ===");
        
     // 1. Récupérer le chemin d'exécution de Tomcat
        String cheminTomcat = getServletContext().getRealPath("/");
        
        // 2. Construire le chemin complet du fichier de config
        String cheminConfig = cheminTomcat + "WEB-INF/appConfig.xml";
        
        // 3. Créer le fichier s'il n'existe pas
        File fichier = new File(cheminConfig);
        if (!fichier.exists()) {
            fichier.getParentFile().mkdirs();
            
            // 4. Créer AppConfig par défaut
            AppConfig config = new AppConfig();
            
            // 5. Sauvegarder
            XMLStorage.encoderConfigFile(config, cheminConfig);
        }
        //System.out.println("=== InitServlet terminé ===");
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
	}

}
