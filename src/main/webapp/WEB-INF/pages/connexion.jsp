<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Se connecter</title>
</head>
<body>

		<div class="form-group row">
	 	<form action="${pageContext.request.contextPath}/se-connecter" method="post" >
	 	<label class= "col-4 offset-md-3 col-md-2 col-form label" for="identifiant">Identifiant</label>
	 	<div class="col-8 col-md3 "> 
	 	<input type="text" name="identifiant" placeholder="Peuso ou email" />
	 	</div>
	 	<div class="form-group row">
	 	<label class="col-4 offset-md-3 col-md-2 col-form-label" for="mot_de_passe">Mot de passe</label>
	 	<div class="col-8 col-md-3">
	 	<input type="password" name="mot_de_passe" id="mot_de_passe placeholder="mot de passe" />
	 	</div>
	 	<div class="form-group d-flex justify-content-center">
        <button type="submit" class="btn btn-info col-4 col-lg-2 offset-sm-1 text-center offset-1">Connexion</button>
        <div class="form-group form-check d-flex flex-column col-8 offset-md-1 col-md-3 pt-2 ml-2">
            <input type="checkbox" class="form-check-input" id="remember_login">
            <label class="form-check-label" for="remember_login">Se souvenir de moi</label>
	 	</div>
	 	
	 	<p><a>Mot de passe oublié</a></p>
	 </form>
</div>
<div class="row">
    <a class="offset-2 col-8 offset-md-4 col-md-3" href="${pageContext.request.contextPath}/createLogin">
        <button class="btn btn-info btn-md btn-block">Créer un compte</button>
    </a>
</div>


