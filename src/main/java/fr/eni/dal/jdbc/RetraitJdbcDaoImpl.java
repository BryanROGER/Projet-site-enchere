package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.bo.Article;
import fr.eni.bo.Retrait;
import fr.eni.dal.DALException;
import fr.eni.dal.RetraitDao;

public class RetraitJdbcDaoImpl implements RetraitDao {

	private static final String INSERT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES  ( ?,?, ?, ?)";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM RETRAITS WHERE no_article = ?";
	private static final String UPDATE = "UPDATE retraits SET rue=?,code_postal = ?, ville=? WHERE no_article = ?";

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
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion d'un retrait " + retrait.toString());

		}

	}

	@Override
	public Retrait selectByArticle(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement statement = cnx.prepareStatement(SELECT_BY_ARTICLE);) {

			statement.setInt(1, article.getNoArticle());

			var rs = statement.executeQuery();
			
			if(rs.next()) {
				return new Retrait(rs.getInt("no_article"),rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de la récupération d'un retrait par article : " + article);

		}
		return null;
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(UPDATE);
			statement.setInt(4, retrait.getArticle().getNoArticle());
			statement.setString(1, retrait.getRue());
			statement.setString(2, retrait.getCodePostal());
			statement.setString(3, retrait.getVille());

			statement.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de la mise à jour d'un retrait " );

		}

	}

}
