<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>


<main id="footerAdapteInscription">

	<div class="container h-100">
		<div class="row h-100 justify-content-center align-items-center">
			<div class="d-flex flex-column">
				<h1>Inscription</h1>
			<div class="d-flex justify-content-center my-4">
				<form action="${pageContext.request.contextPath}/inscription"
					method="post" id="form">
					<c:if test="${ ! empty error }">
						<div class="alert alert-danger">${ error }</div>
					</c:if>
					<div class="form-group">
						<label for="pseudo"><b>Pseudo : </b></label> <input id="pseudo"
							name="pseudo" placeholder="pseudo" type="text"
							required="required" value="${utilisateur.getPseudo() }"
							class="form-control">
					</div>
					<div class="form-group">

						<label for="nom"><b>Nom :</b></label> <input id="nom" name="nom"
							type="text" required="required" value="${ utilisateur.getNom() }"
							class="form-control">
					</div>
					<div class="form-group">
						<label for="prenom"><b>Prénom :</b></label> <input id="prenom"
							name="prenom" type="text" required="required"
							value="${ utilisateur.getPrenom() }" class="form-control">

					</div>

					<div class="form-group">

						<label for="email"><b>Email :</b></label> <input id="email"
							name="email" placeholder="example@eni.com" type="text"
							required="required" value="${ utilisateur.getEmail() }"
							class="form-control">
					</div>

					<div class="form-group">

						<label for="telephone"><b>Teléphone : </b></label> <input
							id="telephone" name="telephone" type="text" required="required"
							value="${ utilisateur.getTelephone() } " class="form-control">
					</div>

					<div class="form-group">
						<label for="rue"><b>Adresse : </b></label> <input id="rue"
							name="rue" type="text" required="required"
							value="${ utilisateur.getRue() }" class="form-control">
					</div>

					<div class="form-group">
						<label for="ville"><b>Ville : </b></label> <input id="ville"
							name="ville" type="text" required="required"
							value="${ utilisateur.getVille() }" class="form-control">
					</div>
					<div class="form-group">

						<label for="code_postal"><b>Code postal :</b></label> <input
							id="code_postal" name="code_postal" type="text"
							required="required" value="${ utilisateur.getCodePostal() }"
							class="form-control">
					</div>




					<div class="form-group">
						<label for="mot_de_passe"> <b>Mot de passe : </b></label> <input
							id="mot_de_passe" name="mot_de_passe" type="password"
							required="required" class="form-control">
					</div>

					<div class="form-group">
						<label for="confirmation"><b>Confirmation du mot de
								passe :</b></label> <input id="confirmation_mdp" name="confirmation_mdp"
							type="password" required="required" class="form-control">
					</div>
					<div class="d-flex justify-content-center mt-2">
						<div class="col-4 d-flex justify-content-around">
				
							<button id="inscription" type="submit" name="inscription"
								class="btn btn-success">S'inscrire</button>
								<a href="${pageContext.request.contextPath}/encheres">
							<button type="button" class="btn btn-danger">Annuler</button>
								</a>
						</div>
					</div>

				</form>
				</div>
				</div>
				</div>
				
			</div>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
