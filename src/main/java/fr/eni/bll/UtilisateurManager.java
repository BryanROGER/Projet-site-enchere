package fr.eni.bll;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.FactoryDAO;
import fr.eni.dal.UtilisateurDao;

public class UtilisateurManager {

	// Singleton

	private static UtilisateurManager instance;

	private UtilisateurManager() {

	}

	public static UtilisateurManager getInstance() {
		if (instance == null)
			instance = new UtilisateurManager();
		return instance;
	}

	// Logique métier

	UtilisateurDao utilisateurDao = FactoryDAO.getUtilisateur();

	public Utilisateur unUtilisateurParPseudoOuMail(String query) throws BLLException {
		try {
			return utilisateurDao.selectByPseudoMail(query);
		} catch (DALException e) {
			throw new BLLException("echec de récupération de l'utilisateur : " + query);
		}
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			// verification présence d'un pseudo
			if (utilisateur.getPseudo().isBlank())
				throw new BLLException("Le pseudo est obligatoire!");
			if(!StringUtils.isAlphanumeric((utilisateur.getPseudo())))
				throw new BLLException("Le pseudo ne doit pas contenir de caractères spéciaux!");
			
			isUtilisateurValide(utilisateur);
			
			utilisateurDao.insertUtilisateur(utilisateur);
		} catch (DALException e) {
			if(e.getMessage().contains("UN_utilisateurs_pseudo"))
				throw new BLLException("Ce pseudo est déjà utilisé");
			if(e.getMessage().contains("UN_utilisateurs_email"))
				throw new BLLException("Cette adresse mail est déjà utilisée");;
		}
	}

	public Utilisateur unUtilisateurParID(int id) throws BLLException {
		try {
			return utilisateurDao.selectByID(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException("echec de récupération de l'utilisateur : " + String.valueOf(id));
		}
	}

	public void miseAJourProfilUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			isUtilisateurValide(utilisateur);

			utilisateurDao.update(utilisateur);
		} catch (DALException e) {
			throw new BLLException("echec de la mise à jour de l'utilisateur : " + utilisateur.toString());
		}
	}

	public void supprimerCompteUtilisateur(Utilisateur utilisateur) throws BLLException {
		
		try {
			isUtilisateurValide(utilisateur);
			utilisateurDao.delete(utilisateur);
		} catch (DALException e) {
			throw new BLLException("echec de la suppression de l'utilisateur : " + utilisateur.toString());

		}
	}

	// vérification

	public void isUtilisateurValide(Utilisateur utilisateur) throws BLLException {

		// si utilisateur null
		if (utilisateur == null)
			throw new BLLException("Utilisateur est null");

		// vérification de champs non null
		if (utilisateur.getNom().isBlank())
			throw new BLLException("Le nom est obligatoire!");
		if (utilisateur.getPrenom().isBlank())
			throw new BLLException("Le prénom est obligatoire!");
		if (utilisateur.getVille().isBlank())
			throw new BLLException("La ville est obligatoire!");
		if (utilisateur.getRue().isBlank())
			throw new BLLException("La rue est obligatoire!");
		if (utilisateur.getCodePostal().isBlank())
			throw new BLLException("Le code postal est obligatoire!");
		if (utilisateur.getTelephone().isBlank())
			throw new BLLException("Le téléphone est obligatoire!");

		//Vérification taille des champs
		if(utilisateur.getPseudo().length() > 30)
			throw new BLLException("Le pseudo ne peut pas dépasser 30 caractères!");
		if(utilisateur.getNom().length() > 30)
			throw new BLLException("Le nom ne peut pas dépasser 30 caractères!");
		if(utilisateur.getPrenom().length() > 30)
			throw new BLLException("Le prénom ne peut pas dépasser 30 caractères!");
		if(utilisateur.getEmail().length() > 80)
			throw new BLLException("L'email ne peut pas dépasser 80 caractères!");
		if(utilisateur.getTelephone().length() > 15)
			throw new BLLException("Le téléphone ne peut pas dépasser 15 caractères!");
		if(utilisateur.getRue().length() > 80)
			throw new BLLException("La rue ne peut pas dépasser 80 caractères!");
		if(utilisateur.getCodePostal().length() != 5)
			throw new BLLException("Le code postal doit être de 5 caractères!");
		if(utilisateur.getVille().length() > 80)
			throw new BLLException("La ville ne peut pas dépasser 80 caractères!");
		if(utilisateur.getMotDePasse().length() > 30)
			throw new BLLException("Le mot de passe ne peut pas dépasser 30 caractères!");
		
		
		// vérification adresse email conforme et non null
		if(!isValidEmail(utilisateur.getEmail()))
			throw new BLLException("L'email n'est pas conforme!");
		if (utilisateur.getEmail().isBlank())
			throw new BLLException("L'email est obligatoire!");
		
	

		// validation de mot de passe
		if (utilisateur.getMotDePasse().isBlank())
			throw new BLLException("Le mot de passe est obligatoire!");
		if (utilisateur.getMotDePasse().length() < 8 || utilisateur.getMotDePasse().length() > 35)
			throw new BLLException("La taille du mot de passe doit etre entre 8 et 35");

	}
	
	public static boolean isValidEmail(String email) {
		String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
