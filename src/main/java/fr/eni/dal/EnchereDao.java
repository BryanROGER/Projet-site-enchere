package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

public interface EnchereDao {

	public List<Enchere> selectAll();
	
	public void insert(Enchere enchere);
	

	public List<Enchere> selectByName (String query);

	public List<Enchere> selectByCategorie(int noCategorie);
	
	public Enchere selectByArticle(Article article);
	
	public void update(Enchere enchere);
	

}
