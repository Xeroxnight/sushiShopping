<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.HistoriqueCommande" %>
<%@ page import="metier.Produit" %>
<%@ page import="metier.Sushi" %>
<%@ page import="metier.Boisson" %>
<%@ page import="metier.Ensemble" %>
<%@ page import="metier.Formule" %>

<%
    HistoriqueCommande panier = (HistoriqueCommande) request.getAttribute("panier");
    Map<Integer, Produit> catalogue = (Map<Integer, Produit>) request.getAttribute("catalogue");
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mon Panier - Sushi Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .panier-container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
        }
        
        h1 {
            margin-bottom: 30px;
            color: #333;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background: #f5f5f5;
        }
        
        .btn-supprimer {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .btn-supprimer:hover {
            background: #c0392b;
        }
        
        .btn-payer {
            background: #27ae60;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        
        .btn-payer:hover {
            background: #229954;
        }
        
        .total {
            text-align: right;
            font-size: 20px;
            font-weight: bold;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 2px solid #ddd;
        }
        
        .panier-vide {
            text-align: center;
            padding: 50px;
            color: #666;
        }
        
        .message {
            background: #d4edda;
            color: #155724;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        .produit-nom {
            font-weight: bold;
        }
        
        .produit-type {
            display: inline-block;
            background: #3498db;
            color: white;
            padding: 2px 6px;
            border-radius: 3px;
            font-size: 10px;
            margin-left: 10px;
        }
    </style>
</head>
<body>

<jsp:include page="includes/header.jsp" />

<div class="panier-container">
    <h1>🛒 Mon Panier</h1>
    
    <% if (message != null) { %>
        <div class="message"><%= message %></div>
    <% } %>
    
    <%
        boolean panierVide = panier == null ||
            (panier.getSushis() == null || panier.getSushis().isEmpty()) &&
            (panier.getBoissons() == null || panier.getBoissons().isEmpty()) &&
            (panier.getEnsembles() == null || panier.getEnsembles().isEmpty()) &&
            (panier.getFormules() == null || panier.getFormules().isEmpty());
    %>
    
    <% if (panierVide) { %>
        <div class="panier-vide">
            <p>Votre panier est vide.</p>
            <a href="catalogue">👉 Découvrir nos produits</a>
        </div>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>Produit</th>
                    <th>Type</th>
                    <th>Prix unitaire</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    double total = 0;
                    
                    // Afficher les sushis
                    if (panier.getSushis() != null) {
                        for (int i = 0; i < panier.getSushis().size(); i++) {
                            Integer id = panier.getSushis().get(i);
                            Produit p = catalogue.get(id);
                            if (p != null) {
                                total += p.getPrix();
                %>
                                <tr>
                                    <td class="produit-nom"><%= p.getNom() %></td>
                                    <td><span class="produit-type">Sushi</span></td>
                                    <td><%= p.getPrix() %> €</td>
                                    <td>
                                        <form action="supprimerPanierItem" method="post" style="display:inline;">
                                            <input type="hidden" name="type" value="sushi">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <button type="submit" class="btn-supprimer">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                <%
                            }
                        }
                    }
                    
                    // Afficher les boissons
                    if (panier.getBoissons() != null) {
                        for (int i = 0; i < panier.getBoissons().size(); i++) {
                            Integer id = panier.getBoissons().get(i);
                            Produit p = catalogue.get(id);
                            if (p != null) {
                                total += p.getPrix();
                %>
                                <tr>
                                    <td class="produit-nom"><%= p.getNom() %></td>
                                    <td><span class="produit-type">Boisson</span></td>
                                    <td><%= p.getPrix() %> €</td>
                                    <td>
                                        <form action="supprimerPanierItem" method="post" style="display:inline;">
                                            <input type="hidden" name="type" value="boisson">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <button type="submit" class="btn-supprimer">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                <%
                            }
                        }
                    }
                    
                    // Afficher les ensembles
                    if (panier.getEnsembles() != null) {
                        for (int i = 0; i < panier.getEnsembles().size(); i++) {
                            Integer id = panier.getEnsembles().get(i);
                            Produit p = catalogue.get(id);
                            if (p != null) {
                                total += p.getPrix();
                %>
                                <tr>
                                    <td class="produit-nom"><%= p.getNom() %></td>
                                    <td><span class="produit-type">Ensemble</span></td>
                                    <td><%= p.getPrix() %> €</td>
                                    <td>
                                        <form action="supprimerPanierItem" method="post" style="display:inline;">
                                            <input type="hidden" name="type" value="ensemble">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <button type="submit" class="btn-supprimer">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                <%
                            }
                        }
                    }
                    
                    // Afficher les formules
                    if (panier.getFormules() != null) {
                        for (int i = 0; i < panier.getFormules().size(); i++) {
                            Integer id = panier.getFormules().get(i);
                            Produit p = catalogue.get(id);
                            if (p != null) {
                                total += p.getPrix();
                %>
                                <tr>
                                    <td class="produit-nom"><%= p.getNom() %></td>
                                    <td><span class="produit-type">Formule</span></td>
                                    <td><%= p.getPrix() %> €</td>
                                    <td>
                                        <form action="supprimerPanierItem" method="post" style="display:inline;">
                                            <input type="hidden" name="type" value="formule">
                                            <input type="hidden" name="index" value="<%= i %>">
                                            <button type="submit" class="btn-supprimer">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                <%
                            }
                        }
                    }
                %>
            </tbody>
        </table>
        
        <div class="total">
            Total : <%= String.format("%.2f", total) %> €
        </div>
        
        <form action="payer" method="post" style="text-align: right;">
            <button type="submit" class="btn-payer">💳 Valider la commande</button>
        </form>
    <% } %>
</div>

<jsp:include page="includes/footer.jsp" />

</body>
</html>