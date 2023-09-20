<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<c:if test="${ ! empty error }">
			<div class="alert alert-danger">${ error }</div>
	</c:if>
	<div class="form-group">
		<form action="${pageContext.request.contextPath}/se-connecter"
			method="post">
			<div>
				<label for="username">Identifiant</label> <input type="text"
					class="form-control" name="utilisateur" id="utilisateur"
					placeholder="pseudo ou email">
			</div>
			<div class="form-group">
				<label for="password">Mot de passe </label> <input type="password"
					class="form-control" name="mot_de_passe" id="mot_de_passe">
				<p>
					<a>Mot de passe oublié ?</a>
				</p>
			</div>
			<div class="form-group">
				<button class="btn btn-primary float-end " type="submit">
					Connexion</button>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value="isChecked"
					id="checkboxButton" name="se_souvenir_de_moi"> <label
					class="form-check-label" for="checkboxButton"> Se souvenir
					de moi </label>
			</div>
		</form>
	</div>

	<form class="mb-3"
		action="${pageContext.request.contextPath}/inscription" method="get">
		<button class="btn btn-info btn-md btn-block" type="submit">Créer
			un compte</button>
	</form>
</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
