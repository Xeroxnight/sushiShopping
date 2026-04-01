package metier;

public class Utilisateur {

    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private String adresse;
    private String telephone;
    private int pointsFidelite;
    private HistoriqueCommande panier;  // Le panier actuel de l'utilisateur

    public Utilisateur() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public int getPointsFidelite() { return pointsFidelite; }
    public void setPointsFidelite(int pointsFidelite) { this.pointsFidelite = pointsFidelite; }

    public HistoriqueCommande getPanier() { return panier; }
    public void setPanier(HistoriqueCommande panier) { this.panier = panier; }
}