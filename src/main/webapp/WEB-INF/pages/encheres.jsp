<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>



<main>
	<h2 class="mb-5 text-center">Liste des enchères</h2>


<form class="row" action="${ pageContext.request.contextPath }/encheres" method="get" >
    <div class="col-4">
        <p class="m-0">Filtres&nbsp;:</p>
       </div>
        <input name="string_filter" type="text" class="form-control m-2" placeholder="Le nom de l'article contient" value="${stringFilter}">
        <p class="d-flex flex-row">

            <label class="col-6" for="categories">Catégorie&nbsp;:</label>
            <select name="categorie" id="categories" class="col mt-2 form-control">

                
                <c:forEach items="${categories}" var="categorie" >
                    <option  value="${categorie.getNoCategorie() }">${categorie.getLibelle()}</option>
                </c:forEach>
            </select>
 <input type="submit" class="btn btn-info btn-lg btn-block mt-2" id="#searchButton" value="Rechercher"/>
</form>




	<c:forEach items="${encheres }" var="enchere">
		<div>
			<p><a href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}">${enchere.getArticle().getNomArticle() }</a></p>
			<p>Prix : ${enchere.getArticle().getMiseAPrix() }</p>
			<p>Fin de l'enchère : ${enchere.getArticle().getDateFinEncheres() }</p>
			<p>
				Vendeur : <a
					href="${pageContext.request.contextPath}/profil?id=${enchere.getArticle().getVendeur().getNoUtilisateur() }">${enchere.getArticle().getVendeur().getPseudo() }</a>
			</p>

		</div>
	</c:forEach>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
