<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>
<body>
<c:if test="${ ! empty error }">
		<div class="alert alert-danger">${ error }</div>
	</c:if>
<div class="row mt-5">
		
		<div class="col-4 offset-4">

		<form action="${pageContext.request.contextPath}/se-connecter"
			method="post">
  <!-- Email input -->
  <div class="form-outline mb-4">
    <input type="text" id="utilisateur"  name="utilisateur" class="form-control" />
    <label class="form-label" for="utilisateur">Pseudo ou email</label>
  </div>

  <!-- Password input -->
  <div class="form-outline mb-4">
    <input type="password" id="mot_de_passe" name="mot_de_passe" class="form-control" />
    <label class="form-label" for="mot_de_passe">Mot de passe</label>
  </div>

  <!-- 2 column grid layout for inline styling -->
  <div class="row mb-4">
    <div class="col d-flex justify-content-center">
      <!-- Checkbox -->
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="form2Example31" name="se_souvenir_de_moi" checked />
        <label class="form-check-label" for="form2Example31"> Se souvenir de moi</label>
      </div>
    </div>

    <div class="col">
      <!-- Simple link -->
      <a href="#!">Mot de passe oublié ?</a>
    </div>
  </div>

  <!-- Submit button -->
  <button type="submit" class="btn btn-primary btn-block mb-4">Se connecter</button>

  <!-- Register buttons -->
  <div class="text-center">
    <p>Pas encore membre ? <a href="${pageContext.request.contextPath}/inscription">S'inscire</a></p>
  </div>
</form>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
