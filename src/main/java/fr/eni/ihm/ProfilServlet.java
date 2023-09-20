package fr.eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

@WebServlet("/mon-profil")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/mon-profil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
		
		String pseudo = request.getParameter("pseudo");
		Utilisateur utilisateur;
		try {
			utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(pseudo);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		var session = request.getSession();
		String choix = request.getParameter("bouton");
		
		if(choix.equals("maj")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("code_postal");
			String ville = request.getParameter("ville");
			String motDePasse = request.getParameter("mot_de_passe");
			String nouveauMDP = request.getParameter("nouveau_mdp");
			String confirmationMDP = request.getParameter("confirmation_mdp");
			
			
			
			try {
				utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(pseudo);
				
				if(utilisateur.getMotDePasse().equals(motDePasse)) {
					if(!nouveauMDP.isBlank() && nouveauMDP.equals(confirmationMDP)) {
						Utilisateur utilisateurModifié = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMDP);
						utilisateurManager.miseAJourProfilUtilisateur(utilisateurModifié);
						session.setAttribute("user", utilisateurModifié);
						
					} else {
						Utilisateur utilisateurModifié = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
						utilisateurManager.miseAJourProfilUtilisateur(utilisateurModifié);
						session.setAttribute("user", utilisateurModifié);
					}
					
				} else {
					request.setAttribute("error","Le mot de passe n'est pas le bon");
					request.getRequestDispatcher("/WEB-INF/pages/mon-profil.jsp").forward(request, response);		
					return;
					}
					
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			response.sendRedirect(request.getContextPath()+"/encheres");
		}
		
		if(choix.equals("suppr")) {
			try {
				utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(pseudo);
				utilisateurManager.supprimerCompteUtilisateur(utilisateur);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/encheres");
		}
		
		
	}

}
