package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;

public interface ArticleDao {

	public void insert(Article article) throws DALException ;
	
	public Article selectById(int id) throws DALException ;
	
	public void update (Article article) throws DALException ;
	
	public void delete (int id) throws DALException ;
	
	List<Article> selectAll() throws DALException ;
	
	
}


