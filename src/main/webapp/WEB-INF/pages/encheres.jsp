<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>



<main>
	<h2 class="mb-5 text-center">Liste des enchères</h2>

<<<<<<< HEAD
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
=======
	<form class="row" action="" method="get">
		<div class="offset-lg-3 col-lg-6 d-flex flex-column">
			<p class="m-0">Filtres&nbsp;:</p>

			<input name="string_filter" type="text" class="form-control m-2"
				placeholder="Le nom de l'article contient" value="${stringFilter}">
			<p class="d-flex flex-row">
				<label class="col-lg-3 pt-3" for="categories">Catégorie&nbsp;:</label>
				<select name="category_filter" id="categories"
					class="col-lg-9 mt-2 form-control">
					<option value="all">Toutes</option>
					<c:forEach items="${categories}" var="category">
						<c:choose>
							<c:when test="${categoryFilter != 'all'}">
								<c:choose>
									<c:when test="${categoryFilter == category.noCategorie}">
										<option selected value="${category.noCategorie}">${category.libelle}</option>
									</c:when>
									<c:otherwise>
										<option value="${category.noCategorie}">${category.libelle}</option>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<option value="${category.noCategorie}">${category.libelle}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <input type="submit" class="btn btn-info btn-lg btn-block mt-2"
					id="#searchButton" value="Rechercher" />
		</div>
	</form>
>>>>>>> 8aa9f7d46ee74a5066daa48b7a7ee6e5d69f82da



	<c:forEach items="${encheres }" var="enchere">
		<div>
			<p>${enchere.getArticle().getNomArticle() }</p>
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
