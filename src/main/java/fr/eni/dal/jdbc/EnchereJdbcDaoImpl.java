package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.EnchereDao;

public class EnchereJdbcDaoImpl implements EnchereDao {

	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS a\r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie inner join ENCHERES e on e.no_article = a.no_article";
	private static final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";

	private static final String SELECT_BY_NAME = "SELECT * FROM ARTICLES_VENDUS a \r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ "inner join ENCHERES e on e.no_article = a.no_article where nom_article  LIKE ? or description LIKE ?";

	private static final String SELECT_BY_CATEGORIE = "select * from ARTICLES_VENDUS a\r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ "inner join ENCHERES e on e.no_article = a.no_article where c.no_categorie = ?";
	private static final String SELECT_BY_ARTICLE= "select * from encheres e\r\n"
			+ "inner join UTILISATEURS u on u.no_utilisateur = e.no_utilisateur where no_article = ?";
	private static final String UPDATE = "UPDATE encheres SET no_utilisateur=?, montant_enchere=? where no_article = ?";
	private static final String SELECT_BY_NAME_AND_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS a \r\n"
			+ "			inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "			inner join CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ "			inner join ENCHERES e on e.no_article = a.no_article where (nom_article  LIKE ?  or description LIKE ? ) and c.no_categorie=?";

	@Override
	public List<Enchere> selectAll() throws DALException {
		try (Connection connection = ConnectionProvider.getConnection(); Statement stmt = connection.createStatement()) {

			List<Enchere> encheres = new ArrayList<Enchere>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {

				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);

				encheres.add(new Enchere(utilisateur, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));

			}

			return encheres;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération de toutes les encherers");
		}

	}

	@Override
	public void insert(Enchere enchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement statement = cnx.prepareStatement(INSERT)) {

			statement.setInt(1, enchere.getArticle().getVendeur().getNoUtilisateur());
			statement.setInt(2, enchere.getArticle().getNoArticle());
			statement.setDate(3, Date.valueOf(enchere.getArticle().getDateDebutEncheres()));
			statement.setInt(4, enchere.getMontantEnchere());

			statement.executeUpdate();

		} catch (

		Exception e) {
			throw new DALException("Erreur lors de l'insertion d'une enchère : "+enchere.toString());

		}
	}

	@Override

	public List<Enchere> selectByName(String query) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME);) {

			List<Enchere> encheres = new ArrayList<Enchere>();

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);

				encheres.add(new Enchere(utilisateur, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));
			}

			return encheres;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération des enchères contenant "+query);


		}

	}

	public List<Enchere> selectByCategorie(int noCategorie) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_CATEGORIE)) {

			List<Enchere> encheres = new ArrayList<Enchere>();

			stmt.setInt(1, noCategorie);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);

				encheres.add(new Enchere(utilisateur, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));

			}

			return encheres;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération de toutes les enchères de catégorie : "+String.valueOf(noCategorie));


		}

	}

	@Override
	public Enchere selectByArticle(Article article) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ARTICLE)) {

			Enchere enchere = null;

			
			stmt.setInt(1, article.getNoArticle());
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

				enchere = (new Enchere(utilisateur, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));

			}
			return enchere;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération de l'enchère par l'article : "+article.toString());

		}

	}

	@Override
	public void update(Enchere enchere) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(UPDATE);) {

			stmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			stmt.setInt(2, enchere.getMontantEnchere());
			stmt.setInt(3, enchere.getArticle().getNoArticle());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			throw new DALException("Erreur lors de la modification de l'enchère "+enchere.toString());


		}
	}

	@Override
	public List<Enchere> selectByNameAndCategorie(String query, int noCategorie) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME_AND_CATEGORIE);) {

			List<Enchere> encheres = new ArrayList<Enchere>();

			stmt.setString(1, "%" + query + "%");
			stmt.setString(2, "%" + query + "%");
			stmt.setInt(3, noCategorie);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);

				encheres.add(new Enchere(utilisateur, article, rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")));
			}
			

			return encheres;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de la récupération des enchères contenant "+query+" et de catégorie : "+String.valueOf(noCategorie));


		}
	}
}
