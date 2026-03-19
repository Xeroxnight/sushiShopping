package metier;

public interface Produit {

    int getId();
    void setId(int id);

    String getNom();
    void setNom(String nom);

    double getPrix();
    void setPrix(double prix);

    String getDescription();
    void setDescription(String description);

    String getImage();
    void setImage(String image);
}
