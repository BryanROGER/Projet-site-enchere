package fr.eni.ihm;

import java.io.IOException;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var utilisateurManager = UtilisateurManager.getInstance();

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("mot_de_passe");
		String confirmationMDP = request.getParameter("confirmation_mdp");

		Utilisateur utilisateur = null;

		try {
			if (motDePasse.equals(confirmationMDP)) {
				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
						motDePasse);
				utilisateurManager.ajouterUtilisateur(utilisateur);
				utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(pseudo);

			} else {
				request.setAttribute("error", "Le mot de passe et la confirmation ne sont pas identiques");
				doGet(request, response);
				return;
			}

		} catch (BLLException e) {
			
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		var session = request.getSession();

		session.setAttribute("user", utilisateur);
		request.getSession().setAttribute("success", "Le compte a bien été créer!");
		response.sendRedirect(request.getContextPath() + "/encheres");

	}

}
