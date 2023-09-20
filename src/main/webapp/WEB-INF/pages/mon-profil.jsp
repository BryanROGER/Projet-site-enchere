<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>


	<main>

		<h1>Mon profil</h1>
		
		<form action="${pageContext.request.contextPath}/mon-profil" method="post">
		<div>
			<label>Pseudo :</label>
			<input name="pseudo" value="${user.getPseudo() }" readonly="readonly"/>
			<label>nom :</label>
			<input name="nom" value="${user.getNom() }"/>
		</div>
		<div>
			<label>Prénom :</label>
			<input name="prenom" value="${user.getPrenom() }"/>
			<label>Email :</label>
			<input name="email" value="${user.getEmail() }"/>
		</div>
		<div>
			<label>Téléphone :</label>
			<input name="telephone" value="${user.getTelephone() }"/>
			<label>Rue :</label>
			<input name="rue" value="${user.getRue() }"/>
		</div>
		<div>
			<label>Code postal :</label>
			<input name="code_postal" value="${user.getCodePostal() }"/>
			<label>Ville :</label>
			<input name="ville" value="${user.getVille() }"/>
		</div>
		<div>
			<label>Mot de passe :</label>
			<input name="mot_de_passe" type="password"/>
			
		</div>
		<div>
			<label>Nouveau mot de passe :</label>
			<input name="nouveau_mdp" type="password"/>
			<label>Confirmation :</label>
			<input name="confirmation_mdp" type="password"/>
		</div>
		
		<div>
			<button type="submit" name="bouton" value="maj">Enregistrer</button>
			<button type="submit" name="bouton" value="suppr">Supprimer mon compte</button>
		</div>
		
		</form>
		
	</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
