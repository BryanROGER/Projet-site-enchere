<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


	<main>

		<h1>Mon profil</h1>



<form action="${pageContext.request.contextPath}/mon-profil" method="post">
		<c:if test="${ ! empty error }">
		<div class="alert alert-danger">${ error }</div>
		</c:if>
   <div class="form-group row">
    <label for="pseudo" class="col-2 col-form-label">Pseudo</label> 
    <div class="col-10">
      <input id="pseudo" name="pseudo" type="text" value="${user.getPseudo() }" required="required" class="form-control" readonly="readonly"/>
    </div>
  </div>
  <div class="form-group row">
    <label for="nom"class="col-2 col-form-label" for="nom">Nom</label> 
    <div class="col-10">
      <input id="nom" name="nom" type="text" value="${user.getNom() }" required="required" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="prenom" class="col-2 col-form-label">Prénom</label> 
    <div class="col-10">
      <input id="prenom" name="prenom" type="text" required="required" value="${user.getPrenom() }" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="email" class="col-2 col-form-label">Email</label> 
    <div class="col-10">
      <input id="email" name="email" type="text" required="required" value="${user.getEmail() }" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="telephone" class="col-2 col-form-label">Teléphone</label> 
    <div class="col-10">
      <input id="telephone" name="telephone" type="text" required="required" value="${user.getTelephone() }" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="rue" class="col-2 col-form-label">Adresse</label> 
    <div class="col-10">
      <input id="rue" name="rue" type="text" required="required" value="${user.getRue() }" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="code_postal" class="col-2 col-form-label">Code postal</label> 
    <div class="col-10">
      <input id="code_postal" name="code_postal" type="number" required="required" value="${user.getCodePostal() }" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="ville" class="col-2 col-form-label">Ville</label> 
    <div class="col-10">
      <input id="ville" name="ville" type="text" required="required" value="${user.getVille() }"  class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="mot_de_passe" class="col-2 col-form-label">Mot de passe</label> 
    <div class="col-10">
      <input id="mot_de_passe" name="mot_de_passe" type="password" required="required" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="nouveau_mdp" class="col-2 col-form-label">Nouveau mot de passe</label> 
    <div class="col-10">
      <input id="nouveau_mdp" name="nouveau_mdp" type="password" class="form-control">
    </div>
  </div>
  <div class="form-group row">
    <label for="confirmation_mdp" class="col-2 col-form-label">Confirmation du mot de passe</label> 
    <div class="col-10">
      <input id="confirmation_mdp" name="confirmation_mdp" type="password" required="required" class="form-control">
    </div>
  </div> 
  <div class="form-group">
  <div class="col-md-8">
    <button id="inscription" type="submit" name="inscription" class="btn btn-success">Enregistrer</button>
    <button id="annuler" type="reset" name="annuler" class="btn btn-danger">Supprimer mon compte</button>
  </div>
</div>
</form>
</main>
 
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
