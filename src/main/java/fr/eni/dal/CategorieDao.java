package fr.eni.dal;

import java.util.List;
import fr.eni.bo.Categorie;

public interface CategorieDao {


	public Categorie getById(int noCategorie)throws DALException;
	public List<Categorie> getAll()throws DALException;
	public Categorie selectByLibelle(String libelle)throws DALException;
}
