package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Utilisateur;

public interface UtilisateurDao {
	
	public Utilisateur selectByPseudoMail(String query)throws DALException ;
	
	public void insertUtilisateur(Utilisateur utilisateur)throws DALException ;
	
	public Utilisateur selectByID(int id)throws DALException ;
	
	public void update(Utilisateur utilisateur)throws DALException ;
	
	public void delete(Utilisateur utilisateur)throws DALException ;

	public List<Utilisateur> selectAll()throws DALException ;
	
}
