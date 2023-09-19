package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDao;
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
	
	
	
	// Logique métier
	
	ArticleDao articleDao = FactoryDAO.getArticle();
	
	public List<Article> tousLesArticles(){
		return articleDao.selectAll();
	}
	
	
	

}
