<%@page import="fr.eni.bo.Categorie"%>
<%@page import="fr.eni.bll.CategorieManager"%>
<%@page import="fr.eni.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>

	<h1>Vendre un article</h1>


	<form class="row g-3" action="${pageContext.request.contextPath}/vendre" method="post">
				 <div class="col-8">
					<label for="article">Article :</label> <input class="input"
						type="text" name="nom" id="article" placeholder="Article"
						required>
				</div>

				 <div class="col-8">
					<label for="description">Description :</label>
					<textarea class="input" name="description" id="description"
						placeholder="Entrez une description de l'article"></textarea>
				</div>

				 <div class="col-8">
					<label for="categorie">Catégorie :</label> 
					<select name="categorie" class="input" id="categorie" >
						<c:forEach items="${categories }" var="categorie"> 
							<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
						</c:forEach>
					</select>
				</div>
				 <div class="col-8">
					<label for="mPrix">Mise à prix :</label> <input class="input"
						type="number" name="prix" id="mPrix" step="1" max="10000"
						required>
				</div>

				 <div class="col-md-6">
					<label for="dEnchere">Début de l'enchère :</label> <input
						class="input" type="date" name="debut_enchere" id="dEnchere" required>
				</div>

				 <div class="col-md-6">
					<label for="fEnchere">Fin de l'enchère :</label> <input
						class="input" type="date" name="fin_enchere" id="fEnchere" required>
				</div>

				<!-- Retrait-->

				<h1>Retrait de l'objet</h1>
				 <div class="col-12">
					<label for="rue">Rue :</label> <input class="input" type="text"
						name="rue" id="rue" maxlength="100" required value="${user.getRue() }">
				</div>

				<div class="col-md-6">
					<label for="cp">Code Postal :</label> <input class="input"
						type="number" name="code_postal" id="cp"  min="0"
						maxlength="5" required value="${user.getCodePostal() }">
				</div>

				<div class="col-md-6">
					<label for="ville">Ville :</label> <input class="input" type="text"
						name="ville" id="ville" required value="${user.getVille() }">
				</div>
				<div class="form-row">
					<input type="submit"
						class="btn btn-lg btn-block btn-success offset-1 col-4 offset-md-4 col-md-2">
					<a href="${pageContext.request.contextPath}/encheres"
						class="offset-1 col-4 offset-md-1 col-md-2">
						<button type="button" class="btn btn-lg btn-block btn-danger">Annuler</button>
					</a>
				</div>

			</form>
		
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
