package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Enchere;

public interface EnchereDao {

	public List<Enchere> selectAll();
	
	public void insert(Enchere enchere);
}
