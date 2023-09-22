package fr.eni.ihm;

import java.io.IOException;
import java.util.List;

import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
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
		if (filtre != null && noCategorieStr.equals("1")) {
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
			try {
				int noCategorie = Integer.parseInt(noCategorieStr);
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

		// si barre de recherche null et libelle "tous les articles"
		if (filtre == null && (noCategorieStr == null || noCategorieStr.equals("1"))) {
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