<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.Formule" %>
<%@ page import="metier.Produit" %>
<%@ page import="metier.Sushi" %>
<%@ page import="metier.Boisson" %>
<%@ page import="metier.Ensemble" %>

<%
    List<Formule> formules = (List<Formule>) request.getAttribute("listeFormules");
    List<Produit> tousLesProduits = (List<Produit>) request.getAttribute("tousLesProduits");
    
    // Créer des maps pour accéder rapidement aux produits par ID
    java.util.Map<Integer, Sushi> sushiMap = new java.util.HashMap<>();
    java.util.Map<Integer, Boisson> boissonMap = new java.util.HashMap<>();
    java.util.Map<Integer, Ensemble> ensembleMap = new java.util.HashMap<>();
    
    // Séparer les produits par type pour les formulaires
    List<Sushi> sushisDisponibles = new java.util.ArrayList<>();
    List<Boisson> boissonsDisponibles = new java.util.ArrayList<>();
    List<Ensemble> ensemblesDisponibles = new java.util.ArrayList<>();
    
    if (tousLesProduits != null) {
        for (Produit p : tousLesProduits) {
            if (p instanceof Sushi) {
                Sushi s = (Sushi) p;
                sushiMap.put(s.getId(), s);
                sushisDisponibles.add(s);
            } else if (p instanceof Boisson) {
                Boisson b = (Boisson) p;
                boissonMap.put(b.getId(), b);
                boissonsDisponibles.add(b);
            } else if (p instanceof Ensemble) {
                Ensemble e = (Ensemble) p;
                ensembleMap.put(e.getId(), e);
                ensemblesDisponibles.add(e);
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Catalogue Formules</title>
    
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
            width: 700px;
            margin: 20px auto;
        }
        
        .modify-box {
            background: #fff9e6;
            border: 2px solid #f39c12;
        }
        
        .produit-section {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            background: #fafafa;
        }
        
        .produit-section h3 {
            margin-top: 0;
            color: #333;
        }
        
        .produit-item {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
            align-items: center;
        }
        
        .produit-item select {
            flex: 2;
            padding: 5px;
        }
        
        .produit-item button {
            background: #95a5a6;
            padding: 5px 10px;
            font-size: 12px;
        }
        
        .add-produit-btn {
            background: #3498db;
            margin-top: 10px;
        }
        
        .add-produit-btn:hover {
            background: #2980b9;
        }
        
        .formule-produits {
            text-align: left;
            font-size: 12px;
        }
        
        .formule-produits ul {
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
        
        .produit-type {
            font-size: 10px;
            color: #666;
            margin-left: 5px;
        }
        
        .missing-product {
            color: red;
            font-style: italic;
        }
    </style>
</head>

<body>
<form action="/AdminPage" method="get">
    <button class="btn" type="submit">
        Home
    </button>
</form>
<h1>Gestion des Formules</h1>

<!-- Affichage des formules existantes -->
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
    if (formules != null) {
        for (Formule f : formules) {
%>
            <tr>
                <td><%= f.getId() %></td>
                <td><%= f.getNom() %></td>
                <td><%= f.getPrix() %> €</td>
                <td><%= f.getDescription() != null ? f.getDescription() : "" %></td>
                <td><%= f.getImage() != null ? f.getImage() : "" %></td>
                <td class="formule-produits">
                    <ul>
                        <%-- Afficher les sushis avec vérification d'existence --%>
                        <% if (f.getSushis() != null && !f.getSushis().isEmpty()) { %>
                            <li><strong>Sushis:</strong>
                                <ul>
                                <% for (Integer sushiId : f.getSushis()) { 
                                    Sushi s = sushiMap.get(sushiId);
                                    if (s != null) { %>
                                        <li><%= s.getNom() %> - <%= s.getPrix() %> €</li>
                                    <% } else { %>
                                        <li class="missing-product">Sushi ID <%= sushiId %> (non trouvé)</li>
                                <% } } %>
                                </ul>
                            </li>
                        <% } %>
                        
                        <%-- Afficher les boissons avec vérification d'existence --%>
                        <% if (f.getBoissons() != null && !f.getBoissons().isEmpty()) { %>
                            <li><strong>Boissons:</strong>
                                <ul>
                                <% for (Integer boissonId : f.getBoissons()) { 
                                    Boisson b = boissonMap.get(boissonId);
                                    if (b != null) { %>
                                        <li><%= b.getNom() %> - <%= b.getPrix() %> €</li>
                                    <% } else { %>
                                        <li class="missing-product">Boisson ID <%= boissonId %> (non trouvé)</li>
                                <% } } %>
                                </ul>
                            </li>
                        <% } %>
                        
                        <%-- Afficher les ensembles avec vérification d'existence --%>
                        <% if (f.getEnsembles() != null && !f.getEnsembles().isEmpty()) { %>
                            <li><strong>Ensembles:</strong>
                                <ul>
                                <% for (Integer ensembleId : f.getEnsembles()) { 
                                    Ensemble e = ensembleMap.get(ensembleId);
                                    if (e != null) { %>
                                        <li><%= e.getNom() %> - <%= e.getPrix() %> €</li>
                                    <% } else { %>
                                        <li class="missing-product">Ensemble ID <%= ensembleId %> (non trouvé)</li>
                                <% } } %>
                                </ul>
                            </li>
                        <% } %>
                    </ul>
                </td>
                <td class="action-buttons">
                    <button onclick="showModifyForm(<%= f.getId() %>, '<%= f.getNom() %>', <%= f.getPrix() %>, '<%= f.getDescription() != null ? f.getDescription().replace("'", "\\'") : "" %>', '<%= f.getImage() != null ? f.getImage() : "" %>', <%= f.getSushis() != null ? f.getSushis() : "[]" %>, <%= f.getBoissons() != null ? f.getBoissons() : "[]" %>, <%= f.getEnsembles() != null ? f.getEnsembles() : "[]" %>)" class="modify-btn">
                        Modifier
                    </button>
                    <form action="DeleteFormuleServlet" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= f.getId() %>" />
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
    <h2>Créer une Formule</h2>
    
    <form action="CreateAndUpdateFormuleServlet" method="post">
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
        
        <label>Composition de la formule :</label><br>
        
        <!-- Section Sushis -->
        <div class="produit-section">
            <h3>Sushis</h3>
            <div id="createSushisContainer"></div>
            <button type="button" onclick="addProduitField('createSushisContainer', 'sushi', 'create')" class="add-produit-btn">+ Ajouter un sushi</button>
        </div>
        
        <!-- Section Boissons -->
        <div class="produit-section">
            <h3>Boissons</h3>
            <div id="createBoissonsContainer"></div>
            <button type="button" onclick="addProduitField('createBoissonsContainer', 'boisson', 'create')" class="add-produit-btn">+ Ajouter une boisson</button>
        </div>
        
        <!-- Section Ensembles -->
        <div class="produit-section">
            <h3>Ensembles</h3>
            <div id="createEnsemblesContainer"></div>
            <button type="button" onclick="addProduitField('createEnsemblesContainer', 'ensemble', 'create')" class="add-produit-btn">+ Ajouter un ensemble</button>
        </div>
        
        <button type="submit" class="save-btn">Créer la formule</button>
    </form>
</div>

<!-- Formulaire de modification -->
<div class="modify-box" id="modifyForm" style="display: none;">
    <h2>Modifier la Formule</h2>
    
    <form action="CreateAndUpdateFormuleServlet" method="post">
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
        
        <label>Composition de la formule :</label><br>
        
        <div class="produit-section">
            <h3>Sushis</h3>
            <div id="modifySushisContainer"></div>
            <button type="button" onclick="addProduitField('modifySushisContainer', 'sushi', 'modify')" class="add-produit-btn">+ Ajouter un sushi</button>
        </div>
        
        <div class="produit-section">
            <h3>Boissons</h3>
            <div id="modifyBoissonsContainer"></div>
            <button type="button" onclick="addProduitField('modifyBoissonsContainer', 'boisson', 'modify')" class="add-produit-btn">+ Ajouter une boisson</button>
        </div>
        
        <div class="produit-section">
            <h3>Ensembles</h3>
            <div id="modifyEnsemblesContainer"></div>
            <button type="button" onclick="addProduitField('modifyEnsemblesContainer', 'ensemble', 'modify')" class="add-produit-btn">+ Ajouter un ensemble</button>
        </div>
        
        <button type="submit" class="save-btn">Enregistrer</button>
        <button type="button" onclick="hideModifyForm()" class="cancel-btn">Annuler</button>
    </form>
</div>

<script>
    const sushisList = [
        <% for (Sushi s : sushisDisponibles) { %>
            { id: <%= s.getId() %>, nom: "<%= s.getNom() %>", prix: <%= s.getPrix() %> },
        <% } %>
    ];
    
    const boissonsList = [
        <% for (Boisson b : boissonsDisponibles) { %>
            { id: <%= b.getId() %>, nom: "<%= b.getNom() %>", prix: <%= b.getPrix() %> },
        <% } %>
    ];
    
    const ensemblesList = [
        <% for (Ensemble e : ensemblesDisponibles) { %>
            { id: <%= e.getId() %>, nom: "<%= e.getNom() %>", prix: <%= e.getPrix() %> },
        <% } %>
    ];
    
    let counters = { create: 0, modify: 0 };
    
    function addProduitField(containerId, type, formType) {
        const container = document.getElementById(containerId);
        const div = document.createElement('div');
        div.className = 'produit-item';
        
        let list = [];
        let fieldName = "";
        
        if (type === 'sushi') {
            list = sushisList;
            fieldName = "sushiIds";
        } else if (type === 'boisson') {
            list = boissonsList;
            fieldName = "boissonIds";
        } else {
            list = ensemblesList;
            fieldName = "ensembleIds";
        }
        
        const select = document.createElement('select');
        select.name = fieldName;
        select.style.padding = '5px';
        select.style.flex = '2';
        
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.text = '-- Choisir un ' + type + ' --';
        select.appendChild(defaultOption);
        
        for (let i = 0; i < list.length; i++) {
            const item = list[i];
            const option = document.createElement('option');
            option.value = item.id;
            option.text = item.nom + ' - ' + item.prix + ' €';
            select.appendChild(option);
        }
        
        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.textContent = 'X';
        removeBtn.onclick = function() { div.remove(); };
        
        div.appendChild(select);
        div.appendChild(removeBtn);
        container.appendChild(div);
    }
    
    function showModifyForm(id, nom, prix, description, image, sushis, boissons, ensembles) {
        document.getElementById('createForm').style.display = 'none';
        const modifyForm = document.getElementById('modifyForm');
        modifyForm.style.display = 'block';
        
        document.getElementById('originalId').value = id;
        document.getElementById('modifyId').value = id;
        document.getElementById('modifyNom').value = nom;
        document.getElementById('modifyPrix').value = prix;
        document.getElementById('modifyDescription').value = description;
        document.getElementById('modifyImage').value = image;
        
        // Charger les sushis existants
        const sushiContainer = document.getElementById('modifySushisContainer');
        sushiContainer.innerHTML = '';
        if (sushis) {
            for (let i = 0; i < sushis.length; i++) {
                addExistingField(sushiContainer, sushis[i], 'sushi');
            }
        }
        
        // Charger les boissons existantes
        const boissonContainer = document.getElementById('modifyBoissonsContainer');
        boissonContainer.innerHTML = '';
        if (boissons) {
            for (let i = 0; i < boissons.length; i++) {
                addExistingField(boissonContainer, boissons[i], 'boisson');
            }
        }
        
        // Charger les ensembles existants
        const ensembleContainer = document.getElementById('modifyEnsemblesContainer');
        ensembleContainer.innerHTML = '';
        if (ensembles) {
            for (let i = 0; i < ensembles.length; i++) {
                addExistingField(ensembleContainer, ensembles[i], 'ensemble');
            }
        }
        
        modifyForm.scrollIntoView({ behavior: 'smooth' });
    }
    
    function addExistingField(container, id, type) {
        const div = document.createElement('div');
        div.className = 'produit-item';
        
        let list = [];
        let fieldName = "";
        
        if (type === 'sushi') {
            list = sushisList;
            fieldName = "sushiIds";
        } else if (type === 'boisson') {
            list = boissonsList;
            fieldName = "boissonIds";
        } else {
            list = ensemblesList;
            fieldName = "ensembleIds";
        }
        
        const select = document.createElement('select');
        select.name = fieldName;
        select.style.padding = '5px';
        select.style.flex = '2';
        
        for (let i = 0; i < list.length; i++) {
            const item = list[i];
            const option = document.createElement('option');
            option.value = item.id;
            option.text = item.nom + ' - ' + item.prix + ' €';
            if (item.id === id) {
                option.selected = true;
            }
            select.appendChild(option);
        }
        
        const removeBtn = document.createElement('button');
        removeBtn.type = 'button';
        removeBtn.textContent = 'X';
        removeBtn.onclick = function() { div.remove(); };
        
        div.appendChild(select);
        div.appendChild(removeBtn);
        container.appendChild(div);
    }
    
    function hideModifyForm() {
        document.getElementById('modifyForm').style.display = 'none';
        document.getElementById('createForm').style.display = 'block';
    }
    
    document.addEventListener('DOMContentLoaded', function() {
        addProduitField('createSushisContainer', 'sushi', 'create');
        addProduitField('createBoissonsContainer', 'boisson', 'create');
        addProduitField('createEnsemblesContainer', 'ensemble', 'create');
    });
</script>

</body>
</html>