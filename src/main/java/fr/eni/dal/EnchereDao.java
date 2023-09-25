package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Enchere;

public interface EnchereDao {

	public List<Enchere> selectAll()throws DALException;
	
	public void insert(Enchere enchere)throws DALException;
	

	public List<Enchere> selectByName (String query)throws DALException;

	public List<Enchere> selectByCategorie(int noCategorie)throws DALException;
	
	public Enchere selectByArticle(Article article)throws DALException;
	
	public void update(Enchere enchere)throws DALException;
	
	public List<Enchere> selectByNameAndCategorie(String query, int noCategorie) throws DALException;

	List<Enchere> selectMesVentes(int noUtilisateur, int etatVente, String query) throws DALException;

	public List<Enchere> selectEnchereEnCours(String Query) throws DALException;

	List<Enchere> selectMesVentesAvecCategorie(int noUtilisateur, int etatVente, String query, int categorie) throws DALException;

	List<Enchere> selectMesAchats(int noUtilisateur, int etatVente, String filtre) throws DALException;

	List<Enchere> selectMesAchatsParCategorie(int noUtilisateur, int etatVente, String query, int noCategorie)
			throws DALException;

	List<Enchere> selectEnchereEnCoursParCategorie(String query, int noCategorie) throws DALException;
	

}
