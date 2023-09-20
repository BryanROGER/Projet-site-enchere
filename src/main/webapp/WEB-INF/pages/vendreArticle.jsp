<%@page import="fr.eni.bo.Categorie"%>
<%@page import="fr.eni.bll.CategorieManager"%>
<%@page import="fr.eni.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>

	<h1>Vendre un article</h1>


	<div class="row mt-5">
		<div class="col-4 offset-4">
			<form action="" method="post">

				<div class="mb-3">
					<label for="article">Article :</label> <input class="input"
						type="text" name="article" id="article" placeholder="Article"
						required>
				</div>

				<div class="mb-3">
					<label for="description">Description :</label>
					<textarea class="input" name="description" id="description"
						placeholder="Entrez une description de l'article"></textarea>
				</div>

				<div class="mb-3">
					<label for="categorie">Catégorie :</label> <select name="categorie"
						class="input" id="categorie">
						<%
						for (Categorie categorie : CategorieManager.getInstance().selectionnerToutesLesCategories()) {
						%>
						<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="mb-3">
					<label for="mPrix">Mise à prix :</label> <input class="input"
						type="number" name="mPrix" id="mPrix" step="1" max="10000"
						required>
				</div>

				<div class="mb-3">
					<label for="dEnchere">Début de l'enchère :</label> <input
						class="input" type="date" name="dEnchere" id="dEnchere" required>
				</div>

				<div class="mb-3">
					<label for="fEnchere">Fin de l'enchère :</label> <input
						class="input" type="date" name="fEnchere" id="fEnchere" required>
				</div>

				<!-- Retrait-->

				<h1>Retrait de l'objet</h1>
				<div class="mb-3">
					<label for="rue">Rue :</label> <input class="input" type="text"
						name="rue" id="rue" maxlength="100" required>
				</div>

				<div class="mb-3">
					<label for="cp">Code Postal :</label> <input class="input"
						type="text" name="codePostal" id="cp" step="1000" min="0"
						maxlength="5" required>
				</div>

				<div class="mb-3">
					<label for="ville">Ville :</label> <input class="input" type="text"
						name="ville" id="ville" required>
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
