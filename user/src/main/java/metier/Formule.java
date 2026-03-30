package metier;

import java.util.List;

public class Formule implements Produit {

    private int id;
    private String nom;
    private double prix;
    private String description;
    private String image;

    private List<Integer> sushis;      // Liste des IDs de sushis
    private List<Integer> boissons;    // Liste des IDs de boissons
    private List<Integer> ensembles;   // Liste des IDs d'ensembles

    public Formule() {}

    public Formule(int id, String nom, double prix, String description, String image, 
                   List<Integer> sushis, List<Integer> boissons, List<Integer> ensembles) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.sushis = sushis;
        this.boissons = boissons;
        this.ensembles = ensembles;
    }

    public List<Integer> getSushis() { return sushis; }
    public void setSushis(List<Integer> sushis) { this.sushis = sushis; }
    
    public List<Integer> getBoissons() { return boissons; }
    public void setBoissons(List<Integer> boissons) { this.boissons = boissons; }
    
    public List<Integer> getEnsembles() { return ensembles; }
    public void setEnsembles(List<Integer> ensembles) { this.ensembles = ensembles; }

    @Override
    public int getId() { return id; }
    @Override
    public void setId(int id) { this.id = id; }

    @Override
    public String getNom() { return nom; }
    @Override
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public double getPrix() { return prix; }
    @Override
    public void setPrix(double prix) { this.prix = prix; }

    @Override
    public String getDescription() { return description; }
    @Override
    public void setDescription(String description) { this.description = description; }

    @Override
    public String getImage() { return image; }
    @Override
    public void setImage(String image) { this.image = image; }
}