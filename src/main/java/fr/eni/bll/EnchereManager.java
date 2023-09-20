package fr.eni.bll;

import java.util.List;


import fr.eni.bo.Enchere;

import fr.eni.dal.EnchereDao;
import fr.eni.dal.FactoryDAO;

public class EnchereManager {
	
private static EnchereManager instance;
	
	private EnchereManager() {
		
	}
	
	public static EnchereManager getInstance() {
		if(instance == null)
			instance = new EnchereManager();
		return instance;
	}
	
	
	
	// Logique métier
	
	EnchereDao enchereDao = FactoryDAO.getEnchere();
	
	public List<Enchere> tousLesArticles(){
		return enchereDao.selectAll();
	}
	
	
	

}
