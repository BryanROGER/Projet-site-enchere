<%@page import="fr.eni.bo.Categorie"%>
<%@page import="fr.eni.bll.CategorieManager"%>
<%@page import="fr.eni.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>

	<c:if test="${ ! empty error }">
		<div class="alert alert-danger">${ error }</div>
	</c:if>
	<h2>Vendre un article</h2>
	<div class="container px-5 my-5">
		<form id="contactForm"
			action="${pageContext.request.contextPath}/vendre" method="post">
			<div class="form-floating mb-3">
				<input class="form-control" id="article" name="nom" type="text"
					placeholder="Article" data-sb-validations="required" /> <label
					for="article">Article</label>
				<div class="invalid-feedback" data-sb-feedback="article:required">Le
					nom de l'article est obligatoire.</div>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" id="description" type="text"
					placeholder="Description" style="height: 5rem;"
					data-sb-validations="required" name="description"></textarea>
				<label for="description">Description</label>
				<div class="invalid-feedback"
					data-sb-feedback="description:required">Veuillez saisir une
					description de l'article</div>
			</div>

			<div class="form-floating mb-3">
				<select name="categorie" class="form-select" id="categories" aria-label="Catégories">
					<c:forEach items="${categories }" var="categorie">
						<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
					</c:forEach>
				</select> <label for="categories">Catégories</label>
			</div>
			<div class="form-floating mb-3">
				<input class="form-control" id="mPrix" type="number" name="prix"
					placeholder="Mise à prix " data-sb-validations="required" required="required"/> <label
					for="mPrix">Mise à prix </label>
				<div class="invalid-feedback" data-sb-feedback="miseAPrix:required">Veuillez
					saisir une mise à prix de l'article.</div>
			</div>


			<div class="form-floating mb-3">
				<input class="form-control" id="dEnchere" type="date"
					name="debut_enchere" id="enchere" placeholder="Début de l'enchère"
					data-sb-validations="required" required="required" /> <label for="dEnchere">Début
					de l'enchère</label>
				<div class="invalid-feedback"
					data-sb-feedback="debutDeLenchere:required">Veuillez saisir
					une date de début d'enchère.</div>
			</div>

			<div class="form-floating mb-3">
				<input class="form-control" id="fEnchere" type="date"
					name="fin_enchere" id="fEnchere" placeholder="Fin de l'enchère"
					data-sb-validations="required" required="required"/> <label for="fEnchere">Fin
					de l'enchère</label>
				<div class="invalid-feedback"
					data-sb-feedback="finDeLenchere:required">Veuillez saisir une
					date de fin d'enchère.</div>
			</div>





			<!-- Retrait-->
			<h2>Point de retrait :</h2>
			<div class="form-floating mb-3">
				<input class="form-control" id="rue" name="rue" type="text"
					placeholder="Adresse : " value="${user.getRue() }"
					data-sb-validations="required" /> <label for="rue">Adresse
					: </label>
				<div class="invalid-feedback" data-sb-feedback="rue:required">Veuillez
					saisir votre adresse.</div>
			</div>
			<div class="form-floating mb-3">
				<input class="form-control" id="code_postal" name="code_postal"
					type="text" placeholder="Code postal : "
					value="${user.getCodePostal() }" data-sb-validations="required" />
				<label for="code_postal">Code postal : </label>
				<div class="invalid-feedback" data-sb-feedback="codePostal:required">Veuillez
					saisir votre code postal.</div>
			</div>
			<div class="form-floating mb-3">
				<input class="form-control" id="ville" name="ville" type="text"
					placeholder="Ville" data-sb-validations="required"
					value="${user.getVille() }" /> <label for="ville">Ville</label>
				<div class="invalid-feedback" data-sb-feedback="ville:required">Ville
					is required.</div>
			</div>

			<div class="d-none" id="submitSuccessMessage">
				<div class="text-center mb-3">
					<div class="fw-bolder">Form submission successful!</div>
				</div>
			</div>
			<div class="d-none" id="submitErrorMessage">
				<div class="text-center text-danger mb-3">Error sending
					message!</div>
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
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
