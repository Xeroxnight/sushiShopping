package metier;

import java.util.List;

public class Formule implements Produit {

    private int id;
    private String nom;
    private double prix;
    private String description;
    private String image;

    private List<Produit> produits;

    public Formule() {}

    public Formule(int id, String nom, double prix, String description, String image, List<Produit> produits) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.produits = produits;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
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
