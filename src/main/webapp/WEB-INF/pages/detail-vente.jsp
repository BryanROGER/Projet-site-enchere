<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	
	<div>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getNomArticle() }">
	</div>
	<div>
	<label>Description :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getNomArticle() }">
	</div>
	<div>
	<label>Catégorie :</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getCategorie().getLibelle() }">
	</div>
	<div>
	<label>Mail:</label>
	<input type="text" readonly="readonly" value="${enchere.getArticle().getNomArticle() }">
	</div>
	
	
	
</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
