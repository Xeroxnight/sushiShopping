package metier;

import java.io.*;

public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    private static AppConfig instance;

    private String xmlPath;

    // private static final String CONFIG_FILE = "C:\\Users\\timeo\\Desktop\\Nouveau dossier\\appConfig.ser";
    private static final String CONFIG_FILE = "WEB-INF/appConfig.ser";

    // constructeur privé
    private AppConfig() {
        // valeur par défaut si fichier pas trouvé
        xmlPath = "C:\\Users\\timeo\\Desktop\\Nouveau dossier\\bdd.xml";
    }

    // instance unique
    public static AppConfig getInstance() {
        if (instance == null) {
            // essayer de charger depuis fichier
            File f = new File(CONFIG_FILE);
            if (f.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    instance = (AppConfig) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                    instance = new AppConfig();
                }
            } else {
                instance = new AppConfig();
            }
        }
        return instance;
    }

    // getter / setter
    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
        save(); // sauvegarde automatiquement
    }

    // méthode de persistance
    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}