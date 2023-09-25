package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.UtilisateurDao;

public class UtilisateurJdbcDaoImpl implements UtilisateurDao {

	private static final String SELECT_BY_PSEUDO_OR_MAIL = "SELECT * FROM utilisateurs WHERE pseudo = ? or email = ?";
	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String UPDATE = "UPDATE utilisateurs SET nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE pseudo = ?";
	private static final String DELETE = "DELETE FROM utilisateurs WHERE pseudo = ?";
	private static final String SELECT_ALL = "SELECT * FROM utilisateurs";

	@Override
	public Utilisateur selectByPseudoMail(String query) throws DALException {
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_PSEUDO_OR_MAIL)) {

			stmt.setString(1, query);
			stmt.setString(2, query);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}

			

		} catch (Exception e) {
			
			throw new DALException(e.toString());
		}
		return null;
		
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT, SQLServerStatement.RETURN_GENERATED_KEYS);) {

			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());

			stmt.executeUpdate();

			ResultSet genetatedKeys = stmt.getGeneratedKeys();

			if (genetatedKeys.next())
				utilisateur.setNoUtilisateur(genetatedKeys.getInt(1));
			

		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public Utilisateur selectByID(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			else
				throw new DALException("La récupération de l'utilisateur à échouée ");

		} catch (Exception e) {
			throw new DALException(e.toString());
		}
		
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(UPDATE);) {

			stmt.setString(1, utilisateur.getNom());
			stmt.setString(2, utilisateur.getPrenom());
			stmt.setString(3, utilisateur.getEmail());
			stmt.setString(4, utilisateur.getTelephone());
			stmt.setString(5, utilisateur.getRue());
			stmt.setString(6, utilisateur.getCodePostal());
			stmt.setString(7, utilisateur.getVille());
			stmt.setString(8, utilisateur.getMotDePasse());
			stmt.setInt(9, utilisateur.getCredit());
			stmt.setString(10, utilisateur.getPseudo());

			int lignesAffectées = stmt.executeUpdate();

			if (lignesAffectées == 0)
				throw new DALException("Echec de modification de l'utilisateur : " + utilisateur.toString());

		} catch (Exception e) {
			throw new DALException("Erreur lors de la modification de l'utilisateur : " + utilisateur.toString());
		}
	}

	@Override
	public void delete(Utilisateur utilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement stmt = connection.prepareStatement(DELETE)) {

			stmt.setString(1, utilisateur.getPseudo());

			int lignesAffectées = stmt.executeUpdate();

			if (lignesAffectées == 0)
				throw new DALException("Echec de modification de l'utilisateur : " + utilisateur.toString());

		} catch (Exception e) {
			throw new DALException("Erreur lors de la suppression de l'utilisateur : "+utilisateur.toString());
		}

	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try (Connection connection = ConnectionProvider.getConnection(); Statement stmt = connection.createStatement()) {

			List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {

				utilisateurs.add(new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur")));
			}

		} catch (Exception e) {
			throw new DALException("Erreur lors de la récupération de tous les utilisateurs");
		}
		return null;
	}

}
