<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.Utilisateur" %>

<%
    List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("listUser");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Utilisateurs</title>
    
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
            vertical-align: top;
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
        
        .modify-box {
            background: #fff9e6;
            border: 2px solid #f39c12;
            padding: 20px;
            border-radius: 10px;
            width: 500px;
            margin: 20px auto;
        }
    </style>
</head>

<body>
<form action="/AdminPage" method="get">
    <button class="btn" type="submit">
        Home
    </button>
</form>
<h1>Gestion des Utilisateurs</h1>

<!-- Affichage des utilisateurs existants -->
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Adresse</th>
            <th>Téléphone</th>
            <th>Points Fidélité</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>

<%
    if (utilisateurs != null) {
        for (Utilisateur u : utilisateurs) {
%>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getNom() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getAdresse() != null ? u.getAdresse() : "" %></td>
            <td><%= u.getTelephone() != null ? u.getTelephone() : "" %></td>
            <td><%= u.getPointsFidelite() %></td>
            <td class="action-buttons">
                <button onclick="showModifyForm(<%= u.getId() %>, '<%= u.getNom() %>', '<%= u.getEmail() %>', '<%= u.getAdresse() != null ? u.getAdresse() : "" %>', '<%= u.getTelephone() != null ? u.getTelephone() : "" %>', <%= u.getPointsFidelite() %>)" class="modify-btn">
                    Modifier
                </button>
                <form action="DeleteUtilisateurServlet" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= u.getId() %>" />
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

<!-- Formulaire de création -->
<div class="create-box" id="createForm">
    <h2>Créer un Utilisateur</h2>
    
    <form action="CreateAndUpdateUtilisateurServlet" method="post">
        <input type="hidden" name="action" value="create">
        
        <label>ID :</label><br>
        <input type="text" name="id" required><br><br>
        
        <label>Nom :</label><br>
        <input type="text" name="nom" required><br><br>
        
        <label>Email :</label><br>
        <input type="email" name="email" required><br><br>
        
        <label>Mot de passe :</label><br>
        <input type="password" name="motDePasse" required><br><br>
        
        <label>Adresse :</label><br>
        <input type="text" name="adresse"><br><br>
        
        <label>Téléphone :</label><br>
        <input type="text" name="telephone"><br><br>
        
        <label>Points Fidélité :</label><br>
        <input type="number" name="pointsFidelite" value="0"><br><br>
        
        <button type="submit" class="save-btn">Créer l'utilisateur</button>
    </form>
</div>

<!-- Formulaire de modification (caché par défaut) -->
<div class="modify-box" id="modifyForm" style="display: none;">
    <h2>Modifier l'Utilisateur</h2>
    
    <form action="CreateAndUpdateUtilisateurServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="originalId" id="originalId">
        
        <label>ID :</label><br>
        <input type="text" name="id" id="modifyId" required readonly><br><br>
        
        <label>Nom :</label><br>
        <input type="text" name="nom" id="modifyNom" required><br><br>
        
        <label>Email :</label><br>
        <input type="email" name="email" id="modifyEmail" required><br><br>
        
        <label>Mot de passe :</label><br>
        <input type="password" name="motDePasse" id="modifyMotDePasse"><br>
        <small>Laissez vide pour ne pas changer</small><br><br>
        
        <label>Adresse :</label><br>
        <input type="text" name="adresse" id="modifyAdresse"><br><br>
        
        <label>Téléphone :</label><br>
        <input type="text" name="telephone" id="modifyTelephone"><br><br>
        
        <label>Points Fidélité :</label><br>
        <input type="number" name="pointsFidelite" id="modifyPointsFidelite"><br><br>
        
        <button type="submit" class="save-btn">Enregistrer</button>
        <button type="button" onclick="hideModifyForm()" class="cancel-btn">Annuler</button>
    </form>
</div>

<script>
    function showModifyForm(id, nom, email, adresse, telephone, pointsFidelite) {
        document.getElementById('createForm').style.display = 'none';
        const modifyForm = document.getElementById('modifyForm');
        modifyForm.style.display = 'block';
        
        document.getElementById('originalId').value = id;
        document.getElementById('modifyId').value = id;
        document.getElementById('modifyNom').value = nom;
        document.getElementById('modifyEmail').value = email;
        document.getElementById('modifyAdresse').value = adresse;
        document.getElementById('modifyTelephone').value = telephone;
        document.getElementById('modifyPointsFidelite').value = pointsFidelite;
        document.getElementById('modifyMotDePasse').value = '';
        
        modifyForm.scrollIntoView({ behavior: 'smooth' });
    }
    
    function hideModifyForm() {
        document.getElementById('modifyForm').style.display = 'none';
        document.getElementById('createForm').style.display = 'block';
    }
</script>

</body>
</html>