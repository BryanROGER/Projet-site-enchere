package fr.eni.dal;

import fr.eni.bo.Article;
import fr.eni.bo.Retrait;

public interface RetraitDao {

	public void insert( Retrait retrait) throws DALException;

	Retrait selectByArticle(Article article) throws DALException;

	void update(Retrait retrait) throws DALException;
	
	
	
	
}
