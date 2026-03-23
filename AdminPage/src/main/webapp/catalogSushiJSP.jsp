<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.Sushi" %>

<%
    List<Sushi> sushis = (List<Sushi>) request.getAttribute("listeSushis");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Catalogue Sushi</title>

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
    </style>
</head>

<body>

<h1>Gestion des Sushis</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Modifier</th>
        <th>Supprimer</th>
    </tr>

<%
    if (sushis != null) {
        for (Sushi s : sushis) {
%>

    <tr>
		<form action="CreateAndUpdateSushiServlet" method="post">
	        <td>
	            <input type="text" name="id" value="<%= s.getId() %>" readonly />
	        </td>
	
	        <td>
	            <input type="text" name="nom" value="<%= s.getNom() %>" />
	        </td>
	
	        <td>
	            <input type="text" name="prix" value="<%= s.getPrix() %>" />
	        </td>
	
	        <td>
	            <button type="submit">Modifier</button>
	        </td>
		</form>
		<!-- DELETE -->
		<form action="DeleteSushiServlet" method="post">
		    <input type="hidden" name="id" value="<%= s.getId() %>" />
		    <td>
		        <button type="submit">Supprimer</button>
		    </td>
		</form>
    </tr>


<%
        }
    }
%>

</table>

<!-- Création d’un nouveau sushi -->

<div class="create-box">
    <h2>Créer un Sushi</h2>

    <form action="CreateAndUpdateSushiServlet" method="post">

        <label>ID :</label><br>
        <input type="text" name="id"><br><br>

        <label>Nom :</label><br>
        <input type="text" name="nom"><br><br>

        <label>Prix :</label><br>
        <input type="text" name="prix"><br><br>

        <button type="submit">Créer</button>

    </form>
</div>

</body>
</html>