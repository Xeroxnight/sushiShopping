package metier;

import java.io.*;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Path;

@Path("/api")
@Singleton
public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String xmlPath;
    //private static final String CONFIG_FILE = "WEB-INF\\appConfig.xml";
    //private static final String CONFIG_FILE = "WEB-INF" + File.separator + "appConfig.xml";

    // Constructeur public vide OBLIGATOIRE pour XMLDecoder
    public AppConfig() {
        // Valeur par défaut
        this.xmlPath = "C:\\Users\\timeo\\Desktop\\Nouveau dossier\\bdd.xml";
    }
    
    // Getter obligatoire pour XMLEncoder
    public String getXmlPath() {
        return this.xmlPath;
    }
    
    // Setter obligatoire pour XMLEncoder
    public void setXmlPath(String path) {
        this.xmlPath = path;
    }
    
    public static void setPath(String path) {
        AppConfig configFile = XMLStorage.decoderConfigFile(CONFIG_FILE);
        if (configFile == null) {
            configFile = new AppConfig();
        }
        configFile.setXmlPath(path);
        XMLStorage.encoderConfigFile(configFile, CONFIG_FILE);
    }
    
    public static String getPath() {
        AppConfig configFile = XMLStorage.decoderConfigFile(CONFIG_FILE);
        if (configFile == null) {
            configFile = new AppConfig();
        }
        return configFile.getXmlPath();
    }
    
    
    private static String getConfigFilePath() {
        String path = AppConfig.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {}
        
        File f = new File(path);
        // Remonter de classes/ vers WEB-INF
        if (f.getName().equals("classes")) {
            f = f.getParentFile();
        }
        
        return f.getAbsolutePath() + File.separator + "appConfig.xml";
    }

    private static final String CONFIG_FILE = getConfigFilePath();
}