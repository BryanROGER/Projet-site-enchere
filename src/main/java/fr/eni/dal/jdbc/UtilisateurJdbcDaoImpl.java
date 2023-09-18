package fr.eni.dal.jdbc;

import java.sql.Connection;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.UtilisateurDao;

public class UtilisateurJdbcDaoImpl implements UtilisateurDao{

	private static final String SELECT_BY_PSEUDO_OR_MAIL = "SELECT * FROM utilisateurs WHERE pseudo = ? or email = ?";

	@Override
	public Utilisateur selectByPseudoMail(String query) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_PSEUDO_OR_MAIL)
				) {

			stmt.setString(1, query);
			stmt.setString(2, query);
			var rs = stmt.executeQuery();
			
			if(rs.next())
				return new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("numéro téléphone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur"));
			else
				throw new DALException("La récupération de l'utilisateur à échoue ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}		return null;
	}

}
