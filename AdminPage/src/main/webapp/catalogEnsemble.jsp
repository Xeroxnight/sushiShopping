<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="metier.Ensemble" %>
<%@ page import="metier.Sushi" %>

<%
    List<Ensemble> ensembles = (List<Ensemble>) request.getAttribute("listeEnsembles");
    List<Sushi> sushis = (List<Sushi>) request.getAttribute("listeSushis");
    
    // Créer une map pour accéder rapidement aux sushis par ID
    Map<Integer, Sushi> sushiMap = new java.util.HashMap<>();
    if (sushis != null) {
        for (Sushi s : sushis) {
            sushiMap.put(s.getId(), s);
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Catalogue Ensembles</title>
    
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
        
        .create-box, .modify-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            width: 600px;
            margin: 20px auto;
        }
        
        .modify-box {
            background: #fff9e6;
            border: 2px solid #f39c12;
        }
        
        .sushi-item {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
            align-items: center;
        }
        
        .sushi-item select {
            flex: 2;
            padding: 5px;
        }
        
        .sushi-item input {
            flex: 1;
            width: auto;
        }
        
        .sushi-item button {
            background: #95a5a6;
            padding: 5px 10px;
            font-size: 12px;
        }
        
        .add-sushi-btn {
            background: #3498db;
            margin-top: 10px;
        }
        
        .add-sushi-btn:hover {
            background: #2980b9;
        }
        
        .ensemble-sushis {
            text-align: left;
            font-size: 12px;
        }
        
        .ensemble-sushis ul {
            margin: 0;
            padding-left: 20px;
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
        
        .cancel-btn {
            background: #95a5a6;
        }
        
        .cancel-btn:hover {
            background: #7f8c8d;
        }
        
        .save-btn {
            background: #27ae60;
        }
        
        .save-btn:hover {
            background: #229954;
        }
    </style>
</head>

<body>
<form action="/AdminPage" method="get">
    <button class="btn" type="submit">
        Home
    </button>
</form>
<h1>Gestion des Ensembles</h1>

<!-- Affichage des ensembles existants -->
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Description</th>
            <th>Image</th>
            <th>Composition</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>

<%
    if (ensembles != null) {
        for (Ensemble e : ensembles) {
%>
        <tr>
            <td><%= e.getId() %></td>
            <td><%= e.getNom() %></td>
            <td><%= e.getPrix() %> €</td>
            <td><%= e.getDescription() != null ? e.getDescription() : "" %></td>
            <td><%= e.getImage() != null ? e.getImage() : "" %></td>
            <td class="ensemble-sushis">
                <ul>
                    <%
                        if (e.getSushis() != null) {
                            for (Map.Entry<Integer, Integer> entry : e.getSushis().entrySet()) {
                                Integer sushiId = entry.getKey();
                                Integer quantite = entry.getValue();
                                Sushi sushi = sushiMap.get(sushiId);
                                if (sushi != null) {
                    %>
                        <li><%= sushi.getNom() %> x<%= quantite %> (<%= sushi.getPrix() %> €)</li>
                    <%
                                } else {
                    %>
                        <li>Sushi ID <%= sushiId %> (non trouvé) x<%= quantite %></li>
                    <%
                                }
                            }
                        }
                    %>
                </ul>
            </td>
            <td class="action-buttons">
                <button onclick="showModifyForm(<%= e.getId() %>, '<%= e.getNom() %>', <%= e.getPrix() %>, '<%= e.getDescription() != null ? e.getDescription().replace("'", "\\'") : "" %>', '<%= e.getImage() != null ? e.getImage() : "" %>', <%= e.getSushis() != null ? e.getSushis().size() : 0 %>)" class="modify-btn">
                    Modifier
                </button>
                <form action="DeleteEnsembleServlet" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= e.getId() %>" />
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
    <h2>Créer un Ensemble</h2>
    
    <form action="CreateAndUpdateEnsembleServlet" method="post">
        <input type="hidden" name="action" value="create">
        
        <label>ID :</label><br>
        <input type="text" name="id" required><br><br>
        
        <label>Nom :</label><br>
        <input type="text" name="nom" required><br><br>
        
        <label>Prix :</label><br>
        <input type="text" name="prix" required><br><br>
        
        <label>Description :</label><br>
        <textarea name="description" rows="3"></textarea><br><br>
        
        <label>Image (URL) :</label><br>
        <input type="text" name="image"><br><br>
        
        <label>Composition de l'ensemble :</label><br>
        <div id="createSushisContainer">
            <!-- Les champs sushi seront ajoutés ici dynamiquement -->
        </div>
        
        <button type="button" onclick="addSushiField('createSushisContainer', 'create')" class="add-sushi-btn">+ Ajouter un sushi</button>
        <br><br>
        
        <button type="submit" class="save-btn">Créer l'ensemble</button>
    </form>
</div>

<!-- Formulaire de modification (caché par défaut) -->
<div class="modify-box" id="modifyForm" style="display: none;">
    <h2>Modifier l'Ensemble</h2>
    
    <form action="CreateAndUpdateEnsembleServlet" method="post" id="modifyEnsembleForm">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="originalId" id="originalId">
        
        <label>ID :</label><br>
        <input type="text" name="id" id="modifyId" required><br><br>
        
        <label>Nom :</label><br>
        <input type="text" name="nom" id="modifyNom" required><br><br>
        
        <label>Prix :</label><br>
        <input type="text" name="prix" id="modifyPrix" required><br><br>
        
        <label>Description :</label><br>
        <textarea name="description" id="modifyDescription" rows="3"></textarea><br><br>
        
        <label>Image (URL) :</label><br>
        <input type="text" name="image" id="modifyImage"><br><br>
        
        <label>Composition de l'ensemble :</label><br>
        <div id="modifySushisContainer">
            <!-- Les champs sushi seront ajoutés ici dynamiquement -->
        </div>
        
        <button type="button" onclick="addSushiField('modifySushisContainer', 'modify')" class="add-sushi-btn">+ Ajouter un sushi</button>
        <br><br>
        
        <button type="submit" class="save-btn">Enregistrer les modifications</button>
        <button type="button" onclick="hideModifyForm()" class="cancel-btn">Annuler</button>
    </form>
</div>

<script>
    // Convertir la liste des sushis en JavaScript
    const sushisList = [
        <%
            if (sushis != null) {
                for (int i = 0; i < sushis.size(); i++) {
                    Sushi s = sushis.get(i);
        %>
            {
                id: <%= s.getId() %>,
                nom: "<%= s.getNom() %>",
                prix: <%= s.getPrix() %>
            }<%= i < sushis.size() - 1 ? "," : "" %>
        <%
                }
            }
        %>
    ];
    
    // Stocker les données des ensembles pour modification
    const ensemblesData = new Map();
    <%
        if (ensembles != null) {
            for (Ensemble e : ensembles) {
                %>
                ensemblesData.set(<%= e.getId() %>, {
                    id: <%= e.getId() %>,
                    nom: "<%= e.getNom() %>",
                    prix: <%= e.getPrix() %>,
                    description: "<%= e.getDescription() != null ? e.getDescription().replace("\"", "\\\"") : "" %>",
                    image: "<%= e.getImage() != null ? e.getImage() : "" %>",
                    sushis: [
                        <%
                            if (e.getSushis() != null) {
                                int count = 0;
                                for (Map.Entry<Integer, Integer> entry : e.getSushis().entrySet()) {
                                    Integer sushiId = entry.getKey();
                                    Integer quantite = entry.getValue();
                        %>
                            { sushiId: <%= sushiId %>, quantite: <%= quantite %> }<%= ++count < e.getSushis().size() ? "," : "" %>
                        <%
                                }
                            }
                        %>
                    ]
                });
                <%
            }
        }
    %>
    
    let createSushiCount = 0;
    let modifySushiCount = 0;
    
    function addSushiField(containerId, type) {
        const container = document.getElementById(containerId);
        const div = document.createElement('div');
        div.className = 'sushi-item';
        const fieldId = type + '_sushi_' + (type === 'create' ? createSushiCount++ : modifySushiCount++);
        div.id = fieldId;
        
        // Créer le select pour choisir le sushi
        const select = document.createElement('select');
        select.name = 'sushiIds';
        select.required = true;
        select.style.padding = '5px';
        
        // Option par défaut
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.text = '-- Choisir un sushi --';
        select.appendChild(defaultOption);
        
        // Ajouter tous les sushis depuis la liste JavaScript
        if (sushisList.length > 0) {
            for (let i = 0; i < sushisList.length; i++) {
                const sushi = sushisList[i];
                const option = document.createElement('option');
                option.value = sushi.id;
                option.text = sushi.nom + ' (' + sushi.prix + ' €)';
                select.appendChild(option);
            }
        } else {
            const errorOption = document.createElement('option');
            errorOption.value = '';
            errorOption.text = 'Aucun sushi disponible';
            errorOption.disabled = true;
            select.appendChild(errorOption);
        }
        
        // Champ pour la quantité
        const quantite = document.createElement('input');
        quantite.type = 'number';
        quantite.name = 'quantites';
        quantite.placeholder = 'Quantité';
        quantite.min = '1';
        quantite.value = '1';
        quantite.required = true;
        quantite.style.width = '80px';
        quantite.style.padding = '5px';
        
        // Bouton supprimer
        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.textContent = 'X';
        removeBtn.style.background = '#95a5a6';
        removeBtn.style.padding = '5px 10px';
        removeBtn.onclick = function() { 
            div.remove(); 
        };
        
        div.appendChild(select);
        div.appendChild(quantite);
        div.appendChild(removeBtn);
        
        container.appendChild(div);
    }
    
    function showModifyForm(id, nom, prix, description, image, sushiCount) {
        // Cacher le formulaire de création
        document.getElementById('createForm').style.display = 'none';
        
        // Afficher le formulaire de modification
        const modifyForm = document.getElementById('modifyForm');
        modifyForm.style.display = 'block';
        
        // Remplir les champs
        document.getElementById('originalId').value = id;
        document.getElementById('modifyId').value = id;
        document.getElementById('modifyNom').value = nom;
        document.getElementById('modifyPrix').value = prix;
        document.getElementById('modifyDescription').value = description;
        document.getElementById('modifyImage').value = image;
        
        // Charger les sushis existants
        const container = document.getElementById('modifySushisContainer');
        container.innerHTML = '';
        modifySushiCount = 0;
        
        const ensemble = ensemblesData.get(parseInt(id));
        if (ensemble && ensemble.sushis) {
            for (let i = 0; i < ensemble.sushis.length; i++) {
                const sushiData = ensemble.sushis[i];
                addExistingSushiField(container, sushiData.sushiId, sushiData.quantite);
            }
        }
        
        // Ajouter un champ vide par défaut si nécessaire
        if (!ensemble || ensemble.sushis.length === 0) {
            addSushiField('modifySushisContainer', 'modify');
        }
        
        // Scroll vers le formulaire
        modifyForm.scrollIntoView({ behavior: 'smooth' });
    }
    
    function addExistingSushiField(container, sushiId, quantite) {
        const div = document.createElement('div');
        div.className = 'sushi-item';
        const fieldId = 'modify_sushi_' + modifySushiCount++;
        div.id = fieldId;
        
        // Créer le select
        const select = document.createElement('select');
        select.name = 'sushiIds';
        select.required = true;
        select.style.padding = '5px';
        
        // Ajouter toutes les options
        for (let i = 0; i < sushisList.length; i++) {
            const sushi = sushisList[i];
            const option = document.createElement('option');
            option.value = sushi.id;
            option.text = sushi.nom + ' (' + sushi.prix + ' €)';
            if (sushi.id === sushiId) {
                option.selected = true;
            }
            select.appendChild(option);
        }
        
        // Champ quantité
        const quantiteInput = document.createElement('input');
        quantiteInput.type = 'number';
        quantiteInput.name = 'quantites';
        quantiteInput.placeholder = 'Quantité';
        quantiteInput.min = '1';
        quantiteInput.value = quantite;
        quantiteInput.required = true;
        quantiteInput.style.width = '80px';
        quantiteInput.style.padding = '5px';
        
        // Bouton supprimer
        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.textContent = 'X';
        removeBtn.style.background = '#95a5a6';
        removeBtn.style.padding = '5px 10px';
        removeBtn.onclick = function() { 
            div.remove(); 
        };
        
        div.appendChild(select);
        div.appendChild(quantiteInput);
        div.appendChild(removeBtn);
        
        container.appendChild(div);
    }
    
    function hideModifyForm() {
        document.getElementById('modifyForm').style.display = 'none';
        document.getElementById('createForm').style.display = 'block';
        
        // Nettoyer le container des sushis de modification
        document.getElementById('modifySushisContainer').innerHTML = '';
        modifySushiCount = 0;
    }
    
    // Initialisation : ajouter un champ par défaut pour la création
    document.addEventListener('DOMContentLoaded', function() {
        addSushiField('createSushisContainer', 'create');
        console.log('Sushis disponibles:', sushisList);
    });
</script>

</body>
</html>