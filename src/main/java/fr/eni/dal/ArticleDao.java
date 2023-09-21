package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Retrait;

public interface ArticleDao {

	public void insert(Article article);
	
	public Article selectById(int id);
	
	public void update (Article article) ;
	
	public void delete (int id) ;
	
	List<Article> selectAll();
	
	public List<Article> filterByString(String filter);
}


