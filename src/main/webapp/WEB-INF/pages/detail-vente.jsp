<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main class="d-flex flex-column justify-content-center">
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
		<h2>${ enchere.getUtilisateur().getPseudo()} a remporté l'enchère</h2>
	</c:if>

	<form
		action="${ pageContext.request.contextPath }/detail-vente?noArticle=${enchere.getArticle().getNoArticle()}"
		method="post">
		<div class="d-flex justify-content-center">
			<div class="container px-5 my-5">
				<div class="form-floating mb-3">
					<input class="form-control" type="text" readonly="readonly"
						value="${enchere.getArticle().getNomArticle() }" /> <label>Article</label>
				</div>
				<div class="form-floating mb-3">
					<input type="text" class="form-control" style="height: 5rem;"
						value="${enchere.getArticle().getDescription() }"
						readonly="readonly"></input> <label>Description :</label>
				</div>
				<c:if test="${enchere.getArticle().getEtatVente()==1 }">
					<div class="form-floating mb-3">
						<input class="form-control" type="text" readonly="readonly"
							value="${enchere.getArticle().getCategorieArticle().getLibelle() }">
						<label>Catégorie :</label>
					</div>
				</c:if>

				<div class="row my-3">
					<div class="col-md-6">
						<div class="form-floating">
							<input class="form-control" type="text" readonly="readonly"
								value="${enchere.getArticle().getMiseAPrix() } pts"> <label>Mise
								à prix :</label>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-floating">
							<c:if
								test="${enchere.getMontantEnchere() == enchere.getArticle().getMiseAPrix() && enchere.getArticle().getEtatVente()!=0  }">
								<input class="form-control" type="text" readonly="readonly"
									value="Aucune offre n'a encore été faite">
								<label>Meilleure offre :</label>
							</c:if>
							<c:if
								test="${enchere.getMontantEnchere() != enchere.getArticle().getMiseAPrix() && enchere.getArticle().getEtatVente()!=0  }">
								<input class="form-control" type="text" readonly="readonly"
									value="${enchere.getMontantEnchere() } pts par ${enchere.getUtilisateur().getPseudo()}">
								<label>Meilleure offre :</label>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row my-3">
					<div class="col-md-6">
						<div class="form-floating">
							<c:if
								test="${enchere.getArticle().getEtatVente()==1 || ( enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()!=enchere.getUtilisateur().getNoUtilisateur()) }">
								<input class="form-control" type="text" readonly="readonly"
									value="${enchere.getArticle().getDateFinEncheres()}">
								<label>Fin de l'enchère :</label>
							</c:if>
							<c:if test="${enchere.getArticle().getEtatVente()==0  }">
								<input class="form-control" type="text" readonly="readonly"
									value="${enchere.getArticle().getDateDebutEncheres()}">
								<label>Début de l'enchère :</label>
							</c:if>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-floating">
							<c:if
								test="${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()!=enchere.getUtilisateur().getNoUtilisateur() }">

								<input class="form-control" type="text" readonly="readonly"
									value="${enchere.getArticle().getVendeur().getPseudo() }">
								<label>Vendeur :</label>
							</c:if>
							<c:if
								test="${user.getNoUtilisateur() != enchere.getArticle().getVendeur().getNoUtilisateur() && enchere.getArticle().getEtatVente()==1 }">

								<div class="d-flex justify-content-center">
								<div class="col-md-6">
								<div class="form-floating">
								<input class="form-control" type="number" name="prix_enchere"
									id="mPrix" step="1"
									min="${enchere.getArticle().getMiseAPrix() }" max="10000"
									required><label> Ma proposition :</label>
								</div>
								
								</div>
								
								<div class="col-md-6 d-flex">
								<button type="submit" class="col-md-12 btn btn-outline-primary"> Enchérir</button>
								</div>
								</div>

							</c:if>

						</div>
					</div>

				</div>
				<c:if
					test="${enchere.getArticle().getEtatVente()==1 } || ${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()!=enchere.getUtilisateur().getNoUtilisateur() } ">
					<div class="row my-3">
						<div class="col-md-6">
							<div class="form-floating">

								<input class="form-control" type="text" readonly="readonly"
									value="${enchere.getArticle().getVendeur().getPseudo() }">
								<label>Vendeur :</label>
							</div>
						</div>

						<c:if
							test="${user.getNoUtilisateur() != enchere.getArticle().getVendeur().getNoUtilisateur() && enchere.getArticle().getEtatVente()==1 }">
							<div class="col-md-6">
								<div class="form-floating">
									<button type="submit" class="btn btn-info btn-lg btn-block">Enchérir</button>
								</div>
							</div>
						</c:if>

						<c:if
							test="${enchere.getArticle().getEtatVente()==2 && user.getNoUtilisateur()==enchere.getUtilisateur().getNoUtilisateur() }">
							<div class="col-md-6">
								<div class="form-floating">
									<input class="form-control"
										value="${enchere.getArticle().getVendeur().getTelephone() }"
										readonly="readonly"> <label>Téléphone :</label>
								</div>
							</div>
						</c:if>
					</div>
				</c:if>
				<div class="row my-3">
					<div class="col-md-6">
						<div class="form-floating">
							<input class="form-control" type="text" readonly="readonly"
								value="${enchere.getArticle().getLieuRetrait().getRue() } ">
							<label>Retrait à l'adresse :</label>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-floating">
							<input class="form-control" type="text" readonly="readonly"
								value="${enchere.getArticle().getLieuRetrait().getCodePostal() } ${enchere.getArticle().getLieuRetrait().getVille() }  ">
							<label>Ville : </label>
						</div>
					</div>
				</div>

				<c:if
					test="${user.getNoUtilisateur() == enchere.getArticle().getVendeur().getNoUtilisateur() && enchere.getArticle().getEtatVente()==0}">
					<a
						href="${ pageContext.request.contextPath }/modifier-article?noArticle=${enchere.getArticle().getNoArticle()}">
						<button type="button" class="btn btn-outline-primary">
							Modifier l'article</button>
					</a>
				</c:if>
				<c:if test="${enchere.getArticle().getEtatVente()==2 }">

					<a href="${ pageContext.request.contextPath }/encheres">
						<button type="button" class="btn btn-outline-dark">
							Retour à l'accueil</button>
					</a>

				</c:if>
			</div>
		</div>

	</form>
</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
