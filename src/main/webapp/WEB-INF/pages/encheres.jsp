<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>


<main>

<h2>Liste des enchères</h2>

<h3>Filtres :</h3>
<form action="" method="get">
<!-- Ajouter via bootstrap une barre de recherche -->
<label>Catégorie :</label>
<select>
	<option>Catégorie 1</option>

</select>

<button type="submit">Rechercher</button>
</form>


<c:forEach items="${encheres }" var="enchere">
<div>
<p>${enchere.getArticle().getNomArticle() }</p>
<p>Prix : ${enchere.getArticle().getMiseAPrix() }</p>
<p>Fin de l'enchère : ${enchere.getArticle().getDateFinEncheres() }</p>
<p>Vendeur : <a href="${pageContext.request.contextPath}/profil?id=${enchere.getArticle().getVendeur().getNoUtilisateur() }">${enchere.getArticle().getVendeur().getPseudo() }</a></p>
</div>
</c:forEach>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
