package fr.eni.bll;

import fr.eni.bo.Retrait;
import fr.eni.dal.DALException;
import fr.eni.dal.FactoryDAO;
import fr.eni.dal.RetraitDao;

public class RetraitManager {

	private static RetraitManager instance;

	private RetraitManager() {

	}

	public static RetraitManager getInstance() {
		if (instance == null)
			instance = new RetraitManager();
		return instance;
	}

	// Logique métier

	RetraitDao retraitDao = FactoryDAO.getRetrait();

	public void ajouterRetrait(Retrait retrait) throws BLLException {
		try {
			isValid(retrait);
			retraitDao.insert(retrait);
		} catch (DALException e) {
			throw new BLLException(e.getMessage());

		}
	}

	// validation

	public void isValid(Retrait retrait) throws BLLException {
		//vérification des champs libres
		if(retrait.getRue().isBlank())
			throw new BLLException("La rue ne peut pas être vide!");
		if(retrait.getVille().isBlank())
			throw new BLLException("La ville ne peut pas être vide!");
		if(retrait.getCodePostal().isBlank())
			throw new BLLException("Le code postal ne peut pas être vide!");
		
		//vérification de la taille des champs
		if(retrait.getRue().length() > 80)
			throw new BLLException("La rue ne peut pas dépasser 80 caractères!");
		if(retrait.getCodePostal().length()!= 5)
			throw new BLLException("Le code postal doit être égale à 5 caractères!");
		if(retrait.getVille().length() > 80)
			throw new BLLException("La ville ne peut pas dépasser 80 caractères!");
		
	}

}
