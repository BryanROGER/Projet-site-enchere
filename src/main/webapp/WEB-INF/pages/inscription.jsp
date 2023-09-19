<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h2>Inscription :</h2>
		</div>
<form action="${pageContext.request.contextPath}/inscription" method="post">
	<div class="row mt-5">
			<div class="col-4 offset-4">
				<div class="mb-3">
					<label for="pseudo" class="form-label">Pseudo:</label> <input
						type="text" class="form-control" 
						value="${utilisateur.getPseudo() }" id="pseudo">
						</div>
	<div class="mb-3">
					<label for="nom" class="form-label">Nom:</label> <input type="text"
						class="form-control" 
						value="${ utilisateur.getNom() }" id="nom">
				</div>
				<div class="mb-3">
					<label for="prenom" class="form-label">prenom</label> <input
						type="text" class="form-control" 
						value="${ utilisateur.getPrenom() }" id="prenom">
				</div>
	<div class="mb-3">
					<label for="email" class="form-label">Email :</label> <input
						type="text" class="form-control" 
						value="${ utilisateur.getEmail() }" id="email">
				</div>
	<div class="mb-3">
					<label for="telephone" class="form-label">Téléphone:</label> <input
						type="text" class="form-control" 
						value="${ utilisateur.getTelephone() }" id="telephone">
				</div>
				<div class="mb-3">
					<label for="rue" class="form-label">Rue :</label> <input
						type="text" class="form-control"
						value="${ utilisateur.getRue() }" id="rue">
				</div>
				<div class="mb-3">
					<label for="cp" class="form-label">Code postal :</label> <input
						type="text" class="form-control" 
						value="${ utilisateur.getCodePostal() }" id="cp">
				</div>
				<div class="mb-3">
					<label for="ville" class="form-label">Ville :</label> <input
						type="text" class="form-control" 
						value="${ utilisateur.getVille() }" id="ville">
				</div>
	<div class="mb-3">
					<label for="mdp" class="form-label">Mot de passe :</label> <input
						type="password" class="form-control" 
						 id="mdp">
				</div>
	<div class="mb-3">
					<label for="confirmation" class="form-label">Mot de passe :</label> <input
						type="password" class="form-control" 
						 id="confirmation">
				</div>
	
	<button type="submit" class="btn btn-info float right">Valider</button>

</form> 
<form action="${pageContext.request.contextPath}/encheres" method="get">
<button type="submit" class="btn btn-danger float left">Annuler</button>

</form>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
