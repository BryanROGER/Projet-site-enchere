<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	
	<div>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getNomArticle() }">
	</div>
	<div>
	<label>Description :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getDescription() }">
	</div>
	<div>
	<label>Catégorie :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getCategorieArticle().getLibelle() }">
	</div>
	<div>
	<label>Meilleure offre :</label>
	<c:if test="${enchere.getMontantEnchere() == 0 }">
	<input type="text" readonly="readonly" value="Aucune offre n'a encore été faite">
	</c:if>
	<c:if test="${enchere.getMontantEnchere() != 0 }">
	<input type="text" readonly="readonly" value="${enchere.getMontantEnchere() } pts par ${enchere.getUtilisateur().getPseudo()}">
	</c:if>
	</div>
	<div>
	<label>Mise à prix :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getMiseAPrix() } pts">
	</div>
	<div>
	<label>Retrait :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getLieuRetrait().getRue() } ">
	<input type="text" readonly="readonly" value="${enchere.getArticle().getLieuRetrait().getCodePostal() } ${enchere.getArticle().getLieuRetrait().getVille() }  ">
	</div>
	<div>
	<label>Vendeur :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getVendeur().getPseudo() }">
	</div>

	<c:if
		test="${user.getNoUtilisateur() != enchere.getArticle().getVendeur().getNoUtilisateur() }">
		<div>
			<form action="${ pageContext.request.contextPath }/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}" method="post">
				<label>Ma proposition :</label> <label for="mPrix">Mise à
					prix :</label> <input class="input" type="number" name="prix_enchere" id="mPrix"
					step="1" min="${enchere.getArticle().getMiseAPrix() }" max="10000"
					required>

				<button>Enchérir</button>
			</form>
		</div>
	</c:if>


</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
