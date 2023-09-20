package fr.eni.dal;

import fr.eni.bo.Utilisateur;

public interface UtilisateurDao {
	
	public Utilisateur selectByPseudoMail(String query);
	
	public void insertUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur selectByID(int id);
	
	public void update(Utilisateur utilisateur);
	
	public void delete(Utilisateur utilisateur);

}
