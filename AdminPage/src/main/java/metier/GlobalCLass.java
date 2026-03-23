package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sushi")
@Singleton
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
    
    public void updateInCatalog(Sushi s)
    {
    	String path = AppConfig.getInstance().getXmlPath();
    	GlobalCLass glob = XMLStorage.decoder(path);
        catalogue.put(s.getId(), s);
        XMLStorage.encoder(glob, path);
    }
    
    @POST
    @Path("/createAndUpdate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putInCatalogRest(Sushi s)
    {
    	String path = AppConfig.getInstance().getXmlPath();
    	GlobalCLass glob = XMLStorage.decoder(path);
    	glob.getCatalogue().put(s.getId(), s);
    	XMLStorage.encoder(glob, path);
    }
    
    
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteInCatalog(Sushi s)
    {
    	String path = AppConfig.getInstance().getXmlPath();
        GlobalCLass global = XMLStorage.decoder(path);
    	global.getCatalogue().remove(s.getId());
    	XMLStorage.encoder(global, path);
    }
    
    public GlobalCLass() {}
}








