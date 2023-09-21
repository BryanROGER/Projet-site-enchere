package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.dal.RetraitDao;

public class RetraitJdbcDaoImpl implements RetraitDao {

	private static final String INSERT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES  ( ?,?, ?, ?)";
	private static final String SELECT_BY_ADRESSE = "selet * from retrait where rue=? and ville=? and code_postal =? ";

	@Override
	public void insert(int noArticle, Retrait retrait) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT);
			statement.setInt(1, noArticle);
			statement.setString(2, retrait.getRue());
			statement.setString(3, retrait.getCodePostal());
			statement.setString(4, retrait.getVille());

			statement.executeUpdate();

			

		} catch (

		Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public Retrait selectByAdresse(String ville, String codePostal, String rue) {
		Retrait retrait = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(SELECT_BY_ADRESSE);
			statement.setString(1, rue);
			statement.setString(2, ville);
			statement.setString(3, codePostal);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				retrait = new Retrait(rs.getInt("no_retrait"), rs.getString("rue"),rs.getString("ville"),rs.getString("code_postal"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
