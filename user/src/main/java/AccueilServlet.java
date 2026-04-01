

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.GlobalCLass;
import metier.Produit;
import metier.XMLStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = metier.AppConfig.getPath();
        GlobalCLass loaded = XMLStorage.decoder(path);
        
        if (loaded != null) {
            // Récupérer tous les produits du catalogue
            List<Produit> tousLesProduits = new ArrayList<>(loaded.getCatalogue().values());
            
            if (!tousLesProduits.isEmpty()) {
                // 1. Premier produit (pack phare)
                Produit produitPhare = tousLesProduits.get(0);
                request.setAttribute("produitPhare", produitPhare);
                
                // 2. Deux produits aléatoires
                Random random = new Random();
                List<Produit> produitsAleatoires = new ArrayList<>();
                
                List<Produit> autres = new ArrayList<>(tousLesProduits);
                autres.remove(0);
                
                if (!autres.isEmpty()) {
                    int nbAleatoires = Math.min(2, autres.size());
                    for (int i = 0; i < nbAleatoires; i++) {
                        int index = random.nextInt(autres.size());
                        produitsAleatoires.add(autres.get(index));
                        autres.remove(index);
                    }
                }
                request.setAttribute("produitsAleatoires", produitsAleatoires);
                
                // 3. Produits pour le slider (3 produits aléatoires)
                List<Produit> sliderProduits = new ArrayList<>();
                List<Produit> tousSlider = new ArrayList<>(tousLesProduits);
                int nbSlider = Math.min(3, tousSlider.size());
                for (int i = 0; i < nbSlider; i++) {
                    int index = random.nextInt(tousSlider.size());
                    sliderProduits.add(tousSlider.get(index));
                    tousSlider.remove(index);
                }
                request.setAttribute("sliderProduits", sliderProduits);
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
