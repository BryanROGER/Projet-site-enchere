package fr.eni.bo;

import java.time.LocalDate;

public class Enchere {
	
	//Attributs
	private Utilisateur utilisateur;
	private Article article;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	
	//Constructeur
	public Enchere(Utilisateur utilisateur, Article article, LocalDate dateEnchere, int montantEnchere) {
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	
	// To String
	@Override
	public String toString() {
		return "Enchere [utilisateur=" + utilisateur + ", article=" + article + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}



	//Getter et Setter
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	
	
	
	
	
	
	

}
