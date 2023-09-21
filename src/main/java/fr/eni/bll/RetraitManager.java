package fr.eni.bll;

import fr.eni.bo.Retrait;
import fr.eni.dal.FactoryDAO;
import fr.eni.dal.RetraitDao;

public class RetraitManager {

private static RetraitManager instance;
	
	private RetraitManager() {
		
	}
	
	public static RetraitManager getInstance() {
		if(instance == null)
			instance = new RetraitManager();
		return instance;
	}
	
	
	
	// Logique métier
	
	RetraitDao retraitDao = FactoryDAO.getRetrait();
	
	public void ajouterRetrait(Retrait retrait) {
		retraitDao.insert(retrait);
	}
	
	
}
