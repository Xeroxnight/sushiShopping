package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalCLass {

    private HashMap<String, Utilisateur> listUtilisateur = new HashMap<>();
    private HashMap<Integer, Produit> catalogue = new HashMap<>();

    public HashMap<String, Utilisateur> getListUtilisateur() {
        return listUtilisateur;
    }

    public void setListUtilisateur(HashMap<String, Utilisateur> listUtilisateur) {
        this.listUtilisateur = listUtilisateur;
    }

    public HashMap<Integer, Produit> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(HashMap<Integer, Produit> catalogue) {
        this.catalogue = catalogue;
    }
    
    public List<Sushi> getSushis() {
        List<Sushi> res = new ArrayList<>();
        for (Produit p : catalogue.values()) {
            if (p instanceof Sushi) {
                res.add((Sushi) p);
            }
        }
        return res;
    }
    
    public GlobalCLass() {}
}