package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;

public interface ArticleDao {

	public List<Article> selectAll();
}
