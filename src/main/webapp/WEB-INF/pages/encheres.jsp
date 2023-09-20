<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>



<main>
<h2 class="mb-5 text-center">Liste des enchères</h2>

<form class="row" action="" method="get" >
    <div class="offset-lg-3 col-lg-6 d-flex flex-column">
        <p class="m-0">Filtres&nbsp;:</p>
       </div>
        <input name="string_filter" type="text" class="form-control m-2" placeholder="Le nom de l'article contient" value="${stringFilter}">
        <p class="d-flex flex-row">
            <label class="col-lg-3 pt-3" for="categories">Catégorie&nbsp;:</label>
            <select name="categorie" id="categories" class="col-lg-9 mt-2 form-control">
                
                <c:forEach items="${categories}" var="categorie">
                    <option name="categorie" value="${categorie.getLibelle() }">${categorie.getLibelle()}</option>
                </c:forEach>
            </select>
 <input type="submit" class="btn btn-info btn-lg btn-block mt-2" id="#searchButton" value="Rechercher"/>
</form>


<c:forEach items="${articles }" var="article">
<div class="container">
    <div class="row d-flex justify-content-between">
<p>${article.getNomArticle() }</p>
<p>Prix : ${article.getMiseAPrix() }</p>
<p>Fin de l'enchère : ${article.getDateFinEncheres() }</p>
<p>Vendeur : <a href="${pageContext.request.contextPath}/profil?id=${article.getVendeur().getNoUtilisateur() }">${article.getVendeur().getPseudo() }</a></p>
</div>
</div>
</c:forEach>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
