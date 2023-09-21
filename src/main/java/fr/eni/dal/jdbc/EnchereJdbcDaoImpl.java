package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.EnchereDao;

public class EnchereJdbcDaoImpl implements EnchereDao {

	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS a\r\n"
			+ "inner join UTILISATEURS u on a.no_utilisateur= u.no_utilisateur\r\n"
			+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie inner join ENCHERES e on e.no_article = a.no_article";
	private static final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";

	@Override
	public List<Enchere> selectAll() {
		try (Connection connection = ConnectionProvider.getConnection(); var stmt = connection.createStatement()) {

			List<Enchere> encheres = new ArrayList<Enchere>();
			var rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {

				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), utilisateur, categorie);
				
				encheres.add(new Enchere(null,article,rs.getDate("date_enchere").toLocalDate(),rs.getInt("montant_enchere")));
				
			} 
			
			return encheres;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Enchere enchere) {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement statement = cnx.prepareStatement(INSERT)
				) {

			statement.setInt(1, enchere.getArticle().getVendeur().getNoUtilisateur());
			statement.setInt(2, enchere.getArticle().getNoArticle());
			statement.setDate(3, Date.valueOf(enchere.getArticle().getDateDebutEncheres()));
			statement.setInt(4, enchere.getMontantEnchere());
			
			statement.executeUpdate();
			

		} catch (

		Exception e) {
			e.printStackTrace();

		}		
	}

}
