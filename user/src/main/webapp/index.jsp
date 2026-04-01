<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.Produit" %>
<%@ page import="metier.Sushi" %>
<%@ page import="metier.Boisson" %>
<%@ page import="metier.Ensemble" %>
<%@ page import="metier.Formule" %>

<%
    Produit produitPhare = (Produit) request.getAttribute("produitPhare");
    List<Produit> produitsAleatoires = (List<Produit>) request.getAttribute("produitsAleatoires");
    List<Produit> sliderProduits = (List<Produit>) request.getAttribute("sliderProduits");
%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SUSHI SHOP</title>
    <link rel="stylesheet" href="css/style.css">
    
</head>

<body>
	
	<jsp:include page="includes/header.jsp" />
	
    <div class="banner"></div>

    

    <section class="bienvenue fade-in">
        <h1>Bienvenue chez Sushi Shopping</h1>
        <p>Découvrez nos délicieux sushis et commandez votre pack préféré dès aujourd'hui.</p>
    </section>

    <!-- Slider dynamique avec des produits aléatoires -->
    <div class="slider">
        <%
            if (sliderProduits != null && !sliderProduits.isEmpty()) {
                for (Produit p : sliderProduits) {
                    String type = "";
                    if (p instanceof Sushi) type = "Sushi";
                    else if (p instanceof Boisson) type = "Boisson";
                    else if (p instanceof Ensemble) type = "Ensemble";
                    else if (p instanceof Formule) type = "Formule";
        %>
            <div class="slide">
                <img src="<%= p.getImage() != null && !p.getImage().isEmpty() ? p.getImage() : "img/plateau.jpg" %>" alt="<%= p.getNom() %>">
                <div class="slide-info">
                    <h3><%= p.getNom() %></h3>
                    <span class="slide-type"><%= type %></span>
                    <span class="slide-prix"><%= p.getPrix() %> €</span>
                </div>
            </div>
        <%
                }
            } else {
                // Images par défaut si pas de produits
        %>
            <div class="slide">
                <img src="img/plateau.jpg" alt="Plateau de sushis">
                <div class="slide-info">
                    <h3>Plateau de sushis</h3>
                    <span class="slide-type">Sushi</span>
                    <span class="slide-prix">24,90 €</span>
                </div>
            </div>
            <div class="slide">
                <img src="img/s.jpg" alt="Sushis variés">
                <div class="slide-info">
                    <h3>Sushis variés</h3>
                    <span class="slide-type">Sushi</span>
                    <span class="slide-prix">18,50 €</span>
                </div>
            </div>
            <div class="slide">
                <img src="img/numnum.jpeg" alt="Sushis appétissants">
                <div class="slide-info">
                    <h3>California rolls</h3>
                    <span class="slide-type">Sushi</span>
                    <span class="slide-prix">22,90 €</span>
                </div>
            </div>
        <%
            }
        %>
    </div>

    <!-- Produit phare -->
    <section class="produit-phare fade-in">
        <h2>Notre pack le plus commandé</h2>
        <div class="carte-phare">
            <img src="<%= produitPhare != null && produitPhare.getImage() != null && !produitPhare.getImage().isEmpty() ? produitPhare.getImage() : "img/plateau.jpg" %>" alt="<%= produitPhare != null ? produitPhare.getNom() : "Produit" %>">
            <div class="info-phare">
                <h3><%= produitPhare != null ? produitPhare.getNom() : "Farandole de sushis" %></h3>
                <p><%= produitPhare != null && produitPhare.getDescription() != null ? produitPhare.getDescription() : "Un assortiment complet de nos meilleurs produits" %></p>
                <span class="prix"><%= produitPhare != null ? produitPhare.getPrix() : "24,90" %> €</span>
            </div>
        </div>
    </section>

    <!-- Produits aléatoires -->
    <section class="produits-aleatoires fade-in">
        <h2>Découvrez aussi</h2>
        <div class="cartes-aleatoires">
            <%
                if (produitsAleatoires != null) {
                    for (Produit p : produitsAleatoires) {
                        String type = "";
                        if (p instanceof Sushi) type = "Sushi";
                        else if (p instanceof Boisson) type = "Boisson";
                        else if (p instanceof Ensemble) type = "Ensemble";
                        else if (p instanceof Formule) type = "Formule";
            %>
            <div class="carte-aleatoire">
                <img src="<%= p.getImage() != null && !p.getImage().isEmpty() ? p.getImage() : "img/plateau.jpg" %>" alt="<%= p.getNom() %>">
                <div class="info-aleatoire">
                    <h3><%= p.getNom() %></h3>
                    <span class="type"><%= type %></span>
                    <span class="prix"><%= p.getPrix() %> €</span>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </section>

    <section class="infos-restaurant fade-in">
        <div>
            <h2>Horaires</h2>
            <p>Lundi - Vendredi : 11h00 - 22h00</p>
            <p>Samedi - Dimanche : 12h00 - 23h00</p>
        </div>
        <div>
            <h2>Contact</h2>
            <p>Téléphone : 01 23 45 67 89</p>
            <p>Email : contact@sushishopping.fr</p>
        </div>
        <div>
            <h2>Localisation</h2>
            <p>123 Rue des Sushis, 75000 Paris</p>
            <iframe 
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2624.999999999!2d2.294481!3d48.858370!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zNDjCsDUxJzQ2LjgiTiAywrAxNic1My4xIkU!5e0!3m2!1sfr!2sfr!4v0000000000000"
                width="300"
                height="200"
                style="border:0;"
                allowfullscreen=""
                loading="lazy">
            </iframe>
        </div>
    </section>

    <section class="rgpd fade-in">
        <p>Nous respectons votre vie privée conformément au RGPD.</p>
    </section>

    <jsp:include page="includes/cookies.jsp" />

    <jsp:include page="includes/footer.jsp" />

    <script>
        function accepterCookies() {
            document.getElementById('cookiesBanner').style.display = 'none';
            document.cookie = "cookies_accepted=true; path=/; max-age=" + (60*60*24*365);
        }

        if (document.cookie.indexOf('cookies_accepted=') === -1) {
            document.getElementById('cookiesBanner').style.display = 'block';
        }

        let current = 0;
        const slides = document.querySelectorAll('.slider .slide');

        function showSlide(index) {
            slides.forEach((slide, i) => {
                slide.classList.toggle('active', i === index);
            });
        }

        function nextSlide() {
            if (slides.length > 0) {
                current = (current + 1) % slides.length;
                showSlide(current);
            }
        }

        if (slides.length > 0) {
            showSlide(current);
            setInterval(nextSlide, 3000);
        }

        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                }
            });
        });

        document.querySelectorAll('.fade-in').forEach(el => observer.observe(el));
    </script>

</body>
</html>