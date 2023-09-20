package fr.eni.dal;


import fr.eni.dal.jdbc.CategorieJdbcDaoImpl;
import fr.eni.dal.jdbc.EnchereJdbcDaoImpl;
import fr.eni.dal.jdbc.UtilisateurJdbcDaoImpl;

public class FactoryDAO {
	
	private FactoryDAO() {
		
	}
	
	public static UtilisateurDao getUtilisateur() {
		return new UtilisateurJdbcDaoImpl();
	}
	

	public static EnchereDao getEnchere() {
		return new EnchereJdbcDaoImpl();
	}

	public static ArticleDao getArticle() {
		return new ArticleJdbcDaoImpl();
		
	}
	public static CategorieDao getCategorie() {
		return new CategorieJdbcDaoImpl();

	}
}
