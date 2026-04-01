<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="metier.Utilisateur" %>

<%
    Utilisateur userConnecte = (Utilisateur) session.getAttribute("utilisateur");
%>

<header class="header-sushi">
    <div class="header-container">
        <div class="logo">
            <a href="accueil">🍣 Sushi Shop</a>
        </div>
        
        <nav class="menu">
    <ul>
        <li><a href="catalogue">Catalogue</a></li>
        <li class="dropdown">
            <a href="#">Catégories ▼</a>
            <ul class="dropdown-content">
                <li><a href="catalogue#sushis">Sushis</a></li>
                <li><a href="catalogue#boissons">Boissons</a></li>
                <li><a href="catalogue#ensembles">Ensembles</a></li>
                <li><a href="catalogue#formules">Formules</a></li>
            </ul>
        </li>
    </ul>
</nav>
        
        <div class="auth-section">
    <% if (userConnecte != null) { %>
        <a href="panier" class="btn-panier">🛒 Panier</a>
        <span class="user-name">👤 <%= userConnecte.getNom() %></span>
        <a href="logout" class="btn-deco">Déconnexion</a>
    <% } else { %>
        <a href="login.jsp" class="btn-connexion">Connexion</a>
        <a href="inscription.jsp" class="btn-inscription">Inscription</a>
    <% } %>
</div>
    </div>
</header>