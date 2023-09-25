package fr.eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.catalina.User;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.BLLException;
import fr.eni.bll.EnchereManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Article;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

@WebServlet("/detail-vente")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// récupération de l'article
		Article article = null;
		try {
			int noArticle = Integer.parseInt(request.getParameter("noArticle"));
			var articleManager = ArticleManager.getInstance();
			article = articleManager.rechercherUnArticle(noArticle);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		Enchere enchere;
		// récupération de l'enchère
		try {

			var enchereManager = EnchereManager.getInstance();
			enchere = enchereManager.enchereParArticle(article);
			request.setAttribute("enchere", enchere);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		request.getRequestDispatcher("/WEB-INF/pages/detail-vente.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int prixEnchere = Integer.parseInt(request.getParameter("prix_enchere"));
		var utilisateurEncherisseur = (Utilisateur) request.getSession().getAttribute("user");
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));

		Article article = null;

		try {
			var articleManager = ArticleManager.getInstance();
			article = articleManager.rechercherUnArticle(noArticle);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		var enchereManager = EnchereManager.getInstance();
		Enchere enchere = null;
		try {

			enchere = enchereManager.enchereParArticle(article);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		if (prixEnchere < enchere.getArticle().getMiseAPrix() || prixEnchere < enchere.getMontantEnchere()) {
			request.setAttribute("error", "Votre offre est inférieure au prix actuel");
			doGet(request, response);
			return;
		}

		if (enchere.getArticle().getVendeur().getNoUtilisateur() == utilisateurEncherisseur.getNoUtilisateur()) {
			request.setAttribute("error", "Vous ne pouvez pas enchérir sur un article que vous possédez");
			doGet(request, response);
			return;
		}

	
		

		if (enchere.getArticle().getEtatVente() != 1) {
			request.setAttribute("error",
					"Vous ne pouvez pas enchérir sur cet article car ce dernier n'est pas actuellement en vente");
			doGet(request, response);
			return;
		}

		try {
			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			// recherche de l'ancien encherisseur par son id

			if (utilisateurEncherisseur.getNoUtilisateur() == enchere.getUtilisateur().getNoUtilisateur()) {
				
				int ancienCredits = utilisateurEncherisseur.getCredit();
				int differenceCredit = prixEnchere - enchere.getMontantEnchere();
				if (differenceCredit > utilisateurEncherisseur.getCredit()) {
					request.setAttribute("error", "Vous ne disposez pas d'assez de crédits");
					doGet(request, response);
					return;
				}
				utilisateurEncherisseur.setCredit(ancienCredits -= differenceCredit);

			} else {

				if (prixEnchere > utilisateurEncherisseur.getCredit()) {
					request.setAttribute("error", "Vous ne disposez pas d'assez de crédits");
					doGet(request, response);
					return;
				}
				var ancienUtilisateur = utilisateurManager
						.unUtilisateurParID(enchere.getUtilisateur().getNoUtilisateur());
				// mise à jour des crédits de l'ancien encherisseur
				int ancienCredits = ancienUtilisateur.getCredit();
				ancienUtilisateur.setCredit(ancienCredits += prixEnchere);
				utilisateurManager.miseAJourProfilUtilisateur(ancienUtilisateur);
				int nouvelUtilisateurCredit = utilisateurEncherisseur.getCredit();
				utilisateurEncherisseur.setCredit(nouvelUtilisateurCredit -= prixEnchere);
				
				
			}
			utilisateurManager.miseAJourProfilUtilisateur(utilisateurEncherisseur);
			enchere.setUtilisateur(utilisateurEncherisseur);
			enchere.setDateEnchere(LocalDate.now());
			enchere.setMontantEnchere(prixEnchere);
			enchereManager.updateEnchere(enchere);

		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		response.sendRedirect(request.getContextPath() + "/encheres");

	}

}
