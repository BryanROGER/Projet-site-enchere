<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>


	<h2>Liste des enchères</h2>


	<div>
		<div class="card">
			<div class="card-body">
				<form action="${ pageContext.request.contextPath }/encheres"
					method="get" id="margginEncheres">
					<div class="d-flex justify-content-around">
						<div class="col-md-5">
							<div class="form-group">
								<div class="col-xs-4">
									<label for="string_filter">Rechercher un article :</label> <input
										name="string_filter" type="text" class="form-control"
										placeholder="Recherche par mots-clés" value="${stringFilter}">
								</div>
							</div>
							<div class="col-xs-4">
								<div class="form-group">
									<label for="categories" class="mt-2">Rechercher une
										catégorie</label> <select name="categorie" id="categories"
										class="col form-control">
										<c:forEach items="${categories}" var="categorie">

											<option value="${categorie.getNoCategorie() }">${categorie.getLibelle()}</option>

										</c:forEach>

									</select>
								</div>
							</div>
							<button type="submit" class="btn btn-outline-info mt-4">Rechercher</button>
						</div>

						<div class="col-md-6 mt-2">
							<c:if test="${user!=null }">
								<div>
									<div class="d-flex justify-content-around ">

										<div class="col-md-5 d-flex flex-column">
											<label> <input type="radio" name="typeTransaction"
												value="achats" id="achat" onclick="gererCasesACocher()">
												Achat
											</label>
											<div id="achatVente">
												<label for="encheresOuvertes"> <input
													type="checkbox" id="encheresOuvertes" name="achat"
													value="encheresOuvertes" disabled
													onclick="toggleCheckboxes('encheresOuvertes')">
													Enchères Ouvertes
												</label> <label for="mesEncheresEnCours"> <input
													type="checkbox" id="mesEncheresEnCours" name="achat"
													value="mesEncheresEnCours" disabled
													onclick="toggleCheckboxes('mesEncheresEnCours')">
													Mes Enchères en Cours
												</label> <label for="mesEncheresRemportees"> <input
													type="checkbox" id="mesEncheresRemportees" name="achat"
													value="mesEncheresRemportees" disabled
													onclick="toggleCheckboxes('mesEncheresRemportees')">
													Mes Enchères Remportées

												</label>

											</div>



										</div>
										<div class="col-md-5">

											<label> <input type="radio" name="typeTransaction"
												value="ventes" id="vente" onclick="gererCasesACocher()">
												Vente
											</label>
											<div id="achatVente">
												<label for="mesVentesEnCours"> <input
													type="checkbox" id="mesVentesEnCours" name="vente"
													value="mesVentesEnCours" disabled
													onclick="toggleCheckboxes('mesVentesEnCours')"> Mes
													Ventes en Cours
												</label> <label for="ventesNonDebutees"> <input
													type="checkbox" id="ventesNonDebutees" name="vente"
													value="ventesNonDebutees" disabled
													onclick="toggleCheckboxes('ventesNonDebutees')">

													Ventes Non Débutées

												</label> <label for="ventesTerminees"> <input
													type="checkbox" id="ventesTerminees" name="vente"
													value="ventesTerminees" disabled
													onclick="toggleCheckboxes('ventesTerminees')">
													Ventes Terminées

												</label>
											</div>
										</div>

									</div>
								</div>

							</c:if>

							
						</div>
						</div>
				</form>
			</div>
		</div>
	</div>

	<div class="mt-3 ">
	<div class="d-flex col-12 flex-wrap justify-content-evenly "> 
		<c:forEach items="${encheres }" var="enchere">
		
		
			
				<div class="card border-secondary mt-2 " style="width: 25rem;">
					<div class="card-body">
						<h5 class="card-title" style="text-align: center">
							<a
								href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}">${enchere.getArticle().getNomArticle() }</a>

						</h5>

						<p class="card-text" style="text-align: center">Prix :
							${enchere.getMontantEnchere() }</p>

						<p class="card-text" style="text-align: center">Fin de
							l'enchère : ${enchere.getArticle().getDateFinEncheres() }</p>

						<p class="card-text" style="text-align: center">

							Vendeur : <a
								href="${pageContext.request.contextPath}/profil?id=${enchere.getArticle().getVendeur().getNoUtilisateur() }">${enchere.getArticle().getVendeur().getPseudo() }</a>

						</p>
						<div class="d-flex justify-content-center">
							<a
								href="${pageContext.request.contextPath}/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}">
								<button class="btn btn-outline-dark">En savoir plus</button>
							</a>
						</div>
					</div>
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