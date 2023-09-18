<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
</head>
<body>

<div>
	 <form action="${pageContext.request.contextPath}/se-connecter" method="post" >
	 	<label>Identifiant</label>
	 	<input type="text" name="identifiant" placeholder="Peuso ou email" />
	 	<label>Mot de passe</label>
	 	<input type="password" name="mot_de_passe" />
	 	<button type="submit">Connexion</button>
	 	<input type="checkbox"/>
	 	<label>Se souvenir de moi</label>
	 	<p><a>Mot de passe oublié</a></p>
	 </form>
</div>

<div>
	 <form action="${pageContext.request.contextPath}/inscription" method="get" >
	 	<button type="submit">Créer un compte</button>
	 </form>
</div>


</body>
</html>