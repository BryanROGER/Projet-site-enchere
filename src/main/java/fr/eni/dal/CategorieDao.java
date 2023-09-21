package fr.eni.dal;

import java.util.List;
import fr.eni.bo.Categorie;

public interface CategorieDao {

	public void insert( Categorie categorie );
	public Categorie getById(int noCategorie);
	public List<Categorie> getAll();
	public void update (Categorie categorie);
	public void delete (int noCategorie);
	public Categorie selectByLibelle(String libelle);
}
