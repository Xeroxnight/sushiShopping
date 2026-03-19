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
            justify-content: center;
            align-items: center;
            height: 80vh;
            gap: 50px;
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

    </style>
</head>

<body>

<header>
    <h1>Interface Admin - Sushi</h1>
</header>

<div class="container">

    <!-- Catalogue -->
    <div class="card">
        <h2>Catalogue</h2>

        <form action="CatalogueSushi" method="get">
            <button class="btn" type="submit">
                Gérer les produits
            </button>
        </form>
    </div>

    <!-- Utilisateurs -->
    <div class="card">
        <h2>Utilisateurs</h2>

        <form action="utilisateurs" method="get">
            <button class="btn" type="submit">
                Voir les utilisateurs
            </button>
        </form>
    </div>

    <!-- Commandes -->
    <div class="card">
        <h2>Commandes</h2>

        <form action="commandes" method="get">
            <button class="btn" type="submit">
                Suivre les commandes
            </button>
        </form>
    </div>

</div>

</body>
</html>