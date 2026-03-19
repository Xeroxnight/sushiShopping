package metier;

public class Sushi implements Produit {

    private int id;
    private String nom;
    private double prix;
    private String description;
    private String image;

    private int nbPieces;

    public Sushi() {}

    public Sushi(int id, String nom, double prix, String description, String image, int nbPieces) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.nbPieces = nbPieces;
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
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
