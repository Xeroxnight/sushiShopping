<!DOCTYPE html>
<html>
<head>
    <title>Admin - Sushi</title>

    <style>
        body {
            font-family: Arial;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        header {
            background: #222;
            color: white;
            padding: 20px;
            text-align: center;
        }

        h1 {
            margin: 0;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
            gap: 30px;
            padding: 20px;
        }

        .card {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            text-align: center;
            width: 250px;
        }

        .card h2 {
            margin-bottom: 20px;
        }

        .btn {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 15px 20px;
            font-size: 16px;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
        }

        .btn:hover {
            background: #c0392b;
        }

        .config-card {
            background: #f9e6e6;
            border: 2px solid #e74c3c;
            width: 350px;
        }

        .config-card input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .config-card label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }

        .current-path {
            background: #f0f0f0;
            padding: 8px;
            border-radius: 5px;
            font-size: 12px;
            margin: 10px 0;
            word-break: break-all;
        }

        .btn-small {
            background: #27ae60;
            padding: 10px;
            font-size: 14px;
            margin-top: 10px;
        }

        .btn-small:hover {
            background: #229954;
        }

        hr {
            margin: 15px 0;
        }
    </style>
</head>

<body>

<header>
    <h1>Interface Admin - Sushi</h1>
</header>

<div class="container">

    <!-- Catalogue Sushi -->
    <div class="card">
        <h2>Catalogue des sushi</h2>
        <form action="CatalogueSushi" method="get">
            <button class="btn" type="submit">Gérer les sushi</button>
        </form>
    </div>

    <!-- Catalogue Boisson -->
    <div class="card">
        <h2>Catalogue des boisson</h2>
        <form action="CatalogueBoisson" method="get">
            <button class="btn" type="submit">Gérer les boissons</button>
        </form>
    </div>

    <!-- Catalogue Ensemble -->
    <div class="card">
        <h2>Catalogue des ensembles</h2>
        <form action="CatalogueEnsemble" method="get">
            <button class="btn" type="submit">Gérer les ensembles</button>
        </form>
    </div>

    <!-- Catalogue Formule -->
    <div class="card">
        <h2>Catalogue des formules</h2>
        <form action="CatalogueFormule" method="get">
            <button class="btn" type="submit">Gérer les formules</button>
        </form>
    </div>

    <!-- Utilisateurs -->
    <div class="card">
        <h2>catalogue des Utilisateurs</h2>
        <form action="CatalogueUtilisateur" method="get">
            <button class="btn" type="submit">Gérer les utilisateurs</button>
        </form>
    </div>

    <!-- Commandes -->
    <div class="card">
	    <h2>Commandes</h2>
	    <form action="CatalogueCommande" method="get">
	        <button class="btn" type="submit">Gérer les commandes</button>
	    </form>
	</div>

    <!-- Configuration Base de Données -->
    <div class="card config-card">
        <h2>Configuration BD</h2>
        
        <%
            String currentPath = metier.AppConfig.getPath();
        %>
        
        <div class="current-path">
            <strong>Chemin actuel :</strong><br>
            <%= currentPath %>
        </div>
        
        <hr>
        
        <form action="MainPage" method="post">
            <label for="xmlPath">Nouveau chemin du fichier XML :</label>
            <input type="text" id="xmlPath" name="xmlPath" placeholder="C:/chemin/vers/bdd.xml" required>
            
            <button type="submit" class="btn btn-small">Modifier le chemin</button>
        </form>
        
        <p style="font-size: 11px; color: #666; margin-top: 15px;">
            vous pouvez copier coler le chemin (bdd.xml se rajoutera tous seul).
        </p>
    </div>

</div>

</body>
</html>