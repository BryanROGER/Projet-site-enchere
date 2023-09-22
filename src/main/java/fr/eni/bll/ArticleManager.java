package fr.eni.bll;

import java.time.LocalDate;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDao;
import fr.eni.dal.DALException;
import fr.eni.dal.FactoryDAO;

public class ArticleManager {

private static ArticleManager instance;
	
	private ArticleManager() {
		
	}
	
	public static ArticleManager getInstance() {
		if(instance == null)
			instance = new ArticleManager();
		return instance;
	}
	
	
	
	ArticleDao articleDao = FactoryDAO.getArticle();
	
	public void ajouterArticle(Article article) throws BLLException {
		try {
			isValidArticle(article);
			articleDao.insert(article);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	public Article rechercherUnArticle(int noArticle) throws BLLException {
		try {
			return articleDao.selectById(noArticle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	
	// vérification
	
	public void isValidArticle(Article article) throws BLLException {
		
		//vérification des champs vides
		if(article.getNomArticle().isBlank())
			throw new BLLException("Le nom de l'article ne peut pas être vide!");
		if(article.getDescription().isBlank())
			throw new BLLException("La description de l'article ne peut pas être vide!");
		
		//vérification de la taille des champs
		if(article.getNomArticle().length() > 30)
			throw new BLLException("Le nom de l'article ne peut pas dépasser 30 caractères!");
		if(article.getDescription().length() > 300)
			throw new BLLException("La description de l'article ne peut pas dépasser 300 caractères!");
		
		//vérification des champs spécifiques
		if(article.getDateDebutEncheres().compareTo(LocalDate.now())<0)
			throw new BLLException("La date de début d'enchère ne peut pas être inférieure à la date d'aujourd'hui!");
		LocalDate dateDebutPlus7Jours = article.getDateDebutEncheres().plusDays(7);
		if(dateDebutPlus7Jours.compareTo(article.getDateFinEncheres())<0)
			throw new BLLException("La date de fin d'enchère doit être au minimum 7 jours supérieurs à la date de début d'enchère!");

			
	}
	
	
	
}
