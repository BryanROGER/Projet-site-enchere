package fr.eni.dal;

import fr.eni.dal.jdbc.UtilisateurJdbcDaoImpl;

public class FactoryDAO {
	
	private FactoryDAO() {
		
	}
	
	public static UtilisateurDao getUtilisateur() {
		return new UtilisateurJdbcDaoImpl();
	}

}
