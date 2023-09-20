<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>
	<h2 class="mb-md-5 text-center">Mon profil</h2>
	<div class="form-group-md row">
		<label class="col-form-label col-2 col-md-1" for="pseudo">Pseudo
			<i class="text-danger">*</i>
		</label>
		<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
			<input required type="text" class="form-control" name="pseudo"
				value="${utilisateur.getPseudo() }" id="pseudo" >
		</div>
		<label class="col-form-label col-2 offset-md-1 col-md-1" for="nom">Nom
			<i class="text-danger">*</i>
		</label>
		<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
			<input required type="text" class="form-control" name="nom"
				value="${ utilisateur.getNom() }" id="nom">
		</div>
		<div class="form-group-md-md row">
			<label class="col-form-label col-2 col-md-1" for="prenom">Prénom
				<i class="text-danger">*</i>
			</label>
		</div>
		<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
			<input required type="text" class="form-control" name="prenom"
				value="${ utilisateur.getPrenom() }" id="prenom">
		</div>
		<label class="col-form-label col-2 offset-md-1 col-md-1" for="mail">Email
			<i class="text-danger">*</i>
		</label>
		<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
			<input required type="text" class="form-control" name="mail"
				id="mail" value="${ utilisateur.getEmail() }">
		</div>
		<div class="form-group-md row">
			<label class="col-form-label col-2 col-md-1" for="telephone">Téléphone
				<i class="text-danger">*</i>
			</label>
			<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
				<input required type="text" class="form-control" name="telephone"
					value="${ utilisateur.getTelephone() }" id="telephone">
			</div>
			<label class="col-form-label col-2 offset-md-1 col-md-1" for="street">Rue
				<i class="text-danger">*</i>
			</label>
			<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
				<input required type="text" class="form-control" name="rue" id="rue"
					value="${ utilisateur.getRue() }" id="rue">
			</div>
			<div class="form-group-md row">
				<label class="col-form-label col-2 col-md-1" for="cp">Code
					Postal <i class="text-danger">*</i>
				</label>
				<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
					<input required type="text" class="form-control" name="cp"
						value="${ utilisateur.getCodePostal() }" id="cp">
				</div>
				<label class="col-form-label col-2 offset-md-1 col-md-1" for="ville">Ville
					<i class="text-danger">*</i>
				</label>
				<div class="col-md-4 offset-1 offset-md-0 col-9 mb-2 mb-md-0">
					<input required type="text" class="form-control" name="ville"
						value="${ utilisateur.getVille() }" id="ville">
				</div>

			</div>
		</div>
	</div>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
