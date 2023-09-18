package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.FactoryDAO;
import fr.eni.dal.UtilisateurDao;

public class UtilisateurManager {

	// Singleton
	
	private static UtilisateurManager instance;
	
	private UtilisateurManager() {
		
	}
	
	public UtilisateurManager getIntance() {
		if(instance == null)
			instance = new UtilisateurManager();
		return instance;
	}
	
	
	
	// Logique métier
	
	UtilisateurDao utilisateurDao = FactoryDAO.getUtilisateur();
	
	
	
	public Utilisateur unUtilisateurParPseudoOuMail(String query) {
		return utilisateurDao.selectByPseudoMail(query);
	}
	
	
	
	
	
}
