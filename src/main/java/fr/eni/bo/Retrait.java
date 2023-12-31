package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.BLLException;

public class Retrait {
	
	//Attributs
	private Article article;
	private String rue;
	private String codePostal;
	private String ville;
	
	//Constructeur
	public Retrait(Article article, String rue, String codePostal, String ville) {
		super();
		this.article = article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Retrait( String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Retrait(int noArticle, String rue, String codePostal, String ville) throws BLLException {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		var articleManager = ArticleManager.getInstance();
		this.article = articleManager.rechercherUnArticle(noArticle);
	}


	@Override
	public String toString() {
		return "Retrait [article=" + article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}






	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
	
	
	

}
