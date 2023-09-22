package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleDao;
import fr.eni.dal.DALException;

public class ArticleJdbcDaoImpl implements ArticleDao {
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial, no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_BY_ID = "SELECT * FROM ARTICLES_VENDUS a \r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ " inner join RETRAITS e on e.no_article = a.no_article where a.no_article = ?";

	private static final String UPDATE = "update ARTICLES set nom_article = ?, description = ?,"
			+ "							 date_debut_encheres=?, date_fin_encheres= ?, prix_initial= ?, prix_vente= ?, "
			+ "							 no_utilisateur= ?, no_categorie=?, where no_article= ? ";
	private static final String DELETE = "delete ARTICLES where no_article = ?";
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS a\r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie";

	@Override
	public void insert(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getNomArticle());
			statement.setString(2, article.getDescription());
			statement.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			statement.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			statement.setInt(5, article.getMiseAPrix());
			statement.setInt(6, article.getVendeur().getNoUtilisateur());
			statement.setInt(7, article.getCategorieArticle().getNoCategorie());

			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion d'un article"+article.toString());
		}
	}

	@Override
	public Article selectById(int id) throws DALException {

		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur, categorie);

				Retrait retrait = new Retrait(article, rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"));

				article.setLieuRetrait(retrait);

			}
			return article;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération par le noArticle : "+String.valueOf(id));
		}

	}

	@Override

	public List<Article> selectAll() throws DALException {
		try (Connection connection = ConnectionProvider.getConnection(); Statement stmt = connection.createStatement()) {

			List<Article> articles = new ArrayList<Article>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {

				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				articles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie));

			}

			return articles;

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération de tous les articles");
		}

	}

	@Override
	public void update(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(UPDATE);

			statement.setString(1, article.getNomArticle());
			statement.setString(2, article.getDescription());
			statement.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			statement.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			statement.setInt(5, article.getMiseAPrix());
			statement.setInt(6, article.getPrixVente());
			statement.setInt(7, article.getVendeur().getNoUtilisateur());
			statement.setInt(8, article.getCategorieArticle().getNoCategorie());
			statement.setNull(9, Types.INTEGER);
			statement.setInt(10, article.getNoArticle());

			statement.executeUpdate();

		} catch (Exception e) {
			throw new DALException("Erreur lors de la modification de l'article : " + article.toString());

		}
	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(DELETE);
			pstmt.setInt(1, id);
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppretion de l'article dont le noArticle est "+String.valueOf(id));
		}
	}

}
