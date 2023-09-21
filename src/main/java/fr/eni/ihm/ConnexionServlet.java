package fr.eni.ihm;

import java.io.IOException;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/se-connecter")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var cookies = request.getCookies();
		if (cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("sessionID")) {
					int time = (int) (System.currentTimeMillis() / 1000); // secondes
					cookie.setMaxAge(time + (3600 * 24 * 7));
					request.getSession();
				}
			}
		}
	
		request.getRequestDispatcher("WEB-INF/pages/connexion.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var utilisateurManager = UtilisateurManager.getInstance();

		String identifiant = request.getParameter("utilisateur");
		String motDePasse = request.getParameter("mot_de_passe");

		Utilisateur utilisateur =null;
		try {
			utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(identifiant);
		} catch (BLLException e) {
			request.setAttribute("error","Le champs saisit n'est pas correct");
			request.getRequestDispatcher("WEB-INF/pages/connexion.jsp").forward(request, response);
			e.printStackTrace();
			return;			
		}
		request.setAttribute("utilisateur", utilisateur);

		if (!utilisateur.getMotDePasse().equalsIgnoreCase(motDePasse)) {
			request.setAttribute("error","Le mot de passe ne correspont pas");

			request.getRequestDispatcher("WEB-INF/pages/connexion.jsp").forward(request, response);
			return;
		}

		var session = request.getSession();
		if (request.getParameter("se_souvenir_de_moi") != null) {

			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			int time = (int) (System.currentTimeMillis() / 1000); // secondes
			cookie.setMaxAge(time + (3600 * 24 * 7));
			response.addCookie(cookie);
		}

		session.setAttribute("user", utilisateur);
		response.sendRedirect(request.getContextPath() + "/encheres");

	}

}
