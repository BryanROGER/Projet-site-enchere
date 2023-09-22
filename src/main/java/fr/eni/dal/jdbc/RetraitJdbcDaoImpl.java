package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.bo.Retrait;
import fr.eni.dal.DALException;
import fr.eni.dal.RetraitDao;

public class RetraitJdbcDaoImpl implements RetraitDao {

	private static final String INSERT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES  ( ?,?, ?, ?)";

	@Override
	public void insert(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT);
			statement.setInt(1, retrait.getArticle().getNoArticle());
			statement.setString(2, retrait.getRue());
			statement.setString(3, retrait.getCodePostal());
			statement.setString(4, retrait.getVille());

			statement.executeUpdate();

			

		} catch (

		Exception e) {
			throw new DALException("Erreur lors de l'insertion d'un retrait " + retrait.toString());

		}

	}

	


}
