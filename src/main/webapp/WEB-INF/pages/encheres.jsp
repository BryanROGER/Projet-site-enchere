<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>

	<h2 class>Liste des enchères</h2>

<div class="w-25 p-3">
 <form action="${ pageContext.request.contextPath }/encheres" method="get">

  <div class="form-group">
    <label for="string_filter">Rechercher un article :</label>
    <input name="string_filter" type="text" class="form-control" placeholder="Recherche par mots-clés" value="${stringFilter}">
  </div>
  <div class="form-group">
    <label for="categories">Rechercher une catégorie</label>
   <select
			name="categorie" id="categories" class="col mt-2 form-control">
			<c:forEach items="${categories}" var="categorie">

				<option value="${categorie.getNoCategorie() }">${categorie.getLibelle()}</option>

			</c:forEach>

		</select>
		<div>
			<div>
				<label> <input type="radio" name="typeTransaction"

					value="achats" id="achat" onclick="gererCasesACocher()">

					Achat

				</label> <label> <input type="radio" name="typeTransaction"

					value="ventes" id="vente" onclick="gererCasesACocher()">

					Vente

				</label><br> <label for="encheresOuvertes"> <input

					type="checkbox" id="encheresOuvertes" name="achat"

					value="encheresOuvertes" disabled

					onclick="toggleCheckboxes('encheresOuvertes')"> Enchères

					Ouvertes

				</label> <label for="mesVentesEnCours"> <input type="checkbox"

					id="mesVentesEnCours" name="vente" value="mesVentesEnCours"

					disabled onclick="toggleCheckboxes('mesVentesEnCours')">

					Mes Ventes en Cours

				</label> <br> <label for="mesEncheresEnCours"> <input

					type="checkbox" id="mesEncheresEnCours" name="achat"

					value="mesEncheresEnCours" disabled

					onclick="toggleCheckboxes('mesEncheresEnCours')"> Mes

					Enchères en Cours

				</label> <label for="ventesNonDebutees"> <input type="checkbox"

					id="ventesNonDebutees" name="vente" value="ventesNonDebutees"

					disabled onclick="toggleCheckboxes('ventesNonDebutees')">

					Ventes Non Débutées

				</label> <br> <label for="mesEncheresRemportees"> <input

					type="checkbox" id="mesEncheresRemportees" name="achat"

					value="mesEncheresRemportees" disabled

					onclick="toggleCheckboxes('mesEncheresRemportees')"> Mes

					Enchères Remportées

				</label> <label for="ventesTerminees"> <input type="checkbox"

					id="ventesTerminees" name="vente" value="ventesTerminees" disabled

					onclick="toggleCheckboxes('ventesTerminees')"> Ventes

					Terminées

				</label> <br>
			</div>

  </div>
  <button type="submit" class="btn btn-default">Rechercher</button>
</form>
</div>
	<div class="card-group">
		<div class="row">
			 <c:forEach items="${encheres }" var="enchere">
					<div class="card" style="width: 18rem;">
					<div class="card-body">
			<h5 class="card-title"> <a href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}">${enchere.getArticle().getNomArticle() }</a>

	</h5>

			<p class="card-text">Prix : ${enchere.getArticle().getMiseAPrix() }</p>

			<p class="card-text">Fin de l'enchère : ${enchere.getArticle().getDateFinEncheres() }</p>

		<p class="card-text">

				Vendeur : <a

					href="${pageContext.request.contextPath}/profil?id=${enchere.getArticle().getVendeur().getNoUtilisateur() }">${enchere.getArticle().getVendeur().getPseudo() }</a>

			</p>

 <a
							href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}"
							class="btn
							btn-outline-dark">En savoir plus</a>

		</div>

	</c:forEach>
		</div>
	</div>



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
		function toggleCheckboxes(clickedCheckboxId) {

			  const checkboxes = document.querySelectorAll('input[type="checkbox"]');

			  

			  checkboxes.forEach(checkbox => {

			    if (checkbox.id !== clickedCheckboxId) {

			      checkbox.checked = false;

			    }

			  });

			}
	</script>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>