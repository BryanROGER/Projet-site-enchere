<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>

<h1>Mon profil</h1>

<form action="${pageContext.request.contextPath}/inscription" method="post">
	<label>Pseudo :</label>
	<input type="text" name="pseudo" />
	<label>Nom :</label>
	<input type="text" name="nom"/>
	<label>Prenom :</label>
	<input type="text" name="prenom"/>
	<label>Email :</label>
	<input type="text" name="email"/>
	<label>Téléphone :</label>
	<input type="text" name="telephone"/>
	<label>Rue :</label>
	<input type="text" name="rue"/>
	<label>Code postal :</label>
	<input type="text" name="code_postal"/>
	<label>Ville :</label>
	<input type="text" name="ville"/>
	<label>Mot de passe :</label>
	<input type="password" name="mot_de_passe"/>
	<label>confirmation</label>
	<input type="password" name="confirmation_mdp"/>
	
	<button type="submit">Créer</button>
	
	
	
	

</form> 
<form action="${pageContext.request.contextPath}/encheres" method="get">
<button type="submit"> Annuler</button>
</form>


</body>
</html>