<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.Boisson" %>

<%
    List<Boisson> boissons = (List<Boisson>) request.getAttribute("listeBoissons");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Catalogue Boissons</title>

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
        }

        input {
            padding: 5px;
            width: 90%;
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

        .create-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            width: 400px;
            margin: auto;
        }
        
        /* Style supplémentaire pour la description */
        .desc-input {
            width: 100%;
            min-width: 200px;
        }
    </style>
</head>

<body>

<form action="/AdminPage" method="get">
    <button class="btn" type="submit">
        Home
    </button>
</form>
<h1>Gestion des Boissons</h1>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Description</th>
            <th>Image</th>
            <th>Modifier</th>
            <th>Supprimer</th>
        </tr>
    </thead>
    <tbody>

<%
    if (boissons != null) {
        for (Boisson b : boissons) {
%>

        <tr>
            <form action="CreateAndUpdateBoissonServlet" method="post">
                <td>
                    <input type="text" name="id" value="<%= b.getId() %>" readonly />
                </td>
                <td>
                    <input type="text" name="nom" value="<%= b.getNom() %>" />
                </td>
                <td>
                    <input type="text" name="prix" value="<%= b.getPrix() %>" />
                </td>
                <td>
                    <input type="text" name="description" class="desc-input" value="<%= b.getDescription() != null ? b.getDescription() : "" %>" />
                </td>
                <td>
                    <input type="text" name="image" value="<%= b.getImage() != null ? b.getImage() : "" %>" />
                </td>
                <td>
                    <button type="submit">Modifier</button>
                </td>
            </form>
            <!-- Formulaire de suppression -->
            <form action="DeleteBoissonServlet" method="post">
                <input type="hidden" name="id" value="<%= b.getId() %>" />
                <td>
                    <button type="submit">Supprimer</button>
                </td>
            </form>
        </tr>

<%
        }
    }
%>

    </tbody>
</table>

<!-- Création d’une nouvelle boisson -->
<div class="create-box">
    <h2>Créer une Boisson</h2>

    <form action="CreateAndUpdateBoissonServlet" method="post">

        <label>ID :</label><br>
        <input type="text" name="id"><br><br>

        <label>Nom :</label><br>
        <input type="text" name="nom"><br><br>

        <label>Prix :</label><br>
        <input type="text" name="prix"><br><br>
        
        <label>Description :</label><br>
        <input type="text" name="description"><br><br>
        
        <label>Image (URL) :</label><br>
        <input type="text" name="image"><br><br>

        <button type="submit">Créer</button>

    </form>
</div>

</body>
</html>