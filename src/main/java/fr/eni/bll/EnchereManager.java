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
	
	
	public void ajouterEnchere(Enchere enchere) {
		enchereDao.insert(enchere);
	}
	

	public List<Enchere> selectionnerParNom(String query){
		return enchereDao.selectByName(query);
	}

	public List<Enchere> encheresParCategorie(int noCategorie){
		return enchereDao.selectByCategorie(noCategorie);
	}
	


}
