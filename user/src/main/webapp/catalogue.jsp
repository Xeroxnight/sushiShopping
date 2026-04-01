<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.Sushi" %>
<%@ page import="metier.Boisson" %>
<%@ page import="metier.Ensemble" %>
<%@ page import="metier.Formule" %>
<%@ page import="metier.Utilisateur" %>

<%
    List<Sushi> sushis = (List<Sushi>) request.getAttribute("sushis");
    List<Boisson> boissons = (List<Boisson>) request.getAttribute("boissons");
    List<Ensemble> ensembles = (List<Ensemble>) request.getAttribute("ensembles");
    List<Formule> formules = (List<Formule>) request.getAttribute("formules");
    Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Catalogue - Sushi Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .catalogue-section {
            max-width: 1200px;
            margin: 30px auto;
            padding: 20px;
        }
        
        .section-title {
            font-size: 28px;
            margin: 40px 0 20px;
            color: #333;
            border-left: 5px solid #e74c3c;
            padding-left: 15px;
        }
        
        .produits-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }
        
        .produit-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s;
            text-align: center;
        }
        
        .produit-card:hover {
            transform: translateY(-5px);
        }
        
        .produit-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        
        .produit-card .info {
            padding: 15px;
        }
        
        .produit-card h3 {
            margin: 0 0 10px;
            font-size: 18px;
            color: #333;
        }
        
        .produit-card .prix {
            font-size: 20px;
            font-weight: bold;
            color: #e74c3c;
            margin: 10px 0;
        }
        
        .produit-card .description {
            font-size: 12px;
            color: #666;
            margin: 10px 0;
            line-height: 1.4;
        }
        
        .produit-card .type-badge {
            display: inline-block;
            background: #3498db;
            color: white;
            padding: 3px 8px;
            border-radius: 5px;
            font-size: 11px;
            margin-bottom: 10px;
        }
        
        .btn-ajouter {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            transition: background 0.3s;
            width: 100%;
        }
        
        .btn-ajouter:hover {
            background: #c0392b;
        }
        
        .message {
            background: #d4edda;
            color: #155724;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 20px;
        }
        
        .erreur {
            background: #f8d7da;
            color: #721c24;
        }
        
        .login-warning {
            background: #fff3cd;
            color: #856404;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<jsp:include page="includes/header.jsp" />

<div class="catalogue-section">
    <h1 class="section-title" style="border-left-color: transparent; text-align: center; margin-top: 0;">Notre Catalogue</h1>
    
    <% if (message != null) { %>
        <div class="message"><%= message %></div>
    <% } %>
    
    <% if (user == null) { %>
        <div class="login-warning">
            🔒 <a href="login.jsp">Connectez-vous</a> pour ajouter des produits au panier
        </div>
    <% } %>
    
    <!-- SUSHIS -->
    <% if (sushis != null && !sushis.isEmpty()) { %>
        <h2 class="section-title">🍣 Sushis</h2>
        <div class="produits-grid">
            <% for (Sushi s : sushis) { %>
                <div class="produit-card">
                    <img src="<%= s.getImage() != null && !s.getImage().isEmpty() ? s.getImage() : "img/default-sushi.jpg" %>" alt="<%= s.getNom() %>">
                    <div class="info">
                        <span class="type-badge">Sushi</span>
                        <h3><%= s.getNom() %></h3>
                        <div class="prix"><%= s.getPrix() %> €</div>
                        <form action="ajouterPanier" method="post">
                            <input type="hidden" name="type" value="sushi">
                            <input type="hidden" name="produitId" value="<%= s.getId() %>">
                            <button type="submit" class="btn-ajouter" <%= user == null ? "disabled" : "" %>>Ajouter au panier</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    <% } %>
    
    <!-- BOISSONS -->
    <% if (boissons != null && !boissons.isEmpty()) { %>
        <h2 class="section-title">🥤 Boissons</h2>
        <div class="produits-grid">
            <% for (Boisson b : boissons) { %>
                <div class="produit-card">
                    <img src="<%= b.getImage() != null && !b.getImage().isEmpty() ? b.getImage() : "img/default-boisson.jpg" %>" alt="<%= b.getNom() %>">
                    <div class="info">
                        <span class="type-badge">Boisson</span>
                        <h3><%= b.getNom() %></h3>
                        <div class="prix"><%= b.getPrix() %> €</div>
                        <form action="ajouterPanier" method="post">
                            <input type="hidden" name="type" value="boisson">
                            <input type="hidden" name="produitId" value="<%= b.getId() %>">
                            <button type="submit" class="btn-ajouter" <%= user == null ? "disabled" : "" %>>Ajouter au panier</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    <% } %>
    
    <!-- ENSEMBLES -->
    <% if (ensembles != null && !ensembles.isEmpty()) { %>
        <h2 class="section-title">📦 Ensembles</h2>
        <div class="produits-grid">
            <% for (Ensemble e : ensembles) { %>
                <div class="produit-card">
                    <img src="<%= e.getImage() != null && !e.getImage().isEmpty() ? e.getImage() : "img/default-ensemble.jpg" %>" alt="<%= e.getNom() %>">
                    <div class="info">
                        <span class="type-badge">Ensemble</span>
                        <h3><%= e.getNom() %></h3>
                        <div class="description"><%= e.getDescription() != null ? e.getDescription() : "" %></div>
                        <div class="prix"><%= e.getPrix() %> €</div>
                        <form action="ajouterPanier" method="post">
                            <input type="hidden" name="type" value="ensemble">
                            <input type="hidden" name="produitId" value="<%= e.getId() %>">
                            <button type="submit" class="btn-ajouter" <%= user == null ? "disabled" : "" %>>Ajouter au panier</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    <% } %>
    
    <!-- FORMULES -->
    <% if (formules != null && !formules.isEmpty()) { %>
        <h2 class="section-title">🎁 Formules</h2>
        <div class="produits-grid">
            <% for (Formule f : formules) { %>
                <div class="produit-card">
                    <img src="<%= f.getImage() != null && !f.getImage().isEmpty() ? f.getImage() : "img/default-formule.jpg" %>" alt="<%= f.getNom() %>">
                    <div class="info">
                        <span class="type-badge">Formule</span>
                        <h3><%= f.getNom() %></h3>
                        <div class="description"><%= f.getDescription() != null ? f.getDescription() : "" %></div>
                        <div class="prix"><%= f.getPrix() %> €</div>
                        <form action="ajouterPanier" method="post">
                            <input type="hidden" name="type" value="formule">
                            <input type="hidden" name="produitId" value="<%= f.getId() %>">
                            <button type="submit" class="btn-ajouter" <%= user == null ? "disabled" : "" %>>Ajouter au panier</button>
                        </form>
                    </div>
                </div>
            <% } %>
        </div>
    <% } %>
    
    <% if ((sushis == null || sushis.isEmpty()) && 
          (boissons == null || boissons.isEmpty()) && 
          (ensembles == null || ensembles.isEmpty()) && 
          (formules == null || formules.isEmpty())) { %>
        <p style="text-align: center; padding: 50px;">Aucun produit disponible pour le moment.</p>
    <% } %>
</div>

<jsp:include page="includes/footer.jsp" />

</body>
</html>