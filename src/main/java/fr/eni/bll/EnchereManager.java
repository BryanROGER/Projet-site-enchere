package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Enchere;
import fr.eni.dal.DALException;
import fr.eni.dal.EnchereDao;
import fr.eni.dal.FactoryDAO;

public class EnchereManager {

	private static EnchereManager instance;

	private EnchereManager() {

	}

	public static EnchereManager getInstance() {
		if (instance == null)
			instance = new EnchereManager();
		return instance;
	}

	// Logique métier

	EnchereDao enchereDao = FactoryDAO.getEnchere();

	public List<Enchere> tousLesArticles() throws BLLException {
		try {
			return enchereDao.selectAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public void ajouterEnchere(Enchere enchere) throws BLLException {
		try {

			enchereDao.insert(enchere);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	

	public List<Enchere> encheresParCategorie(int noCategorie) throws BLLException {
		try {
			return enchereDao.selectByCategorie(noCategorie);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public Enchere enchereParArticle(Article article) throws BLLException {
		try {
			return enchereDao.selectByArticle(article);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public void updateEnchere(Enchere enchere) throws BLLException {
		try {
			if (enchere.getMontantEnchere() == 0 && enchere.getMontantEnchere() < enchere.getArticle().getMiseAPrix())
				throw new BLLException("Le montant de l'enchère doit être supérieur au prix de mise en vente");
			enchereDao.update(enchere);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public List<Enchere> enchereParFiltreEtCatégorie(String query, String libelle) throws BLLException {
		try {
			return enchereDao.selectByNameAndCategorie(query, libelle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	
	public List<Enchere> mesVentesParCategorie(int noUtilisateur, int etatVente, String query, String libelle) throws BLLException{
		try {
			return enchereDao.selectMesVentesAvecCategorie(noUtilisateur, etatVente, query, libelle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	public List<Enchere> mesAchatsParCategorie(int noUtilisateur, int etatVente, String query, String libelle) throws BLLException{
		try {
			return enchereDao.selectMesAchatsParCategorie(noUtilisateur, etatVente, query, libelle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}
	
	public List<Enchere> encheresEnCoursParCategorie (String query, String libelle) throws BLLException{
		try {
			return enchereDao.selectEnchereEnCoursParCategorie(query, libelle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());
		}
	}

}
