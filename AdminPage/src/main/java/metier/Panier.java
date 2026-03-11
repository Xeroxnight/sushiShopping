package metier;

import java.util.Map;

public class Panier {

    private Map<Produit, Integer> produits;
    private double prixTotal;

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(Map<Produit, Integer> produits) {
        this.produits = produits;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
