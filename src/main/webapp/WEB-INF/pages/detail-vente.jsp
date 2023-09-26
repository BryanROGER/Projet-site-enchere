<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<c:if test="${ ! empty error }">
		<div class="alert alert-danger">${ error }</div>
	</c:if>

	<c:if test="${enchere.getArticle().getEtatVente()==1 }">
		<h2>Détail vente</h2>
	</c:if>
	<c:if
		test="${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()==enchere.getUtilisateur().getNoUtilisateur() }">
		<h2>Vous avez remporté la vente</h2>
	</c:if>
	<c:if
		test="${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()!=enchere.getUtilisateur().getNoUtilisateur() }">
		<h2>${ enchere.getUtilisateur().getNom()}a remporté l'enchère</h2>
	</c:if>

	<div>
		<input type="text" readonly="readonly"
			value="${enchere.getArticle().getNomArticle() }">
	</div>
	<div>
		<label>Description :</label> <input type="text" readonly="readonly"
			value="${enchere.getArticle().getDescription() }">
	</div>
	<c:if test="${enchere.getArticle().getEtatVente()==1 }">
		<div>
			<label>Catégorie :</label> <input type="text" readonly="readonly"
				value="${enchere.getArticle().getCategorieArticle().getLibelle() }">
		</div>
	</c:if>

	<div>

		<c:if
			test="${enchere.getMontantEnchere() == enchere.getArticle().getMiseAPrix() && enchere.getArticle().getEtatVente()!=0  }">
			<label>Meilleure offre :</label>
			<input type="text" readonly="readonly"
				value="Aucune offre n'a encore été faite">
		</c:if>
		<c:if
			test="${enchere.getMontantEnchere() != enchere.getArticle().getMiseAPrix() && enchere.getArticle().getEtatVente()!=0  }">
			<label>Meilleure offre :</label>
			<input type="text" readonly="readonly"
				value="${enchere.getMontantEnchere() } pts par ${enchere.getUtilisateur().getPseudo()}">
		</c:if>
	</div>

	<div>
		<label>Mise à prix :</label> <input type="text" readonly="readonly"
			value="${enchere.getArticle().getMiseAPrix() } pts">
	</div>

	<c:if
		test="${enchere.getArticle().getEtatVente()==1 || ( enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()!=enchere.getUtilisateur().getNoUtilisateur()) }">
		<div>
			<label>Fin de l'enchère :</label> <input type="text"
				readonly="readonly"
				value="${enchere.getArticle().getDateFinEncheres()}">
		</div>
	</c:if>

	<c:if test="${enchere.getArticle().getEtatVente()==0  }">
		<div>
			<label>Début de l'enchère :</label> <input type="text"
				readonly="readonly"
				value="${enchere.getArticle().getDateDebutEncheres()}">
		</div>
	</c:if>



	<div>
		<label>Retrait :</label> <input type="text" readonly="readonly"
			value="${enchere.getArticle().getLieuRetrait().getRue() } ">
		<input type="text" readonly="readonly"
			value="${enchere.getArticle().getLieuRetrait().getCodePostal() } ${enchere.getArticle().getLieuRetrait().getVille() }  ">
	</div>
	<div>
		<label>Vendeur :</label> <input type="text" readonly="readonly"
			value="${enchere.getArticle().getVendeur().getPseudo() }">
	</div>

	<c:if
		test="${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()==enchere.getUtilisateur().getNoUtilisateur() }">
		<label>Tel :</label>
		<input value="${enchere.getArticle().getVendeur().getTelephone() }">
	</c:if>

	<c:if
		test="${user.getNoUtilisateur() != enchere.getArticle().getVendeur().getNoUtilisateur() && enchere.getArticle().getEtatVente()==1 }">
		<div>
			<form
				action="${ pageContext.request.contextPath }/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}"
				method="post">
				<label>Ma proposition :</label> <input class="input" type="number"
					name="prix_enchere" id="mPrix" step="1"
					min="${enchere.getArticle().getMiseAPrix() }" max="10000" required>

				<button>Enchérir</button>
			</form>
		</div>
	</c:if>
	<c:if
		test="${user.getNoUtilisateur() == enchere.getArticle().getVendeur().getNoUtilisateur() && enchere.getArticle().getEtatVente()==0}">
		<div>
			<a href="${ pageContext.request.contextPath }/modifier-article?noArticle=${enchere.getArticle().getNoArticle()}">
				Modifier article </a>
		</div>
	</c:if>
	<c:if test="${enchere.getArticle().getEtatVente()==2 }">
		<div>
			<form action="${ pageContext.request.contextPath }/encheres"
				method="get">

				<button type="submit">Retour à l'accueil</button>
			</form>
		</div>
	</c:if>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
