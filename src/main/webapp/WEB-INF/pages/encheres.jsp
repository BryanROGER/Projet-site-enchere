<%@ include file="/WEB-INF/fragments/header.jspf"%>


<main>

<h2>Liste des ench�res</h2>

<h3>Filtres :</h3>
<form action="" method="get">
<!-- Ajouter via bootstrap une barre de recherche -->
<label>Cat�gorie :</label>
<select>
	<option>Cat�gorie 1</option>

</select>

<button type="submit">Rechercher</button>
</form>


<c:forEach items="${articles }" var="article">
<div>
<p>${article.getNomArticle() }</p>
<p>Prix : ${article.getMiseAPrix() }</p>
<p>Fin de l'ench�re : ${article.getDateFinEncheres() }</p>
<p>Vendeur : <a href="${pageContext.request.contextPath}/detail-utilisateur?id=${article.getVendeur().getNoUtilisateur() }">${article.getVendeur().getPseudo() }</a></p>
</div>
</c:forEach>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
