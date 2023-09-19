package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

	//attributs
	
	private int noCategorie;
	private String libelle;
	
	List<Article> articles = new ArrayList<Article>();
	
	// constructeurs
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	
	// toString
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articles=" + articles + "]";
	}
	
	
	
	//Getter et Setter

	public int getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
	
	
	
	
	
}
