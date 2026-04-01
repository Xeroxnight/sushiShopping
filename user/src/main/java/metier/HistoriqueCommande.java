package metier;

import java.util.Date;
import java.util.List;

public class HistoriqueCommande {

    private int idCommande;
    private Utilisateur utilisateur;
    
    // Listes d'IDs pour chaque type de produit
    private List<Integer> sushis;      // IDs des sushis
    private List<Integer> boissons;    // IDs des boissons
    private List<Integer> ensembles;   // IDs des ensembles
    private List<Integer> formules;    // IDs des formules
    
    private Date dateCommande;
    private double total;
    private StatutCommande statut;

    public HistoriqueCommande() {}

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Integer> getSushis() {
        return sushis;
    }

    public void setSushis(List<Integer> sushis) {
        this.sushis = sushis;
    }

    public List<Integer> getBoissons() {
        return boissons;
    }

    public void setBoissons(List<Integer> boissons) {
        this.boissons = boissons;
    }

    public List<Integer> getEnsembles() {
        return ensembles;
    }

    public void setEnsembles(List<Integer> ensembles) {
        this.ensembles = ensembles;
    }

    public List<Integer> getFormules() {
        return formules;
    }

    public void setFormules(List<Integer> formules) {
        this.formules = formules;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }
    
    // Méthode utilitaire pour calculer le total à partir des produits
    public double calculerTotal(GlobalCLass catalogue) {
        double total = 0;
        
        // Sushis
        if (sushis != null) {
            for (Integer id : sushis) {
                Produit p = catalogue.getCatalogue().get(id);
                if (p != null) total += p.getPrix();
            }
        }
        
        // Boissons
        if (boissons != null) {
            for (Integer id : boissons) {
                Produit p = catalogue.getCatalogue().get(id);
                if (p != null) total += p.getPrix();
            }
        }
        
        // Ensembles
        if (ensembles != null) {
            for (Integer id : ensembles) {
                Produit p = catalogue.getCatalogue().get(id);
                if (p != null) total += p.getPrix();
            }
        }
        
        // Formules
        if (formules != null) {
            for (Integer id : formules) {
                Produit p = catalogue.getCatalogue().get(id);
                if (p != null) total += p.getPrix();
            }
        }
        
        return total;
    }
}