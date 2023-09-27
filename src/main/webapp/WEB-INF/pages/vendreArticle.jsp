<%@page import="fr.eni.bo.Categorie"%>
<%@page import="fr.eni.bll.CategorieManager"%>
<%@page import="fr.eni.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<h2>Vendre un article</h2>

	<div class="w-50">
		<c:if test="${ ! empty error }">
			<div class="alert alert-danger">${ error }</div>
		</c:if>
		<form id="contactForm"
			action="${pageContext.request.contextPath}/vendre" method="post">
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" id="article" name="nom" type="text"
							placeholder="Article" data-sb-validations="required" /> <label
							for="article">Article</label>
						<div class="invalid-feedback" data-sb-feedback="article:required">Le
							nom de l'article est obligatoire.</div>
					</div>
				</div>
			</div>

			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<textarea class="form-control" id="description"
							placeholder="Description" style="height: 5rem;"
							data-sb-validations="required" name="description"></textarea>
						<label for="description">Description</label>
						<div class="invalid-feedback"
							data-sb-feedback="description:required">Veuillez saisir une
							description de l'article</div>
					</div>
				</div>
			</div>

			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<select name="categorie" class="form-select" id="categories"
							aria-label="Catégories">
							<c:forEach items="${categories }" var="categorie">
								<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
							</c:forEach>
						</select> <label for="categories">Catégories</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" id="mPrix" type="number" name="prix"
							placeholder="Mise à prix " data-sb-validations="required"
							required="required" /> <label for="mPrix">Mise à prix </label>
						<div class="invalid-feedback"
							data-sb-feedback="miseAPrix:required">Veuillez saisir une
							mise à prix de l'article.</div>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="dEnchere" type="date"
							name="debut_enchere" id="enchere"
							placeholder="Début de l'enchère" data-sb-validations="required"
							required="required" /> <label for="dEnchere">Début de
							l'enchère</label>
						<div class="invalid-feedback"
							data-sb-feedback="debutDeLenchere:required">Veuillez saisir
							une date de début d'enchère.</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="fEnchere" type="date"
							name="fin_enchere" id="fEnchere" placeholder="Fin de l'enchère"
							data-sb-validations="required" required="required" /> <label
							for="fEnchere">Fin de l'enchère</label>
						<div class="invalid-feedback"
							data-sb-feedback="finDeLenchere:required">Veuillez saisir
							une date de fin d'enchère.</div>
					</div>
				</div>
			</div>





			<!-- Retrait-->
			<h2>Point de retrait :</h2>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" id="rue" name="rue" type="text"
							placeholder="Adresse : " value="${user.getRue() }"
							data-sb-validations="required" /> <label for="rue">Adresse
							: </label>
						<div class="invalid-feedback" data-sb-feedback="rue:required">Veuillez
							saisir votre adresse.</div>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="code_postal" name="code_postal"
							type="text" placeholder="Code postal : "
							value="${user.getCodePostal() }" data-sb-validations="required" />
						<label for="code_postal">Code postal : </label>
						<div class="invalid-feedback"
							data-sb-feedback="codePostal:required">Veuillez saisir
							votre code postal.</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="ville" name="ville" type="text"
							placeholder="Ville" data-sb-validations="required"
							value="${user.getVille() }" /> <label for="ville">Ville</label>
						<div class="invalid-feedback" data-sb-feedback="ville:required">Ville
							is required.</div>
					</div>
				</div>
			</div>

			<div class="d-none" id="submitSuccessMessage">
				<div class="text-center mb-3">
					<div class="fw-bolder">Article bien renseigné !</div>
				</div>
			</div>
			<div class="d-none" id="submitErrorMessage">
				<div class="text-center text-danger mb-3">Erreur lors de la
					saisie article !</div>
			</div>
			<div class="d-flex justify-content-center">
				<div class="col-md-4">
					<a href="${pageContext.request.contextPath}/encheres">
						<button type="submit" class="btn btn-success">Vendre</button>
					</a>
					<button type="reset" class="btn btn-danger">Annuler</button>
				</div>
			</div>
		</form>
	</div>

</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
