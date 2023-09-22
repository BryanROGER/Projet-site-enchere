<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>



<main>
	<h2 class="mb-5 text-center">Liste des enchères</h2>


	<form class="row"
		action="${ pageContext.request.contextPath }/encheres" method="get">
		<div class="col-4">
			<p class="m-0">Filtres&nbsp;:</p>
		</div>
		<input name="string_filter" type="text" class="form-control m-2"
			placeholder="Le nom de l'article contient" value="${stringFilter}">
		<p class="d-flex flex-row"></p>

		<label class="col-6" for="categories">Catégorie&nbsp;:</label> <select
			name="categorie" id="categories" class="col mt-2 form-control">


			<c:forEach items="${categories}" var="categorie">
				<option value="${categorie.getNoCategorie() }">${categorie.getLibelle()}</option>
			</c:forEach>
		</select>

		<div>
			<div>
			
			<label> <input type="radio" name="typeTransaction"
					value="achat" id="achat" onclick="gererCasesACocher()">
					Achat
				</label> <label> <input type="radio" name="typeTransaction"
					value="vente" id="vente" onclick="gererCasesACocher()">
					Vente
				</label><br>
			
				 <label for="encheresOuvertes"> <input
					type="checkbox" id="encheresOuvertes" value="encheresOuvertes" disabled> Enchères
					Ouvertes
				</label>
				<label for="mesVentesEnCours"> <input
					type="checkbox" id="mesVentesEnCours" value="mesVentesEnCours" disabled> Mes Ventes
					en Cours
				</label> <br>
				
				<label for="mesEncheresEnCours"> <input
					type="checkbox" id="mesEncheresEnCours" value="mesEncheresEnCours" disabled> Mes
					Enchères en Cours
				</label> 
					<label for="ventesNonDebutees"> <input
					type="checkbox" id="ventesNonDebutees" value="ventesNonDebutees" disabled> Ventes Non
					Débutées
				</label> <br>
				
				<label for="mesEncheresRemportees"> <input
					type="checkbox" id="mesEncheresRemportees" value="mesEncheresRemportees" disabled> Mes
					Enchères Remportées
				</label> 
					
				<label for="ventesTerminees"> <input
					type="checkbox" id="ventesTerminees" value="ventesTerminees" disabled> Ventes
					Terminées
				</label><br>
			
			</div>

		</div>
		<input type="submit" class="btn btn-info btn-lg btn-block mt-2"
			id="#searchButton" value="Rechercher" />
	</form>




	<c:forEach items="${encheres }" var="enchere">
		<div>
			<p>
				<a
					href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}">${enchere.getArticle().getNomArticle() }</a>
			</p>
			<p>Prix : ${enchere.getArticle().getMiseAPrix() }</p>
			<p>Fin de l'enchère : ${enchere.getArticle().getDateFinEncheres() }</p>
			<p>
				Vendeur : <a
					href="${pageContext.request.contextPath}/profil?id=${enchere.getArticle().getVendeur().getNoUtilisateur() }">${enchere.getArticle().getVendeur().getPseudo() }</a>
			</p>

		</div>
	</c:forEach>



	<script>
		function gererCasesACocher() {
			var achatRadio = document.getElementById("achat");
			var venteRadio = document.getElementById("vente");
			var encheresOuvertesCheckbox = document
					.getElementById("encheresOuvertes");
			var mesEncheresEnCoursCheckbox = document
					.getElementById("mesEncheresEnCours");
			var mesEncheresRemporteesCheckbox = document
					.getElementById("mesEncheresRemportees");
			var mesVentesEnCoursCheckbox = document
					.getElementById("mesVentesEnCours");
			var ventesNonDebuteesCheckbox = document
					.getElementById("ventesNonDebutees");
			var ventesTermineesCheckbox = document
					.getElementById("ventesTerminees");

			if (achatRadio.checked) {
				encheresOuvertesCheckbox.disabled = false;
				mesEncheresEnCoursCheckbox.disabled = false;
				mesEncheresRemporteesCheckbox.disabled = false;
				mesVentesEnCoursCheckbox.disabled = true;
				ventesNonDebuteesCheckbox.disabled = true;
				ventesTermineesCheckbox.disabled = true;
			} else if (venteRadio.checked) {
				encheresOuvertesCheckbox.disabled = true;
				mesEncheresEnCoursCheckbox.disabled = true;
				mesEncheresRemporteesCheckbox.disabled = true;
				mesVentesEnCoursCheckbox.disabled = false;
				ventesNonDebuteesCheckbox.disabled = false;
				ventesTermineesCheckbox.disabled = false;
			} else {
				// Aucun bouton radio sélectionné, désactiver toutes les cases à cocher.
				encheresOuvertesCheckbox.disabled = true;
				mesEncheresEnCoursCheckbox.disabled = true;
				mesEncheresRemporteesCheckbox.disabled = true;
				mesVentesEnCoursCheckbox.disabled = true;
				ventesNonDebuteesCheckbox.disabled = true;
				ventesTermineesCheckbox.disabled = true;
			}
		}
	</script>

</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
