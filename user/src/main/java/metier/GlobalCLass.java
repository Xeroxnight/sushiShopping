package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	 
	 
	 
	 
	 // ajouter sa dans adminpage(pas obligatoire)
	// Ajouter un produit au panier de l'utilisateur
	 @POST
	 @Path("/panier/ajouter")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void ajouterAuPanier(HistoriqueCommande commande) {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     
	     // Récupérer l'utilisateur de la commande
	     Utilisateur user = commande.getUtilisateur();
	     if (user != null && glob.getListUtilisateur().containsKey(String.valueOf(user.getId()))) {
	         Utilisateur utilisateurExistant = glob.getListUtilisateur().get(String.valueOf(user.getId()));
	         HistoriqueCommande panier = utilisateurExistant.getPanier();
	         
	         if (panier == null) {
	             panier = new HistoriqueCommande();
	             panier.setUtilisateur(utilisateurExistant);
	             panier.setStatut(StatutCommande.EN_ATTENTE);
	             utilisateurExistant.setPanier(panier);
	         }
	         
	         // Ajouter les produits de la commande au panier
	         if (commande.getSushis() != null) {
	             if (panier.getSushis() == null) panier.setSushis(new ArrayList<>());
	             panier.getSushis().addAll(commande.getSushis());
	         }
	         
	         if (commande.getBoissons() != null) {
	             if (panier.getBoissons() == null) panier.setBoissons(new ArrayList<>());
	             panier.getBoissons().addAll(commande.getBoissons());
	         }
	         
	         if (commande.getEnsembles() != null) {
	             if (panier.getEnsembles() == null) panier.setEnsembles(new ArrayList<>());
	             panier.getEnsembles().addAll(commande.getEnsembles());
	         }
	         
	         if (commande.getFormules() != null) {
	             if (panier.getFormules() == null) panier.setFormules(new ArrayList<>());
	             panier.getFormules().addAll(commande.getFormules());
	         }
	         
	         // Recalculer le total
	         panier.setTotal(panier.calculerTotal(glob));
	         
	         // Sauvegarder
	         XMLStorage.encoder(glob, path);
	     }
	 }
	 
	// Récupérer une commande par son ID
	 public HistoriqueCommande getCommandeById(int id) {
	     return listeCommandes.get(id);
	 }

	 // Ajouter une commande à l'historique et vider le panier
	 @POST
	 @Path("/panier/payer")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void payerPanier(Utilisateur user) {
	     String path = AppConfig.getPath();
	     GlobalCLass glob = XMLStorage.decoder(path);
	     
	     Utilisateur utilisateurExistant = glob.getListUtilisateur().get(String.valueOf(user.getId()));
	     HistoriqueCommande panier = utilisateurExistant.getPanier();
	     
	     if (panier != null && !estPanierVide(panier)) {
	         // Générer un ID unique pour la commande
	         int newId = 1;
	         for (Integer id : glob.listeCommandes.keySet()) {
	             if (id >= newId) newId = id + 1;
	         }
	         
	         // Créer une copie de la commande
	         HistoriqueCommande commande = new HistoriqueCommande();
	         commande.setIdCommande(newId);
	         commande.setUtilisateur(utilisateurExistant);
	         commande.setSushis(panier.getSushis() != null ? new ArrayList<>(panier.getSushis()) : null);
	         commande.setBoissons(panier.getBoissons() != null ? new ArrayList<>(panier.getBoissons()) : null);
	         commande.setEnsembles(panier.getEnsembles() != null ? new ArrayList<>(panier.getEnsembles()) : null);
	         commande.setFormules(panier.getFormules() != null ? new ArrayList<>(panier.getFormules()) : null);
	         commande.setDateCommande(new Date());
	         commande.setTotal(panier.getTotal());
	         commande.setStatut(StatutCommande.EN_ATTENTE);
	         
	         // Ajouter à l'historique
	         glob.listeCommandes.put(newId, commande);
	         
	         // Vider le panier
	         utilisateurExistant.setPanier(new HistoriqueCommande());
	         utilisateurExistant.getPanier().setUtilisateur(utilisateurExistant);
	         
	         
	      // Avant de sauvegarder
	         System.out.println("=== PAIEMENT ===");
	         System.out.println("Nouvelle commande ID: " + newId);
	         System.out.println("Nombre de commandes avant: " + glob.listeCommandes.size());
	         glob.listeCommandes.put(newId, commande);
	         System.out.println("Nombre de commandes après: " + glob.listeCommandes.size());
	         System.out.println("Sauvegarde dans: " + path);
	         XMLStorage.encoder(glob, path);
	         System.out.println("=== PAIEMENT TERMINÉ ===");
	         
	         // Sauvegarder
	         //XMLStorage.encoder(glob, path);
	     }
	 }

	 // Vérifier si le panier est vide
	 private boolean estPanierVide(HistoriqueCommande panier) {
	     boolean sushiVide = panier.getSushis() == null || panier.getSushis().isEmpty();
	     boolean boissonsVide = panier.getBoissons() == null || panier.getBoissons().isEmpty();
	     boolean ensemblesVide = panier.getEnsembles() == null || panier.getEnsembles().isEmpty();
	     boolean formulesVide = panier.getFormules() == null || panier.getFormules().isEmpty();
	     return sushiVide && boissonsVide && ensemblesVide && formulesVide;
	 }
	 
	 public HashMap<Integer, HistoriqueCommande> getListeCommandes() {
		    return listeCommandes;
		}

		public void setListeCommandes(HashMap<Integer, HistoriqueCommande> listeCommandes) {
		    this.listeCommandes = listeCommandes;
		}
	 
    
    public GlobalCLass() {}
}








