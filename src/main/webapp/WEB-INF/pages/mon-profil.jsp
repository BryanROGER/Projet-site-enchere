<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<main>

	<h2>Mon profil</h2>

	<div class="w-50">


		<form action="${pageContext.request.contextPath}/mon-profil"
			method="post">
			<c:if test="${ ! empty error }">
				<div class="alert alert-danger">${ error }</div>
			</c:if>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input class="form-control" id="pseudo" name="pseudo" type="text"
							value="${user.getPseudo() }" required="required"
							readonly="readonly" /> <label for="pseudo" class="form-label">Pseudo</label>
					</div>

				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input id="nom" class="form-control" name="nom" type="text"
							value="${user.getNom() }" required="required"> <label
							for="nom" class="form-label" for="nom">Nom</label>

					</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
						<input id="prenom" name="prenom" type="text" required="required"
							value="${user.getPrenom() }" class="form-control"> <label
							for="prenom" class="form-label">Prénom</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input id="email" name="email" type="text" required="required"
							value="${user.getEmail() }" class="form-control"> <label
							for="email" class="form-label">Email</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input id="telephone" name="telephone" type="text"
							required="required" value="${user.getTelephone() }"
							class="form-control"> <label for="telephone"
							class="form-label">Teléphone</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input id="rue" name="rue" type="text" required="required"
							value="${user.getRue() }" class="form-control"> <label
							for="rue" class="form-label">Adresse</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input id="code_postal" name="code_postal" type="number"
							required="required" value="${user.getCodePostal() }"
							class="form-control"> <label for="code_postal"
							class="form-label">Code postal</label>

					</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
						<input id="ville" name="ville" type="text" required="required"
							value="${user.getVille() }" class="form-control"> <label
							for="ville" class="form-label">Ville</label>

					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-12">
					<div class="form-floating">
						<input id="mot_de_passe" name="mot_de_passe" type="password"
							required="required" class="form-control"> <label
							for="mot_de_passe" class="form-label">Ancien mot de passe</label>
					</div>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input id="nouveau_mdp" name="nouveau_mdp" type="password"
							class="form-control"> <label for="nouveau_mdp"
							class="form-label">Nouveau mot de passe</label>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
						<input id="confirmation_mdp" name="confirmation_mdp"
							type="password" class="form-control"> <label
							for="confirmation_mdp" class="form-label">Confirmation du
							mot de passe</label>

					</div>
				</div>
			</div>
			<div class="d-flex justify-content-around">

				<div>
					<button id="inscription" type="submit" name="bouton" value="maj"
						class="btn btn-success">Enregistrer profil</button>
				</div>
				<div>
					<button id="annuler" type="submit" name="bouton" value="suppr"
						class="btn btn-danger"
						onclick="return confirm('Voulez-vous vraiment supprimer votre compte ? Cette action sera définitive')">Supprimer le compte</button>
				</div>

			</div>
		</form>
	</div>
</main>



<%@ include file="/WEB-INF/fragments/footer.jspf"%>
