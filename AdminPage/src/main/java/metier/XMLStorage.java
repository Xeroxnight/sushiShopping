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

            System.out.println("Catalogue chargé depuis : " + chemin);

        } catch (Exception e) {

            System.out.println("Impossible de lire le fichier XML");
            e.printStackTrace();

        }

        return data;
    }
}