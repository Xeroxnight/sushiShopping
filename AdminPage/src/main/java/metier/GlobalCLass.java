package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.User;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Singleton
public class GlobalCLass {

    private HashMap<String, Utilisateur> listUtilisateur = new HashMap<>();
    private HashMap<Integer, Produit> catalogue = new HashMap<>();
    private HashMap<Integer, HistoriqueCommande> listeCommandes = new HashMap<>();

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
    
    public void updateInCatalog(Sushi s)//inutile ? teste de degager sa
    {
    	String path = AppConfig.getPath();
    	GlobalCLass glob = XMLStorage.decoder(path);
        catalogue.put(s.getId(), s);
        XMLStorage.encoder(glob, path);
    }
    
    @POST
    @Path("/sushi/createAndUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putInCatalogRest(Sushi s)
    {
    	String path = AppConfig.getPath();
    	GlobalCLass glob = XMLStorage.decoder(path);
    	glob.getCatalogue().put(s.getId(), s);
    	XMLStorage.encoder(glob, path);
    }
    
    
    @POST
    @Path("/sushi/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteInCatalog(Sushi s)
    {
    	String path = AppConfig.getPath();
        GlobalCLass global = XMLStorage.decoder(path);
    	global.getCatalogue().remove(s.getId());
    	XMLStorage.encoder(global, path);
    }
    
    public List<Boisson> getBoissons() {
        List<Boisson> res = new ArrayList<>();
        for (Produit p : catalogue.values()) {
            if (p instanceof Boisson) {
                res.add((Boisson) p);
            }
        }
        return res;
    }
    
    public List<Utilisateur> getUsers()
    {
    	List<Utilisateur> res = new ArrayList<>();
        for (Utilisateur p : listUtilisateur.values()) {
            if (p instanceof Utilisateur) {
                res.add((Utilisateur) p);
            }
        }
        return res;
    }
    
    @POST
    @Path("/utilisateur/createAndUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putUtilisateurInCatalogRest(Utilisateur u) {
        String path = AppConfig.getPath();
        GlobalCLass glob = XMLStorage.decoder(path);
        glob.getListUtilisateur().put(String.valueOf(u.getId()), u);
        XMLStorage.encoder(glob, path);
    }

    // Méthode REST pour supprimer un utilisateur
    @POST
    @Path("/utilisateur/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUtilisateurInCatalogRest(Utilisateur u) {
        String path = AppConfig.getPath();
        GlobalCLass glob = XMLStorage.decoder(path);
        glob.getListUtilisateur().remove(String.valueOf(u.getId()));
        XMLStorage.encoder(glob, path);
    }
    

	 // Méthode REST pour créer/modifier une boisson
	 @POST
	 @Path("/boisson/createAndUpdate")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void putBoissonInCatalogRest(Boisson b)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().put(b.getId(), b);
	     XMLStorage.encoder(glob, path);
	 }
	
	 // Méthode REST pour supprimer une boisson
	 @POST
	 @Path("/boisson/delete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteBoissonInCatalogRest(Boisson b)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().remove(b.getId());
	     XMLStorage.encoder(glob, path);
	 }
	 
	// Dans GlobalCLass.java, ajoutez cette méthode pour récupérer les ensembles
	 public List<Ensemble> getEnsembles() {
	     List<Ensemble> res = new ArrayList<>();
	     for (Produit p : catalogue.values()) {
	         if (p instanceof Ensemble) {
	             res.add((Ensemble) p);
	         }
	     }
	     return res;
	 }

	 // Méthode REST pour créer/modifier un ensemble
	 @POST
	 @Path("/ensemble/createAndUpdate")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void putEnsembleInCatalogRest(Ensemble e)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().put(e.getId(), e);
	     XMLStorage.encoder(glob, path);
	 }

	 // Méthode REST pour supprimer un ensemble
	 @POST
	 @Path("/ensemble/delete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteEnsembleInCatalogRest(Ensemble e)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().remove(e.getId());
	     XMLStorage.encoder(glob, path);
	 }
	 
	// Dans GlobalCLass.java, ajoutez cette méthode pour récupérer les formules
	 public List<Formule> getFormules() {
	     List<Formule> res = new ArrayList<>();
	     for (Produit p : catalogue.values()) {
	         if (p instanceof Formule) {
	             res.add((Formule) p);
	         }
	     }
	     return res;
	 }

	// Méthode REST pour créer/modifier une formule
	 @POST
	 @Path("/formule/createAndUpdate")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void putFormuleInCatalogRest(Formule formule)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().put(formule.getId(), formule);
	     XMLStorage.encoder(glob, path);
	 }

	 // Méthode REST pour supprimer une formule
	 @POST
	 @Path("/formule/delete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteFormuleInCatalogRest(Formule formule)
	 {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.getCatalogue().remove(formule.getId());
	     XMLStorage.encoder(glob, path);
	 }
	 
	 
	// Récupérer toutes les commandes
	 public List<HistoriqueCommande> getCommandes() {
	     List<HistoriqueCommande> res = new ArrayList<>();
	     for (HistoriqueCommande c : listeCommandes.values()) {
	         res.add(c);
	     }
	     return res;
	 }

	 // Méthode REST pour supprimer une commande
	 @POST
	 @Path("/commande/delete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void deleteCommandeInCatalogRest(HistoriqueCommande commande) {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     glob.listeCommandes.remove(commande.getIdCommande());
	     XMLStorage.encoder(glob, path);
	 }

	 // Méthode REST pour valider une commande (passer à LIVREE)
	 @POST
	 @Path("/commande/valider")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void validerCommandeInCatalogRest(HistoriqueCommande commande) {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     HistoriqueCommande cmd = glob.listeCommandes.get(commande.getIdCommande());
	     if (cmd != null) {
	         cmd.setStatut(StatutCommande.LIVREE);
	     }
	     XMLStorage.encoder(glob, path);
	 }
    
    public GlobalCLass() {}
}








