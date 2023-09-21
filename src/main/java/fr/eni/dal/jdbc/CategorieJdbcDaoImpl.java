package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDao;

public class CategorieJdbcDaoImpl implements CategorieDao {

	private static final String INSERT = "insert into CATEGORIES values (?)";
	private static final String GET_BY_ID = "select * from CATEGORIES where no_categorie = ?";
	private static final String GET_ALL = "select * from CATEGORIES";
	private static final String UPDATE = "update CATEGORIES set libelle = ? where no_categorie = ?";
	private static final String DELETE = "delete CATEGORIES where no_categorie=?";
	private static final String GET_BY_LIBELLE = "Select * from CATEGORIES where libelle = ?";

	@Override
	public void insert(Categorie categorie) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, categorie.getLibelle());

			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public Categorie getById(int noCategorie) {
		Categorie categorie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_BY_ID);
			statement.setInt(1, noCategorie);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), (rs.getString("libelle")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;

	}

	@Override
	public List<Categorie> getAll() {
		List<Categorie> categories = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_ALL);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			
			return categories;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return categories;
	}

	@Override
	public void update(Categorie categorie) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(UPDATE);

			statement.setInt(1, categorie.getNoCategorie());
			statement.setString(2, categorie.getLibelle());

			statement.executeUpdate();
		} catch (Exception e) {
		}
	}

	@Override
	public void delete(int noCategorie) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(DELETE);

			statement.setInt(1, noCategorie);

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Categorie selectByLibelle(String libelle) {

		Categorie categorie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(GET_BY_LIBELLE);
			statement.setString(1, libelle);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), (rs.getString("libelle")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;

	}
}
