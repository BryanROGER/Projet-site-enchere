package fr.eni.ihm;

import java.io.IOException;
import java.sql.Timestamp;

import fr.eni.bll.UtilisateurManager;
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

		var utilisateur = utilisateurManager.unUtilisateurParPseudoOuMail(identifiant);
		request.setAttribute("utilisateur", utilisateur);

		if (!utilisateur.getMotDePasse().equalsIgnoreCase(motDePasse)) {
			// exception à gérer mauvais mot de passe
			System.out.println("mauvais mot de passe");
			request.getRequestDispatcher("WEB-INF/pages/connexion.jsp").forward(request, response);
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
