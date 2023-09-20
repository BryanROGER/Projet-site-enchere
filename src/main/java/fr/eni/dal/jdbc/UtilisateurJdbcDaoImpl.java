package fr.eni.dal.jdbc;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.UtilisateurDao;

public class UtilisateurJdbcDaoImpl implements UtilisateurDao{
	
	private static final String SELECT_BY_PSEUDO_OR_MAIL = "SELECT * FROM utilisateurs WHERE pseudo = ? or email = ?";
	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String UPDATE = "UPDATE utilisateurs SET nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE pseudo = ?";
	private static final String DELETE = "DELETE FROM utilisateurs WHERE pseudo = ?";

	@Override
	public Utilisateur selectByPseudoMail(String query) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_PSEUDO_OR_MAIL)
				) {

			stmt.setString(1, query);
			stmt.setString(2, query);
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
				}
				
			else
				throw new DALException("La récupération de l'utilisateur à échouée ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(INSERT,SQLServerStatement.RETURN_GENERATED_KEYS);
				) {
			
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
			
			var genetatedKeys = stmt.getGeneratedKeys();
			
			if(genetatedKeys.next())
				utilisateur.setNoUtilisateur(genetatedKeys.getInt(1));
			else 
				throw new DALException("Echec de récupération du n° utilisateur : "+utilisateur.toString());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	@Override
	public Utilisateur selectByID(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_ID)
				) {

			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			if(rs.next())
				return new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
			else
				throw new DALException("La récupération de l'utilisateur à échouée ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(UPDATE);
				) {
			
			
			stmt.setString(1, utilisateur.getNom());
			stmt.setString(2, utilisateur.getPrenom());
			stmt.setString(3, utilisateur.getEmail());
			stmt.setString(4, utilisateur.getTelephone());
			stmt.setString(5, utilisateur.getRue());
			stmt.setString(6, utilisateur.getCodePostal());
			stmt.setString(7, utilisateur.getVille());
			stmt.setString(8, utilisateur.getMotDePasse());
			stmt.setString(9, utilisateur.getPseudo());
			
			
			var lignesAffectées = stmt.executeUpdate();
			

			
			if(lignesAffectées == 0)
				throw new DALException("Echec de modification de l'utilisateur : "+utilisateur.toString());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection();
				var stmt = connection.prepareStatement(DELETE)
				) {

			stmt.setString(1, utilisateur.getPseudo());
			
			
			var lignesAffectées = stmt.executeUpdate();
			

			
			if(lignesAffectées == 0)
				throw new DALException("Echec de modification de l'utilisateur : "+utilisateur.toString());
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}		
			
	}
	
	
	
	

}
