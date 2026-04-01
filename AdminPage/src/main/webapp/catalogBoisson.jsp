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
            vertical-align: middle;
        }

        input, textarea {
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
            width: 500px;
            margin: auto;
        }

        .action-buttons {
            display: flex;
            gap: 5px;
            justify-content: center;
        }

        .modify-btn {
            background: #f39c12;
        }

        .modify-btn:hover {
            background: #e67e22;
        }

        .delete-btn {
            background: #e74c3c;
        }

        .delete-btn:hover {
            background: #c0392b;
        }

        .save-btn {
            background: #27ae60;
        }

        .save-btn:hover {
            background: #229954;
        }

        .cancel-btn {
            background: #95a5a6;
        }

        .cancel-btn:hover {
            background: #7f8c8d;
        }

        .image-preview {
            max-width: 50px;
            max-height: 50px;
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

<!-- Affichage des boissons existantes -->
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Description</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>

<%
    if (boissons != null) {
        for (Boisson b : boissons) {
%>
            <tr>
                <td><%= b.getId() %></td>
                <td><%= b.getNom() %></td>
                <td><%= b.getPrix() %> €</td>
                <td><%= b.getDescription() != null ? b.getDescription() : "" %></td>
                <td>
                    <% if (b.getImage() != null && !b.getImage().isEmpty()) { %>
                        <img src="<%= b.getImage() %>" class="image-preview" alt="<%= b.getNom() %>">
                    <% } else { %>
                        -
                    <% } %>
                </td>
                <td class="action-buttons">
                    <button onclick="fillForm(<%= b.getId() %>, '<%= b.getNom() %>', <%= b.getPrix() %>, '<%= b.getDescription() != null ? b.getDescription().replace("'", "\\'") : "" %>', '<%= b.getImage() != null ? b.getImage() : "" %>')" class="modify-btn">
                        Modifier
                    </button>
                    <form action="DeleteBoissonServlet" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= b.getId() %>" />
                        <button type="submit" class="delete-btn">Supprimer</button>
                    </form>
                </td>
            </tr>
<%
        }
    }
%>

    </tbody>
</table>

<!-- Formulaire de création/modification -->
<div class="create-box">
    <h2 id="formTitle">Créer une Boisson</h2>
    
    <form action="CreateAndUpdateBoissonServlet" method="post" id="boissonForm">
        <input type="hidden" name="action" id="action" value="create">
        <input type="hidden" name="originalId" id="originalId">
        
        <label>ID :</label><br>
        <input type="text" name="id" id="id" required><br><br>
        
        <label>Nom :</label><br>
        <input type="text" name="nom" id="nom" required><br><br>
        
        <label>Prix :</label><br>
        <input type="text" name="prix" id="prix" required><br><br>
        
        <label>Description :</label><br>
        <textarea name="description" id="description" rows="3"></textarea><br><br>
        
        <label>Image (URL) :</label><br>
        <input type="text" name="image" id="image"><br><br>
        
        <button type="submit" class="save-btn" id="submitBtn">Créer</button>
        <button type="button" onclick="resetForm()" class="cancel-btn">Annuler</button>
    </form>
</div>

<script>
    function fillForm(id, nom, prix, description, image) {
        document.getElementById('formTitle').innerText = 'Modifier la Boisson';
        document.getElementById('action').value = 'update';
        document.getElementById('originalId').value = id;
        document.getElementById('id').value = id;
        document.getElementById('nom').value = nom;
        document.getElementById('prix').value = prix;
        document.getElementById('description').value = description;
        document.getElementById('image').value = image;
        document.getElementById('submitBtn').innerText = 'Modifier';
        
        // Scroll vers le formulaire
        document.getElementById('boissonForm').scrollIntoView({ behavior: 'smooth' });
    }
    
    function resetForm() {
        document.getElementById('formTitle').innerText = 'Créer une Boisson';
        document.getElementById('action').value = 'create';
        document.getElementById('originalId').value = '';
        document.getElementById('id').value = '';
        document.getElementById('nom').value = '';
        document.getElementById('prix').value = '';
        document.getElementById('description').value = '';
        document.getElementById('image').value = '';
        document.getElementById('submitBtn').innerText = 'Créer';
    }
</script>

</body>
</html>