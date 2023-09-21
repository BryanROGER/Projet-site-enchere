package fr.eni.bll;

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
	
	
	
	ArticleDao articleDao = FactoryDAO.getArticle();
	
	public void ajouterArticle(Article article) {
		articleDao.insert(article);
	}
	
	public Article rechercherUnArticle(int noArticle) {
		return articleDao.selectById(noArticle);
	}
	
	
	
}
