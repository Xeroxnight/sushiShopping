package metier;

import java.io.Serializable;
import java.util.Date;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private int idUtilisateur;
    
    @JsonbDateFormat("yyyy-MM-dd")
    private Date date;
    
    @JsonbDateFormat("HH:mm")
    private Date heure;
    
    private int tableNumero;
    private int nbPersonnes;
    private StatutReservation statut;

    public Reservation() {}

    // Getters et Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    // Surcharge pour String
    public void setDate(String dateStr) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            this.date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getHeure() { return heure; }
    public void setHeure(Date heure) { this.heure = heure; }
    
    // Surcharge pour String
    public void setHeure(String heureStr) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
            this.heure = sdf.parse(heureStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTableNumero() { return tableNumero; }
    public void setTableNumero(int tableNumero) { this.tableNumero = tableNumero; }

    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int nbPersonnes) { this.nbPersonnes = nbPersonnes; }

    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
}