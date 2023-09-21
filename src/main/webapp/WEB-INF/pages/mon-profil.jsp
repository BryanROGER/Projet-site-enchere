<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>


	<main>
	
	<c:if test="${ ! empty error }">
			<div class="alert alert-danger">${ error }</div>
		</c:if>

		<h1>Mon profil</h1>
<form class="row g-3" action="${pageContext.request.contextPath}/mon-profil" method="post">
  <div class="col-12">
  <label for="pseudo" class="form-label">Pseudo :</label>
	<input type="text" name="pseudo" value="${user.getPseudo() }" readonly="readonly"/>
	<div class="col-12">
  <label for="nom" class="form-label">Nom:</label>
	<input type="text" name="nom" value="${user.getNom() }" readonly="readonly"/>
  </div>
  <div class="col-12">
  <label for="nom" class="form-label">Prénom:</label>
	<input type="text" name="prenom" value="${user.getPrenom() }" readonly="readonly"/>
  </div>
  <div class="col-12">
  <label for="email" class="form-label">Email:</label>
	<input type="text" name="email" value="${user.getEmail() }" readonly="readonly"/>
		</div>
		<div class="col-12">
  <label for="telephone" class="form-label">Téléphone :</label>
	<input type="number" name="telephone" value="${user.getTelephone() }" readonly="readonly"/>
		</div>
  <div class="col-12">
  <label for="rue" class="form-label">Rue :</label>
	<input type="text" name="rue" value="${user.getRue() }" readonly="readonly"/>
		</div>
   <div class="col-12">
  <label for="code_postal" class="form-label">Code Postal :</label>
	<input type="number" name="code_postal" value="${user.getCodePostal() }" readonly="readonly"/>
		</div>
		<div class="col-12">
		 <label for="cp" class="form-label">Ville :</label>
	<input type="text" name="ville" value="${user.getVille() }" readonly="readonly"/>
		</div>
  <div class="col-6">
    <label for="mot_de_passe" class="form-label">Mot de passe :</label>
    <input type="password" name="mot_de_passe" class="form-control" id="mot_de_passe">
  </div>
 <div class="col-md-6">
    <label for="nouveau_mdp" class="form-label">Nouveau mot de passe :</label>
    <input type="password" name="nouveau_mdp" class="form-control" id="nouveau_mdp">
  </div>
   <div class="col-md-6">
    <label for="confirmation_mdp" class="form-label">Confirmation du nouveau mot de passe :</label>
    <input type="password" name="confirmation_mdp" class="form-control" id="confirmation_mdp">
  </div>
  <div class="col-6">
    <button type="submit" class="btn btn-success d-block mx-auto" value="maj">Enregistrer</button>
  </div>
  <div class="col-6">
    <button type="submit" class="btn btn-danger btn-block" value="suppr">Supprimer mon compte</button>
  </div>
</form>

	</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
