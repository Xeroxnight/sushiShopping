package metier;

import java.util.HashMap;

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
    
    public GlobalCLass() {}
}