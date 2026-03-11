package metier;

import java.util.*;

public class TestApplication {

    public static void main(String[] args) {

        System.out.println("=== TEST CREATION PRODUITS ===");

        Sushi sushi1 = new Sushi(1, "Sushi Saumon", 5.5, "Sushi au saumon", "img/saumon.jpg", 2);
        Sushi sushi2 = new Sushi(2, "Sushi Thon", 6.0, "Sushi au thon", "img/thon.jpg", 2);

        Boisson boisson1 = new Boisson(3, "Coca", 2.5, "Boisson gazeuse", "img/coca.jpg");

        System.out.println("Sushi 1 : " + sushi1.getNom() + " prix=" + sushi1.getPrix());
        System.out.println("Sushi 2 : " + sushi2.getNom() + " prix=" + sushi2.getPrix());
        System.out.println("Boisson : " + boisson1.getNom() + " prix=" + boisson1.getPrix());


        System.out.println("\n=== TEST FORMULE ===");

        List<Produit> produitsFormule = new ArrayList<>();
        produitsFormule.add(sushi1);
        produitsFormule.add(boisson1);

        Formule formuleMidi = new Formule(
                10,
                "Formule Midi",
                8.5,
                "Sushi + boisson",
                "img/formule.jpg",
                produitsFormule
        );

        System.out.println("Formule : " + formuleMidi.getNom());
        System.out.println("Nombre de produits dans la formule : " + formuleMidi.getProduits().size());


        System.out.println("\n=== TEST ENSEMBLE ===");

        Map<Sushi, Integer> plateau = new HashMap<>();
        plateau.put(sushi1, 4);
        plateau.put(sushi2, 6);

        Ensemble ensembleFamille = new Ensemble(
                20,
                "Plateau famille",
                25.0,
                "Plateau de sushi variés",
                "img/plateau.jpg",
                plateau
        );

        System.out.println("Ensemble : " + ensembleFamille.getNom());
        System.out.println("Nombre de types de sushi dans le plateau : " + ensembleFamille.getSushis().size());


        System.out.println("\n=== TEST UTILISATEUR ===");

        Utilisateur user = new Utilisateur();
        user.setId(1);
        user.setNom("Dupont");
        user.setEmail("dupont@mail.com");
        user.setMotDePasse("1234");
        user.setAdresse("Bordeaux");
        user.setTelephone("0600000000");
        user.setPointsFidelite(50);

        System.out.println("Utilisateur : " + user.getNom());
        System.out.println("Points fidélité : " + user.getPointsFidelite());


        System.out.println("\n=== TEST PANIER ===");

        Map<Produit, Integer> produitsPanier = new HashMap<>();
        produitsPanier.put(sushi1, 2);
        produitsPanier.put(boisson1, 1);
        produitsPanier.put(formuleMidi, 1);

        Panier panier = new Panier();
        panier.setProduits(produitsPanier);

        double total = 0;

        for (Produit p : produitsPanier.keySet()) {

            int quantite = produitsPanier.get(p);
            double prix = p.getPrix();

            System.out.println(p.getNom() + " x" + quantite + " = " + (prix * quantite));

            total += prix * quantite;
        }

        panier.setPrixTotal(total);

        System.out.println("Total panier = " + panier.getPrixTotal());


        System.out.println("\n=== TEST HISTORIQUE COMMANDE ===");

        HistoriqueCommande commande = new HistoriqueCommande();
        commande.setIdCommande(100);
        commande.setUtilisateur(user);
        commande.setProduits(produitsPanier);
        commande.setDateCommande(new Date());
        commande.setTotal(total);
        commande.setStatut(StatutCommande.EN_ATTENTE);

        System.out.println("Commande ID : " + commande.getIdCommande());
        System.out.println("Client : " + commande.getUtilisateur().getNom());
        System.out.println("Statut : " + commande.getStatut());
        System.out.println("Total commande : " + commande.getTotal());


        System.out.println("\n=== FIN DES TESTS ===");
    }
}
