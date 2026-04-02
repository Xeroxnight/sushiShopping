<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.Reservation" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>


<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
    //System.out.println("=== JSP: reservations = " + reservations);
    if (reservations != null) {
        //System.out.println("=== JSP: nombre = " + reservations.size());
    }
    String message = (String) session.getAttribute("message");
    String erreur = (String) session.getAttribute("erreur");
    if (message != null) session.removeAttribute("message");
    if (erreur != null) session.removeAttribute("erreur");
%>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Réservation - Sushi Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .reservation-container {
            max-width: 900px;
            margin: 30px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            text-align: center;
        }
        
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        
        .message {
            background: #d4edda;
            color: #155724;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        .erreur {
            background: #f8d7da;
            color: #721c24;
        }
        
        .restaurant-image {
            position: relative;
            display: inline-block;
            width: 100%;
            max-width: 800px;
            margin: 0 auto;
        }
        
        .restaurant-image img {
            width: 100%;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        }
        
        .table-buttons {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
        }
        
        .btn-table {
            position: absolute;
            background: #e74c3c;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 20px;
            cursor: pointer;
            font-weight: bold;
            box-shadow: 0 2px 5px rgba(0,0,0,0.3);
            transition: transform 0.2s;
            z-index: 10;
        }
        
        .btn-table:hover:not(.reserved) {
            transform: scale(1.1);
            background: #c0392b;
        }
        
        .btn-table.reserved {
            background: #95a5a6;
            cursor: not-allowed;
            opacity: 0.6;
        }
        
        .btn-table.selected {
            border: 3px solid #27ae60;
            box-shadow: 0 0 10px #27ae60;
            background: #e74c3c;
        }
        
        .table-1 { top: 68%; left: 25%; }
        .table-2 { top: 70%; left: 65%; }
        
        .reservation-form {
            margin-top: 30px;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 10px;
        }
        
        .form-group {
            margin: 15px 0;
        }
        
        .form-group label {
            font-weight: bold;
            margin-right: 10px;
        }
        
        .form-group input, .form-group select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        
        .btn-valider {
            background: #27ae60;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .btn-valider:hover {
            background: #229954;
        }
        
        .btn-retour {
            background: #95a5a6;
            margin-right: 10px;
        }
        
        .btn-retour:hover {
            background: #7f8c8d;
        }
        
        .selected-table {
            margin-top: 15px;
            font-weight: bold;
            padding: 10px;
            border-radius: 5px;
            background: #f0f0f0;
        }
        
        .info-creneau {
            margin-top: 10px;
            font-size: 14px;
            padding: 8px;
            border-radius: 5px;
        }
        
        .info-dispo {
            background: #d4edda;
            color: #155724;
        }
        
        .info-indispo {
            background: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>

<jsp:include page="includes/header.jsp" />

<div class="reservation-container">
    <h1>Réservation pour le restaurant</h1>
    
    <% if (message != null) { %>
        <div class="message"><%= message %></div>
    <% } %>
    
    <% if (erreur != null) { %>
        <div class="message erreur"><%= erreur %></div>
    <% } %>
    
    <div class="restaurant-image">
        <img src="https://cdn.thefork.com/tf-lab/image/upload/w_640,c_fill,q_auto,f_auto/restaurant/cb4ddbdc-dcd0-4273-96ee-f97374aedf62/97bc81ab-5272-4af9-8b9a-e35d221a8534.jpg" alt="Restaurant">
        <div class="table-buttons">
		    <button id="btnTable1"  data-table="1" class="btn-table table-1" onclick="selectionnerTable(event, 1)">Table 1</button>
		    <button id="btnTable2"  data-table="2" class="btn-table table-2" onclick="selectionnerTable(event, 2)">Table 2</button>
		</div>
    </div>
    
    <div class="selected-table" id="selectedTableInfo">
        Aucune table sélectionnée
    </div>
    
    <div class="reservation-form">
        <div class="form-group">
            <label>Date :</label>
            <input type="date" id="dateReservation" onchange="verifierDisponibilite()">
        </div>
        
        <div class="form-group">
            <label>Heure :</label>
            <select id="heureReservation" onchange="verifierDisponibilite()">
                <option value="">-- Choisir une heure --</option>
                <option value="15:00">15h00</option>
                <option value="16:00">16h00</option>
                <option value="17:00">17h00</option>
                <option value="18:00">18h00</option>
                <option value="19:00">19h00</option>
                <option value="20:00">20h00</option>
                <option value="21:00">21h00</option>
                <option value="22:00">22h00</option>
                <option value="23:00">23h00</option>
            </select>
        </div>
        
        <div class="form-group">
            <label>Nombre de personnes :</label>
            <input type="number" id="nbPersonnes" min="1" max="10" value="2">
        </div>
        
        <div class="info-creneau" id="infoCreneau"></div>
        
        <form id="reservationForm" action="validerReservation" method="post" style="margin-top: 20px;">
            <input type="hidden" name="date" id="formDate">
            <input type="hidden" name="heure" id="formHeure">
            <input type="hidden" name="tableNumero" id="formTable">
            <input type="hidden" name="nbPersonnes" id="formNbPersonnes">
            
            <button type="button" class="btn-valider btn-retour" onclick="window.location.href='panier'">Retour au panier</button>
            <button type="button" class="btn-valider" id="btnConfirmer" onclick="confirmerReservation()">Confirmer la réservation</button>
        </form>
    </div>
</div>

<script>
    // Toutes les réservations depuis le serveur
    const reservations = [
        <%
            if (reservations != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < reservations.size(); i++) {
                    Reservation r = reservations.get(i);
                    String dateStr = sdf.format(r.getDate());
                    String heureStr = new java.text.SimpleDateFormat("HH:mm").format(r.getHeure());
        %>
            {
                date: "<%= dateStr %>",
                heure: "<%= heureStr %>",
                tableNumero: <%= r.getTableNumero() %>
            }<%= i < reservations.size() - 1 ? "," : "" %>
        <%
                }
            }
        %>
    ];
    
    let tableSelectionnee = null;
    
    // Initialisation : date minimale = aujourd'hui
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('dateReservation').min = today;
    document.getElementById('dateReservation').value = today;
    
    function verifierDisponibilite() {
        const date = document.getElementById('dateReservation').value;
        const heure = document.getElementById('heureReservation').value;
        const infoDiv = document.getElementById('infoCreneau');
        console.log("Date sélectionnée:", date);
        console.log("Heure sélectionnée:", heure);
        console.log("Réservations:", reservations);

        reservations.forEach(res => {
            console.log("Comparaison:", res.date, "===", date, "?", res.date === date);
            if (res.date === date) {
                console.log("Même date, comparaison heure...");
            }
        });
        
        if (!date || !heure) {
            // Réactiver toutes les tables
            document.querySelectorAll('.btn-table').forEach(btn => {
                btn.classList.remove('reserved');
                btn.disabled = false;
            });
            infoDiv.innerHTML = '';
            infoDiv.className = 'info-creneau';
            return;
        }
        
        // Parcourir les tables (1 et 2)
        const tablesReservees = [];
        
        reservations.forEach(res => {
            if (res.date === date) {
                const heureRes = res.heure;
                const heureResInt = parseInt(heureRes.split(':')[0]);
                const heureChoisieInt = parseInt(heure.split(':')[0]);
                
                // Vérifier si dans le créneau de 2 heures (1h avant, 1h après)
                if (Math.abs(heureResInt - heureChoisieInt) <= 1) {
                    tablesReservees.push(res.tableNumero);
                }
            }
        });
        
        // Mettre à jour l'affichage des tables
        document.querySelectorAll('.btn-table').forEach(btn => {
            const tableNum = parseInt(btn.getAttribute('data-table'));
            if (tablesReservees.includes(tableNum)) {
                btn.classList.add('reserved');
                btn.disabled = true;
            } else {
                btn.classList.remove('reserved');
                btn.disabled = false;
            }
        });
        
        // Afficher l'info
        if (tablesReservees.length > 0) {
            infoDiv.innerHTML = '⚠️ Tables déjà réservées pour ce créneau : ' + tablesReservees.join(', ');
            infoDiv.className = 'info-creneau info-indispo';
        } else {
            infoDiv.innerHTML = '✅ Toutes les tables sont disponibles pour ce créneau';
            infoDiv.className = 'info-creneau info-dispo';
        }
        
        // Si la table sélectionnée est devenue réservée, désélectionner
        if (tableSelectionnee && tablesReservees.includes(tableSelectionnee)) {
            tableSelectionnee = null;
            document.getElementById('selectedTableInfo').innerHTML = 'Aucune table sélectionnée (celle-ci n\'est plus disponible)';
            document.getElementById('selectedTableInfo').style.background = '#f8d7da';
            
            // Enlever le surlignage
            document.querySelectorAll('.btn-table').forEach(btn => {
                btn.classList.remove('selected');
            });
        }
    }
    
    function selectionnerTable(event, numero) {
	        event.preventDefault();
	        console.log("Clic sur table " + numero);
	        
	        let btn;
	        if (numero === 1) {
	            btn = document.getElementById('btnTable1');
	        } else {
	            btn = document.getElementById('btnTable2');
	        }
	        
	        if (!btn) {
	            console.log("Bouton non trouvé");
	            return;
	        }
	        
	        if (btn.disabled) {
	            alert('Cette table n\'est pas disponible pour le créneau choisi');
	            return;
	        }
	        
	        // Retirer la sélection des autres tables
	        if (numero === 1) {
	            document.getElementById('btnTable2').classList.remove('selected');
	        } else {
	            document.getElementById('btnTable1').classList.remove('selected');
	        }
	        
	        // Mettre en évidence la table sélectionnée
	        btn.classList.add('selected');
	        
	        tableSelectionnee = numero;
	        document.getElementById('selectedTableInfo').innerHTML = '✅ Table ' + numero + ' sélectionnée';
	        document.getElementById('selectedTableInfo').style.background = '#d4edda';
	        document.getElementById('selectedTableInfo').style.color = '#155724';
	    }
    
    function confirmerReservation() {
        const date = document.getElementById('dateReservation').value;
        const heure = document.getElementById('heureReservation').value;
        const nbPersonnes = document.getElementById('nbPersonnes').value;
        console.log("Date:", document.getElementById('formDate').value);
        console.log("Heure:", document.getElementById('formHeure').value);
        console.log("Table:", document.getElementById('formTable').value);
        
        if (!tableSelectionnee) {
            alert('Veuillez sélectionner une table');
            return;
        }
        
        if (!date) {
            alert('Veuillez sélectionner une date');
            return;
        }
        
        if (!heure) {
            alert('Veuillez sélectionner une heure');
            return;
        }
        
        // Vérifier une dernière fois la disponibilité
        let disponible = true;
        const heureInt = parseInt(heure.split(':')[0]);
        
        reservations.forEach(res => {
            if (res.date === date) {
                const heureResInt = parseInt(res.heure.split(':')[0]);
                if (Math.abs(heureResInt - heureInt) <= 1 && res.tableNumero === tableSelectionnee) {
                    disponible = false;
                }
            }
        });
        
        if (!disponible) {
            alert('Désolé, cette table n\'est plus disponible pour ce créneau');
            verifierDisponibilite();
            return;
        }
        
        // Remplir le formulaire et soumettre
        document.getElementById('formDate').value = date;
        document.getElementById('formHeure').value = heure;
        document.getElementById('formTable').value = tableSelectionnee;
        document.getElementById('formNbPersonnes').value = nbPersonnes;
        
        document.getElementById('reservationForm').submit();
    }
    
    // Vérifier la disponibilité au chargement
    verifierDisponibilite();
    document.addEventListener('DOMContentLoaded', function() {
        verifierDisponibilite();
    });
</script>

<jsp:include page="includes/footer.jsp" />

</body>
</html>