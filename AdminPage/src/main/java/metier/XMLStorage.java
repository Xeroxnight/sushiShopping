package metier;



import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.beans.ExceptionListener;
import java.io.*;

import metier.GlobalCLass;

public class XMLStorage {

    public static void encoder(GlobalCLass data, String chemin) {

        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(chemin)))) {

            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Erreur pendant l'encodage XML");
                    e.printStackTrace();
                }
            });

            encoder.writeObject(data);

            System.out.println("Catalogue sauvegardé dans : " + chemin);

        } catch (Exception e) {
            System.out.println("Impossible d'écrire le fichier XML");
            e.printStackTrace();
        }
    }


    public static GlobalCLass decoder(String chemin) {

        GlobalCLass data = null;

        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(chemin)))) {

            data = (GlobalCLass) decoder.readObject();

            //System.out.println("Catalogue chargé depuis : " + chemin);

        } catch (Exception e) {

            System.out.println("Impossible de lire le fichier XML");
            e.printStackTrace();

        }

        return data;
    }
    
    
    public static void encoderConfigFile(AppConfig data, String chemin) {

        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(chemin)))) {

            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Erreur pendant l'encodage XML");
                    e.printStackTrace();
                }
            });

            encoder.writeObject(data);

            System.out.println("Catalogue sauvegardé dans : " + chemin);

        } catch (Exception e) {
            System.out.println("Impossible d'écrire le fichier XML");
            e.printStackTrace();
        }
    }

    
    public static AppConfig decoderConfigFile(String chemin) {
        
        // Afficher le chemin complet absolu
        File f = new File(chemin);
        
        // Lister le dossier parent
        File parent = f.getParentFile();
        if (parent != null && parent.exists()) {
            //System.out.println("Contenu du dossier " + parent.getAbsolutePath() + ":");
            String[] files = parent.list();
            if (files != null) {
            	/*
                for (String file : files) {
                    System.out.println("  - " + file);
                }*/
            }
        } else {
            System.out.println("Le dossier parent n'existe pas: " + (parent != null ? parent.getAbsolutePath() : "null"));
        }
        
        AppConfig data = null;
        
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(chemin)))) {
            data = (AppConfig) decoder.readObject();
        } catch (Exception e) {
            System.out.println("Impossible de lire le fichier XML");
            e.printStackTrace();
        }
        
        return data;
    }
    
    
    
    
    
}