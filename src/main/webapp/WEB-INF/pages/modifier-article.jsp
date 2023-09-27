<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<c:if test="${ ! empty error }">
		<div class="alert alert-danger">${ error }</div>
	</c:if>

	<h2>Modifier l'article</h2>
	<div class="d-flex justify-content-center">
		

		<form action="" method="post">
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" type="text" name="nom"
							value="${enchere.getArticle().getNomArticle() }"> <label>Nom
							de l'article :</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" type="text" name="description"
							value="${enchere.getArticle().getDescription() }"> <label>Description
							:</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<select name="categorie" class="form-select" id="categories"
							aria-label="Catégories">
							<c:forEach items="${categories }" var="categorie">
								<c:if
									test="${categorie.getLibelle() eq enchere.getArticle().getCategorieArticle().getLibelle() }">
									<option value="${categorie.getLibelle() }" selected="selected">${categorie.getLibelle() }</option>
								</c:if>
								<c:if
									test="${ categorie.getLibelle() ne enchere.getArticle().getCategorieArticle().getLibelle() }">
									<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
								</c:if>
							</c:forEach>
						</select> <label for="categories">Catégories</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" type="number" name="mise_a_prix"
							value="${enchere.getArticle().getMiseAPrix() }"> <label>Mise
							à prix :</label>
					</div>
				</div>
			</div>

			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="dEnchere" type="date"
							name="debut_enchere" id="enchere"
							placeholder="Début de l'enchère" data-sb-validations="required"
							required="required"
							value="${enchere.getArticle().getDateDebutEncheres() }" /> <label
							for="dEnchere">Début de l'enchère</label>
						<div class="invalid-feedback"
							data-sb-feedback="debutDeLenchere:required">Veuillez saisir
							une date de début d'enchère.</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
						<input class="form-control" id="fEnchere" type="date"
							name="fin_enchere" id="fEnchere" placeholder="Fin de l'enchère"
							data-sb-validations="required" required="required"
							value="${enchere.getArticle().getDateFinEncheres()}" /> <label
							for="fEnchere">Fin de l'enchère</label>
						<div class="invalid-feedback"
							data-sb-feedback="finDeLenchere:required">Veuillez saisir
							une date de fin d'enchère.</div>
					</div>
				</div>
			</div>
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
			<div class="d-flex justify-content-center">
				<div class="col-md-7 d-flex justify-content-around">
					<a href="${pageContext.request.contextPath}/encheres" class="mr-5">
						<button type="submit" class="btn btn-success">Enregistrer</button>
					</a>
					<button type="reset" class="btn btn-danger">Annuler</button>
				</div>
			</div>
		</form>
	</div>
</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
