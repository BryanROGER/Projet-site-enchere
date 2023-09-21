package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleDao;
import fr.eni.dal.CategorieDao;
import fr.eni.dal.FactoryDAO;
import fr.eni.dal.UtilisateurDao;

public class ArticleJdbcDaoImpl implements ArticleDao {
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial, no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_BY_ID = "SELECT * FROM ARTICLES_VENDUS a \r\n"
	+"inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ " inner join RETRAITS e on e.no_article = a.no_article where a.no_article = ?";
	private static final String GET_BY_CATEGORIE = "SELECT *" +
            "FROM ARTICLES_VENDUS " +
            "INNER JOIN CATEGORIES C on ARTICLES_VENDUS.no_categorie = C.no_categorie " +
            "WHERE C.no_categorie = ?";
	private static final String GET_BY_VENDEUR = "select * from ARTICLES where no_utilisateur= ?";
	private static final String UPDATE = "update ARTICLES set nom_article = ?, description = ?,"
			+ "							 date_debut_encheres=?, date_fin_encheres= ?, prix_initial= ?, prix_vente= ?, "
			+ "							 no_utilisateur= ?, no_categorie=?, where no_article= ? ";
	private static final String DELETE = "delete ARTICLES where no_article = ?";
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS a\r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie";
	private static final String SELECT_BY_NAME = "SELECT * " +
            "FROM ARTICLES_VENDUS " +
            "WHERE nom_article LIKE ?";

	@Override
	public void insert(Article article) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, article.getNomArticle());
			statement.setString(2, article.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			statement.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
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
		
		}
	}

	@Override
	public Article selectById(int id) {
		
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(GET_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				
				

				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);
				

				Retrait retrait = new Retrait(article, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
				article.setLieuRetrait(retrait);
				
				

			}
			return article;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override

		public List<Article> selectAll() {
					try (Connection connection = ConnectionProvider.getConnection(); 
							var stmt = connection.createStatement()) {

						List<Article> articles = new ArrayList<Article>();
						var rs = stmt.executeQuery(SELECT_ALL);

						while (rs.next()) {

							Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"),
									rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
									rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
									rs.getInt("credit"), rs.getBoolean("administrateur"));

							Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

							articles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
									rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
									rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
									rs.getInt("prix_vente"), utilisateur, categorie));
							
						} 
						
						return articles;

					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			


	
	@Override
	public void update(Article article) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement statement = cnx.prepareStatement(UPDATE);

			statement.setString(1, article.getNomArticle());
			statement.setString(2, article.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			statement.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			statement.setInt(5, article.getMiseAPrix());
			statement.setInt(6, article.getPrixVente());
			statement.setInt(7, article.getVendeur().getNoUtilisateur());
			statement.setInt(8, article.getCategorieArticle().getNoCategorie());
			if (article.getLieuRetrait() != null) {
				//statement.setInt(9, article.getLieuRetrait());
			} else {
				statement.setNull(9, Types.INTEGER);
			}
			statement.setInt(10, article.getNoArticle());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			

		}
	}


	@Override
	public void delete(int id) {
		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(DELETE);
			pstmt.setInt(1, id); } 
		catch (SQLException e) {
	            e.printStackTrace();
		}
	}
	
	
	
	private Article articleBuilder(ResultSet rs) throws SQLException  {
		Utilisateur vendeur = this.getVendeurArticle(rs.getInt("no_utilisateur"));
		Categorie categorie = this.getCategorieArticle(rs.getInt("no_categorie"));
		
		Retrait retrait = this.getRetraitArticle(rs.getInt("no_retrait"));

		Article article = new Article();

		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres((rs.getDate("date_debut_encheres").toLocalDate()));
		article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		article.setMiseAPrix(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		article.setVendeur(vendeur);
		article.setCategorieArticle(categorie);
		article.setLieuRetrait(retrait);
		

		return article;

    }
	private Utilisateur getVendeurArticle(int vendeurId) {
		UtilisateurDao utilisateurDao = FactoryDAO.getUtilisateur();
		Utilisateur vendeurArticle = null;
		try {
			vendeurArticle = utilisateurDao.selectByID(vendeurId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendeurArticle;
	}
	private Categorie getCategorieArticle(int categorieId) {
		CategorieDao categorieDAO = FactoryDAO.getCategorie();
		Categorie categorieArticle = null;
		try {
			categorieArticle = categorieDAO.getById(categorieId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorieArticle;
	}
	private Retrait getRetraitArticle(int retraitId) {
		//RetraitDao retraitDAO = FactoryDAO.getRetrait();
		Retrait retraitArticle = null;
		try {
		//	retraitArticle = retraitDAO.getById(retraitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retraitArticle;
	}

	@Override
	public List<Article> filterByString(String filter) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.createStatement()) {
	
		List<Article> articles = new ArrayList<Article>();
            PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_NAME);
            pstmt.setString(1, "%" + filter + "%");
            pstmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
            	Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				articles.add(new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie));
				
			} 
			
			return articles;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
