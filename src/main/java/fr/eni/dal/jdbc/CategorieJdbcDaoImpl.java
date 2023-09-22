package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDao;
import fr.eni.dal.DALException;

public class CategorieJdbcDaoImpl implements CategorieDao {

	
	private static final String GET_BY_ID = "select * from CATEGORIES where no_categorie = ?";
	private static final String GET_ALL = "select * from CATEGORIES";
	private static final String GET_BY_LIBELLE = "Select * from CATEGORIES where libelle = ?";

	

	@Override
	public Categorie getById(int noCategorie) throws DALException {
		Categorie categorie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, noCategorie);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), (rs.getString("libelle")));
			}

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération d'une catégorie au noCatégorie " + String.valueOf(noCategorie));
		}
		return categorie;

	}

	@Override
	public List<Categorie> getAll() throws DALException {
		List<Categorie> categories = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_ALL);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			
			return categories;
			
		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération des catégories" + categories.toString());

		}

	}

	
	@Override
	public Categorie selectByLibelle(String libelle) throws DALException {

		Categorie categorie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_BY_LIBELLE);
			statement.setString(1, libelle);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), (rs.getString("libelle")));
			}

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération d'une catégorie au libelle " + libelle);
		}
		return categorie;

	}
}
