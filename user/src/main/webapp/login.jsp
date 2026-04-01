<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion - Sushi Shop</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="includes/header.jsp" />

<section class="form-container">
    <h1>Connexion</h1>
    
    <% 
        String erreur = (String) request.getAttribute("erreur");
        if (erreur != null) {
    %>
        <div class="erreur-message">
            <%= erreur %>
        </div>
    <% } %>
    
    <form action="login" method="post" class="login-form">
        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>
        </div>
        
        <div class="form-group">
            <label for="motDePasse">Mot de passe :</label>
            <input type="password" id="motDePasse" name="motDePasse" required>
        </div>
        
        <button type="submit" class="btn-submit">Se connecter</button>
    </form>
    
    <p class="inscription-lien">
        Pas encore de compte ? <a href="inscription.jsp">Inscrivez-vous</a>
    </p>
</section>

<jsp:include page="includes/footer.jsp" />

</body>
</html>