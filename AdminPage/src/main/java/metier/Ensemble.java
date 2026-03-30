package metier;

import java.util.Map;

public class Ensemble implements Produit {

    private int id;
    private String nom;
    private double prix;
    private String description;
    private String image;

    private Map<Integer, Integer> sushis;  // ID du sushi -> quantité

    public Ensemble() {}

    public Ensemble(int id, String nom, double prix, String description, String image, Map<Integer, Integer> sushis) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.sushis = sushis;
    }

    public Map<Integer, Integer> getSushis() {
        return sushis;
    }

    public void setSushis(Map<Integer, Integer> sushis) {
        this.sushis = sushis;
    }

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