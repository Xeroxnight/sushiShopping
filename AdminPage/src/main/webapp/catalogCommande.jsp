<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="metier.HistoriqueCommande" %>
<%@ page import="metier.Utilisateur" %>
<%@ page import="metier.Produit" %>
<%@ page import="metier.Sushi" %>
<%@ page import="metier.Boisson" %>
<%@ page import="metier.Ensemble" %>
<%@ page import="metier.Formule" %>
<%@ page import="metier.StatutCommande" %>

<%
    List<HistoriqueCommande> commandes = (List<HistoriqueCommande>) request.getAttribute("listeCommandes");
    Map<Integer, Produit> catalogue = (Map<Integer, Produit>) request.getAttribute("catalogue");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Commandes</title>
    
    <style>
        body {
            font-family: Arial;
            background: #f4f4f4;
            padding: 20px;
        }
        
        h1 {
            text-align: center;
        }
        
        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            margin-bottom: 40px;
        }
        
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
            vertical-align: top;
        }
        
        button {
            padding: 8px 12px;
            background: #e74c3c;
            color: white;
            border: none;
            cursor: pointer;
        }
        
        button:hover {
            background: #c0392b;
        }
        
        .valider-btn {
            background: #27ae60;
        }
        
        .valider-btn:hover {
            background: #229954;
        }
        
        .delete-btn {
            background: #e74c3c;
        }
        
        .delete-btn:hover {
            background: #c0392b;
        }
        
        .statut-en-attente {
            background: #f39c12;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            display: inline-block;
        }
        
        .statut-livree {
            background: #27ae60;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            display: inline-block;
        }
        
        .produits-list {
            text-align: left;
            font-size: 12px;
        }
        
        .produits-list ul {
            margin: 0;
            padding-left: 20px;
        }
        
        .btn-home {
            background: #3498db;
            margin-bottom: 20px;
        }
        
        .btn-home:hover {
            background: #2980b9;
        }
    </style>
</head>

<body>

<form action="/AdminPage" method="get">
    <button class="btn" type="submit">
        Home
    </button>
</form>

<h1>Gestion des Commandes</h1>

<table>
    <thead>
        <tr>
            <th>ID Commande</th>
            <th>Client (ID)</th>
            <th>Client (Nom)</th>
            <th>Produits</th>
            <th>Total</th>
            <th>Date</th>
            <th>Statut</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>

<%
    if (commandes != null && !commandes.isEmpty()) {
        for (HistoriqueCommande c : commandes) {
            Utilisateur user = c.getUtilisateur();
%>
         <tr>
             <td><%= c.getIdCommande() %></td>
             <td><%= user != null ? user.getId() : "?" %></td>
             <td><%= user != null ? user.getNom() : "Client inconnu" %></td>
             <td class="produits-list">
                 <ul>
                     <%
                         // Afficher les sushis
                         if (c.getSushis() != null) {
                             for (Integer id : c.getSushis()) {
                                 Produit p = catalogue != null ? catalogue.get(id) : null;
                                 if (p != null) {
                     %>
                         <li><%= p.getNom() %> (Sushi) - <%= p.getPrix() %> €</li>
                     <%
                                 } else {
                     %>
                         <li>Sushi ID <%= id %> (non trouvé)</li>
                     <%
                                 }
                             }
                         }
                         
                         // Afficher les boissons
                         if (c.getBoissons() != null) {
                             for (Integer id : c.getBoissons()) {
                                 Produit p = catalogue != null ? catalogue.get(id) : null;
                                 if (p != null) {
                     %>
                         <li><%= p.getNom() %> (Boisson) - <%= p.getPrix() %> €</li>
                     <%
                                 } else {
                     %>
                         <li>Boisson ID <%= id %> (non trouvé)</li>
                     <%
                                 }
                             }
                         }
                         
                         // Afficher les ensembles
                         if (c.getEnsembles() != null) {
                             for (Integer id : c.getEnsembles()) {
                                 Produit p = catalogue != null ? catalogue.get(id) : null;
                                 if (p != null) {
                     %>
                         <li><%= p.getNom() %> (Ensemble) - <%= p.getPrix() %> €</li>
                     <%
                                 } else {
                     %>
                         <li>Ensemble ID <%= id %> (non trouvé)</li>
                     <%
                                 }
                             }
                         }
                         
                         // Afficher les formules
                         if (c.getFormules() != null) {
                             for (Integer id : c.getFormules()) {
                                 Produit p = catalogue != null ? catalogue.get(id) : null;
                                 if (p != null) {
                     %>
                         <li><%= p.getNom() %> (Formule) - <%= p.getPrix() %> €</li>
                     <%
                                 } else {
                     %>
                         <li>Formule ID <%= id %> (non trouvé)</li>
                     <%
                                 }
                             }
                         }
                     %>
                 </ul>
             </td>
             <td><%= c.getTotal() %> €</td>
             <td><%= c.getDateCommande() != null ? c.getDateCommande().toString() : "" %></td>
             <td>
                 <% if (c.getStatut() == StatutCommande.EN_ATTENTE) { %>
                     <span class="statut-en-attente">En attente</span>
                 <% } else { %>
                     <span class="statut-livree">Livrée</span>
                 <% } %>
             </td>
             <td>
                 <% if (c.getStatut() == StatutCommande.EN_ATTENTE) { %>
                     <form action="ValiderCommandeServlet" method="post" style="display: inline;">
                         <input type="hidden" name="id" value="<%= c.getIdCommande() %>" />
                         <button type="submit" class="valider-btn">Valider</button>
                     </form>
                 <% } %>
                 <form action="DeleteCommandeServlet" method="post" style="display: inline;">
                     <input type="hidden" name="id" value="<%= c.getIdCommande() %>" />
                     <button type="submit" class="delete-btn">Supprimer</button>
                 </form>
             </td>
         </tr>
<%
        }
    } else {
%>
         <tr>
             <td colspan="8">Aucune commande trouvée</td>
         </tr>
<%
    }
%>

    </tbody>
</table>

</body>
</html>