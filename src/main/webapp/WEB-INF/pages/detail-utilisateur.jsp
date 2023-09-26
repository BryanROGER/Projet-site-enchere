<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>

	<h2> Détails sur le vendeur</h2>
<div class="w-50">
		<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
			<input required type="text" class="form-control" name="pseudo"
				readonly="readonly" value="${utilisateur.getPseudo() }" id="pseudo">
		<label for="pseudo">Pseudo
		
		</label>
		</div>
		</div>
			<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="mail"
					id="mail" readonly="readonly" value="${ utilisateur.getEmail() }">	
			<label for="mail">Email
				
			</label>
			</div>
			</div>
			</div>
			
	<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="nom"
				readonly="readonly" value="${ utilisateur.getNom() }" id="nom">
		<label for="nom"> Nom
	
		</label>
	</div>	
		</div>
			<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="prenom"
					readonly="readonly" value="${ utilisateur.getPrenom() }"
					id="prenom">
			<label for="prenom">Prénom
			
			</label>
		</div>
		</div>
			</div>
		
			<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="telephone"
						readonly="readonly" value="${ utilisateur.getTelephone() }"
						id="telephone">
				<label for="telephone">Téléphone
					</label>
				</div>
				</div>
				<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="rue"
						id="rue" readonly="readonly" value="${ utilisateur.getRue() }"
						id="rue">
				<label for="rue">Adresse </label>
		</div>
		</div>
				</div>
				<div class="row my-3">
				<div class="col-md-6">
					<div class="form-floating">
						<input required type="text" class="form-control" name="cp"
							readonly="readonly" value="${ utilisateur.getCodePostal() }"
							id="cp">
					<label for="cp">Code	Postal 
					</label>
					</div>
					</div>
					<div class="col-md-6">
					<div class="form-floating">
					<input required type="text" class="form-control" name="ville"
							readonly="readonly" value="${ utilisateur.getVille() }"
							id="ville">
					<label for="ville">Ville </label>
				</div>
			</div>
		</div>
		</div>
</main>


<%@ include file="/WEB-INF/fragments/footer.jspf"%>
