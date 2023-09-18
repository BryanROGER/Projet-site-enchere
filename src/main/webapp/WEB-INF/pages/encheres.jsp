<%@page import="fr.eni.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%Utilisateur user = (Utilisateur) request.getAttribute("utilisateur"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchères</title>
</head>
<body>

<h1><%= user.getPseudo() %></h1>


</body>
</html>