package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDao;
import fr.eni.dal.FactoryDAO;

public class CategorieManager {

	private static CategorieManager instance;
	
	private CategorieManager() {
		
	}
	
	public static CategorieManager getInstance() {
		if(instance == null)
			instance = new CategorieManager();
		return instance;
	}
	CategorieDao categorieDao = FactoryDAO.getCategorie();
	
	
public void ajouterCategorie (Categorie categorie){
		
		categorieDao.insert(categorie);
		
	}
	
	public Categorie CategorieById(int NoCategorie ) 
	{
		return categorieDao.getById(NoCategorie);
	}

	public List<Categorie> selectionnerToutesLesCategories()
	{
		return categorieDao.getAll();
	}
	
	public void modifierCategorie(Categorie categorie) 
	{
			categorieDao.update(categorie);
		}
	

	public void supprimerCategorie(int NoCategorie) 
	{
			categorieDao.delete(NoCategorie);
		}
	}
