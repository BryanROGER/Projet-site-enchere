<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="/webapp/assets/style.css">
	<title>Troc-encheres</title>
</head>
<body>
<div class="container">		
		<header class="row" >
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="color:blue">
			  <div class="container-fluid">
			    <a class="navbar-brand" href="#">Troc-encheres </i></a>
			    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			    </button>
			    <div class="collapse navbar-collapse" id="navbarNav">
			      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
			       <li class="nav-item ms-5">
			        <li class="nav-item">
			          <a class="nav-link active" aria-current="page" href="${ pageContext.request.contextPath }/encheres">Encheres <i class="fa-solid fa-gavel"></i></a>
			        </li>
			        </li>
			         <li class="nav-item ms-5">
			        <li class="nav-item">
			          <a class="nav-link" href="${ pageContext.request.contextPath }/vendre"> Vendre <i class="fa-solid fa-hand-holding-dollar"></i></a>
			        </li>
			        </li>
			         <li class="nav-item ms-5">
			        <li class="nav-item">
			          <a class="nav-link" href="${ pageContext.request.contextPath }/connexion">Connexion <i class="fa-solid fa-right-to-bracket"></i></a>
			        </li>
			        </li>
			        </li>
			        <c:if test="${ user == null }">		
			         <li class="nav-item ms-5">	        
				        <li class="nav-item">
				          <a class="nav-link" href="${ pageContext.request.contextPath }/profil">Mon Profil <i class="fa-regular fa-user"></i></a>
				        </li>
				        </li>	
			        </c:if>
			        <c:if test="${ user != null }">	
			         <li class="nav-item ms-5">
    				    <li class="nav-item">
				          <a class="nav-link" href="${ pageContext.request.contextPath }/deconnexion">Deconnexion<i class="fa-solid fa-right-to-bracket"></a>
				        </li>
				        </li>
			        </c:if>
			      </ul>
	              <form class="d-flex" method="GET" action="" >
			        <input class="form-control me-2" type="search" name="q" placeholder="Recherche..." aria-label="Search">
			        <button class="btn btn-outline-success" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
			      </form>
			    </div>
			  </div>
			</nav>
		</header>
		</body>
		</html>
</html>