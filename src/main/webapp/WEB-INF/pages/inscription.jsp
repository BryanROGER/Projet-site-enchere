<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<main>

<h1>Inscription</h1> 

<form action="${pageContext.request.contextPath}/inscription"
			method="post">
			<c:if test="${ ! empty error }">
			<div class="alert alert-danger">${ error }</div>
		</c:if>
  <div class="form-group row">
    <label for="pseudo" class="col-2 col-form-label"><b>Pseudo : </b></label> 
    <div class="col-10">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text">
            <i class="fa fa-user-o"></i>
          </div>
        </div> 
        <input id="pseudo" name="pseudo" placeholder="pseudo" type="text" required="required" value="${utilisateur.getPseudo() }" class="form-control">
      </div>
    </div>
  </div>
  <div class="form-group row">
    <label class="col-2 col-form-label" for="nom"><b>Nom :</b></label> 
    <div class="col-10">
      <input id="nom" name="nom" type="text" required="required" value="${ utilisateur.getNom() }" class="form-control">
    </div>
  </div>
  
  <div class="form-group row">
    <label class="col-2 col-form-label" for="prenom"><b>Prénom :</b></label> 
    <div class="col-10">
    <input id="prenom" name="prenom" type="text" required="required" value="${ utilisateur.getPrenom() }" class="form-control">
  </div>
  </div>
  <div class="form-group row">
    <label for="email"  class="col-2 col-form-label"><b>Email :</b></label> 
    <div class="col-10">
    <div class="input-group">
      <div class="input-group-prepend">
        <div class="input-group-text">
          <i class="fa fa-address-book"></i>
        </div>
      </div> 
      <input id="email" name="email" placeholder="example@eni.com" type="text" required="required" value="${ utilisateur.getEmail() }"class="form-control">
    </div>
  </div>
 </div>
  
  
  <div class="form-group row">
    <label for="telephone" class="col-2 col-form-label"><b>Teléphone : </b></label> 
    <div class="col-10">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text">
            <i class="fa fa-phone"></i>
          </div>
        </div> 
        <input id="telephone" name="telephone" type="number" aria-describedby="HelpBlock" required="required" value="${ utilisateur.getTelephone() } "class="form-control">
      </div> 
      <span id="HelpBlock" class="form-text text-muted">0605040302</span>
    </div>
  </div>
 
 
 <div class="form-group row">
    <label for="rue" class="col-2 col-form-label"><b>Adresse : </b></label> 
    <div class="col-10">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text">
            <i class="fa fa-home"></i>
          </div>
        </div> 
        <input id="rue" name="rue" type="text" required="required" value="${ utilisateur.getRue() }" class="form-control">
      </div>
    </div>
  </div> 
  
  <div class="form-group row">
    <label for="ville" class="col-2 col-form-label"><b>Ville : </b></label> 
    <div class="col-10">
      <input id="ville" name="ville" type="text" required="required" value="${ utilisateur.getVille() }" class="form-control">
    </div>
  </div>


 <div class="form-group row">
    <label for="code_postal" class="col-2 col-form-label">Code postal</label> 
    <div class="col-10">
      <input id="code_postal" name="code_postal" type="text" required="required" value="${ utilisateur.getCodePostal() }" class="form-control">
    </div>
  </div>
  
  
 
  <div class="form-group row">
    <label for="mot_de_passe" class="col-2 col-form-label"> <b>Mot de passe : </b></label>
    <div class="col-10"> 
    <div class="input-group">
      <div class="input-group-prepend">
        <div class="input-group-text">
          <i class="fa fa-500px"></i>
        </div>
      </div> 
      <input id="mot_de_passe" name="mot_de_passe" type="password" required="required" class="form-control">
    </div>
  </div>
  </div>
  <div class="form-group row">
    <label for="confirmation" class="col-2 col-form-label">Confirmation du mot de passe</label> 
    <div class="col-10"> 
    <input id="confirmation_mdp" name="confirmation_mdp" type="password" required="required" class="form-control">
  </div> 
  </div>
  <div class="form-group">
  <div class="col-md-8">
    <button id="inscription" type="submit" name="inscription" class="btn btn-success">S'inscrire</button>
    <button id="annuler" type="reset" name="annuler" class="btn btn-danger">Annuler</button>
  </div>
</div>
</form>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
