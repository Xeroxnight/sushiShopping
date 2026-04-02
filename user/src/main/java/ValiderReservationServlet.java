

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GlobalCLass;
import metier.Reservation;
import metier.StatutReservation;
import metier.Utilisateur;
import metier.XMLStorage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Servlet implementation class ValiderReservationServlet
 */
@WebServlet("/validerReservation")
public class ValiderReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderReservationServlet() {
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
		HttpSession session = request.getSession();
	    Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
	    
	    if (user == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
	    
	    try {
	        String dateStr = request.getParameter("date");
	        String heureStr = request.getParameter("heure");
	        int tableNumero = Integer.parseInt(request.getParameter("tableNumero"));
	        int nbPersonnes = Integer.parseInt(request.getParameter("nbPersonnes"));
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");

	        Date date = dateFormat.parse(dateStr);
	        Date heure = heureFormat.parse(heureStr);
	        
	        String path = metier.AppConfig.getPath();
	        GlobalCLass glob = XMLStorage.decoder(path);
	        
	        // Vérifier si la table est disponible
	        if (!glob.isTableDisponible(date, heure, tableNumero)) {
	            session.setAttribute("erreur", "Cette table n'est plus disponible pour ce créneau");
	            response.sendRedirect("reservation");
	            return;
	        }
	        
	        Reservation reservation = new Reservation();
	        reservation.setDate(date);
	        reservation.setHeure(heure);
	        reservation.setTableNumero(tableNumero);
	        reservation.setNbPersonnes(nbPersonnes);

	        // Utilise la même instance glob, pas un new GlobalCLass()
	        glob.validerReservationEtPayer(reservation, user);

	        session.setAttribute("message", "Réservation confirmée !");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.setAttribute("erreur", "Erreur lors de la réservation");
	    }
	    
	    response.sendRedirect("reservation");
        
	}

}
