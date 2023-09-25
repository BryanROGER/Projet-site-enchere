package fr.eni.ihm;

import java.io.IOException;
import java.util.List;

import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.EnchereManager;
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

		EnchereManager enchereManager = EnchereManager.getInstance();
		var session = request.getSession();
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

		String filtre = request.getParameter("string_filter");
		String noCategorieStr = request.getParameter("categorie");

		List<Enchere> encheres = null;
		request.setAttribute("encheres", null);

		// si barre de recherche non null et libelle "tous les articles"
		if (filtre != null && !filtre.isBlank() && noCategorieStr.equals("1")) {
			try {
				encheres = enchereManager.selectionnerParNom(filtre);
				request.setAttribute("encheres", encheres);
				request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
				return;
			} catch (BLLException e) {
				request.setAttribute("error", e.getMessage());
				doGet(request, response);
				e.printStackTrace();
				return;
			}

		}

		// si libelle actif
		if (noCategorieStr != null && !noCategorieStr.equals("1")) {
			int noCategorie = Integer.parseInt(noCategorieStr);
			try {

				encheres = enchereManager.enchereParFiltreEtCatégorie(filtre, noCategorie);
				request.setAttribute("encheres", encheres);
				request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
				return;
			} catch (BLLException e) {
				request.setAttribute("error", e.getMessage());
				doGet(request, response);
				e.printStackTrace();
				return;
			}

		}

		if (filtre == null)
			filtre = "";

		int noCategorie = 1;
		if (noCategorieStr != null)
			noCategorie = Integer.parseInt(noCategorieStr);

		String venteChecked = request.getParameter("vente");

		if (venteChecked != null) {
			session = request.getSession();
			var user = (Utilisateur) session.getAttribute("user");

			switch (venteChecked) {
			case "mesVentesEnCours":
				try {
					if (noCategorieStr.equals("1")) {// toutes les catégories
						encheres = enchereManager.mesVentes(user.getNoUtilisateur(), 1, filtre); // 1 = etat vente
																									// en cours

					} else {
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 1, filtre, noCategorie);
					}

				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;
			case "ventesNonDebutees":
				try {

					if (noCategorieStr.equals("1")) {// toutes les catégories
						encheres = enchereManager.mesVentes(user.getNoUtilisateur(), 0, filtre); // 0 = etat vente
																									// non débutées

					} else {
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 0, filtre, noCategorie);
					}

				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;

			case "ventesTerminees":
				try {
					if (noCategorieStr.equals("1")) {// toutes les catégories
						encheres = enchereManager.mesVentes(user.getNoUtilisateur(), 2, filtre); // 2 = etat vente
																									// terminées

					} else {
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 2, filtre, noCategorie);
					}

				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;
			default:

			}
		}

		String achatChecked = request.getParameter("achat");

		if (achatChecked != null) {

			var user = (Utilisateur) session.getAttribute("user");

			switch (achatChecked) {
			case "encheresOuvertes":
				try {
					if (noCategorieStr.equals("1")) {// toutes les catégories
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 1, filtre, noCategorie);
					} else {
						enchereManager.encheresEnCoursParCategorie(achatChecked, noCategorie);
					}
					encheres = enchereManager.encheresEnCours(filtre);
					request.setAttribute("encheres", encheres);
				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;
			case "mesEncheresEnCours":
				try {
					if (noCategorieStr.equals("1")) {// toutes les catégories
						encheres = enchereManager.mesAchats(user.getNoUtilisateur(), 1, filtre); // 1 = enchères en
																									// cours

					} else {
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 1, filtre, noCategorie);
					}

				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;

			case "mesEncheresRemportees":
				try {
					if (noCategorieStr.equals("1")) {// toutes les catégories
						encheres = enchereManager.mesAchats(user.getNoUtilisateur(), 2, filtre); // 1 = enchères en
																									// cours

					} else {
						enchereManager.mesVentesParCategorie(user.getNoUtilisateur(), 2, filtre, noCategorie);
					}

				} catch (BLLException e) {
					e.printStackTrace();
				}
				break;
			default:

			}
		}

		if (encheres != null)
			request.setAttribute("encheres", encheres);
		else {
			// si barre de recherche null et libelle "tous les articles"

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