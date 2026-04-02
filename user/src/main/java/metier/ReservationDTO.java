package metier;

public class ReservationDTO {
    private int idUtilisateur;
    private String date;
    private String heure;
    private int tableNumero;
    private int nbPersonnes;
    private StatutReservation statut;
    
    public ReservationDTO() {}
    
    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }
    
    public int getTableNumero() { return tableNumero; }
    public void setTableNumero(int tableNumero) { this.tableNumero = tableNumero; }
    
    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int nbPersonnes) { this.nbPersonnes = nbPersonnes; }
    
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
}