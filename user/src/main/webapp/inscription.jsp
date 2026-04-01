<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription - Sushi Shop</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="includes/header.jsp" />

<section class="form-container">
    <h1>Inscription</h1>
    
    <% 
        String erreur = (String) request.getAttribute("erreur");
        if (erreur != null) {
    %>
        <div class="erreur-message">
            <%= erreur %>
        </div>
    <% } %>
    
    <form action="inscription" method="post" class="inscription-form">
        <div class="form-group">
            <label for="nom">Nom :</label>
            <input type="text" id="nom" name="nom" required>
        </div>
        
        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>
        </div>
        
        <div class="form-group">
            <label for="motDePasse">Mot de passe :</label>
            <input type="password" id="motDePasse" name="motDePasse" required>
        </div>
        
        <div class="form-group">
            <label for="adresse">Adresse :</label>
            <input type="text" id="adresse" name="adresse">
        </div>
        
        <div class="form-group">
            <label for="telephone">Téléphone :</label>
            <input type="text" id="telephone" name="telephone">
        </div>
        
        <button type="submit" class="btn-submit">S'inscrire</button>
    </form>
    
    <p class="login-lien">
        Déjà inscrit ? <a href="login.jsp">Connectez-vous</a>
    </p>
</section>

<jsp:include page="includes/footer.jsp" />

</body>
</html>