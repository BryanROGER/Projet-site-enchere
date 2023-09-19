package fr.eni.bo;

import java.util.ArrayList;
import java.util.List;

public class Retrait {
	
	//Attributs
	private List<Article> article = new ArrayList<Article>();
	private String rue;
	private String codePostal;
	private String ville;
	
	//Constructeur
	public Retrait(List<Article> article, String rue, String codePostal, String ville) {
		super();
		this.article = article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	@Override
	public String toString() {
		return "Retrait [article=" + article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}


	public List<Article> getArticle() {
		return article;
	}


	public void setArticle(List<Article> article) {
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
