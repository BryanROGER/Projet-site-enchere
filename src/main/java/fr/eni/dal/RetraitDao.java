package fr.eni.dal;

import fr.eni.bo.Retrait;

public interface RetraitDao {

	public void insert( Retrait retrait);
	
	public Retrait selectByAdresse(String ville, String codePostal, String rue);
	
	
}
