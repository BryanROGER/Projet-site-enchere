package fr.eni.ihm;

import java.io.IOException;
import java.util.List;

import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/encheres")
public class EncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// récupération des catégories pour les transmettre à la vue
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> categories = null;
		try {
			categories = categorieManager.selectionnerToutesLesCategories();
			request.setAttribute("categories", categories);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		// récupération des paramètres et vérification si null
		String filtre = request.getParameter("string_filter") == null ? "" : request.getParameter("string_filter");
		String libelleCategorie = request.getParameter("categorie") == null ? "" : request.getParameter("categorie");

		// création d'une arraylist enchere, on vide la valeur dans la requête par
		// securité
		EnchereManager enchereManager = EnchereManager.getInstance();
		List<Enchere> encheres = null;
		request.setAttribute("encheres", null);

		// si radio bouton vente
		String venteChecked = request.getParameter("vente");
		var session = request.getSession();
		if (venteChecked != null) {
			session = request.getSession();
			var user = (Utilisateur) session.getAttribute("user");

			try {
				switch (venteChecked) {
				case "mesVentesEnCours":
					encheres = enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), Article.EN_COURS, filtre, libelleCategorie);
					break;

				case "ventesNonDebutees":
					encheres = enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), Article.NON_DEBUTEE, filtre, libelleCategorie);
					break;

				case "ventesTerminees":
					encheres = enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), Article.TERMINEE, filtre,	libelleCategorie);
					break;
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}

		// si radio bouton achat
		String achatChecked = request.getParameter("achat");

		if (achatChecked != null) {

			var user = (Utilisateur) session.getAttribute("user");

			try {
				switch (achatChecked) {
				case "encheresOuvertes":
					encheres = enchereManager.encheresEnCoursParCategorie(filtre, libelleCategorie);
					break;
					
				case "mesEncheresEnCours":
					encheres = enchereManager.mesAchatsParCategorie(user.getNoUtilisateur(), Article.EN_COURS, filtre,
							libelleCategorie);
					break;

				case "mesEncheresRemportees":
					encheres = enchereManager.mesAchatsParCategorie(user.getNoUtilisateur(), Article.TERMINEE, filtre,
							libelleCategorie);
					break;

				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}

		if (encheres != null) {
			request.setAttribute("encheres", encheres);
			request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
			return;
		} else if ((libelleCategorie.isBlank() != filtre.isBlank()) || !libelleCategorie.isBlank()) { 
					// récupération si barre de recherche ou catégorie non vide
			try {
				encheres = enchereManager.enchereParFiltreEtCatégorie(filtre, libelleCategorie);
				request.setAttribute("encheres", encheres);
				request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
				return;
			} catch (BLLException e) {
				request.setAttribute("error", e.getMessage());
				doGet(request, response);
				e.printStackTrace();
				return;
			}

		} else {
			// si tous les champs null

			try {
				encheres = enchereManager.tousLesArticles();
				request.setAttribute("encheres", encheres);
			} catch (BLLException e) {
				request.setAttribute("error", e.getMessage());
				doGet(request, response);
				e.printStackTrace();
				return;
			}

		}

		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);

	}

}