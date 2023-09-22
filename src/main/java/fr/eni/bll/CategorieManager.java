package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDao;
import fr.eni.dal.DALException;
import fr.eni.dal.FactoryDAO;

public class CategorieManager {

	private static CategorieManager instance;

	private CategorieManager() {

	}

	public static CategorieManager getInstance() {
		if (instance == null)
			instance = new CategorieManager();
		return instance;
	}

	CategorieDao categorieDao = FactoryDAO.getCategorie();

	public Categorie categorieById(int NoCategorie) throws BLLException {
		try {
			return categorieDao.getById(NoCategorie);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public List<Categorie> selectionnerToutesLesCategories() throws BLLException {
		try {
			return categorieDao.getAll();
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	public Categorie categorieByLibelle(String libelle) throws BLLException {
		try {
			return categorieDao.selectByLibelle(libelle);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

}
