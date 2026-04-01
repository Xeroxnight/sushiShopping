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
                <li class="dropdown">
                    <a href="#">Catalogue ▼</a>
                    <ul class="dropdown-content">
                        <li><a href="sushi.jsp">Sushi</a></li>
                        <li><a href="pack.jsp">Pack</a></li>
                        <li><a href="formule.jsp">Formule</a></li>
                        <li><a href="dessert.jsp">Dessert</a></li>
                        <li><a href="boisson.jsp">Boisson</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        
        <div class="auth-section">
            <% if (userConnecte != null) { %>
                <span class="user-name">👤 <%= userConnecte.getNom() %></span>
                <a href="logout" class="btn-deco">Déconnexion</a>
            <% } else { %>
                <a href="login.jsp" class="btn-connexion">Connexion</a>
                <a href="inscription.jsp" class="btn-inscription">Inscription</a>
            <% } %>
        </div>
    </div>
</header>