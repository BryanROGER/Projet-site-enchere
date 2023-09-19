<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>


	<main>

		<div>
			<div>
				<label>Pseudo :</label> <input readonly="readonly"
					value="${utilisateur.getPseudo() }">
			</div>
			<div>
				<label>Nom :</label> <input readonly="readonly"
					value="${utilisateur.getNom() }">
			</div>
			<div>
				<label>Prénom :</label> <input readonly="readonly"
					value="${utilisateur.getPrenom() }">
			</div>
			<div>
				<label>Email :</label> <input readonly="readonly"
					value="${utilisateur.getEmail() }">
			</div>
			<div>
				<label>Téléphone :</label> <input readonly="readonly"
					value="${utilisateur.getTelephone() }">
			</div>
			<div>
				<label>Rue :</label> <input readonly="readonly"
					value="${utilisateur.getRue() }">
			</div>
			<div>
				<label>Code postal :</label> <input readonly="readonly"
					value="${utilisateur.getCodePostal() }">
			</div>
			<div>
				<label>Ville :</label> <input readonly="readonly"
					value="${utilisateur.getVille() }">
			</div>
		</div>
	</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
