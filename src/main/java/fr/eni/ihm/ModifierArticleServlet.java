package fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bll.RetraitManager;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Retrait;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifier-article")
public class ModifierArticleServlet extends HttpServlet {
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

		try {
			CategorieManager categorieManager = CategorieManager.getInstance();
			List<Categorie> categories;
			categories = categorieManager.selectionnerToutesLesCategories();
			categories.remove(0);// on enlève le libelle "tous les articles"
			request.setAttribute("categories", categories);

		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		request.getRequestDispatcher("/WEB-INF/pages/modifier-article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int noArticle = Integer.parseInt(request.getParameter("noArticle"));

		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int miseAPrix = Integer.parseInt(request.getParameter("mise_a_prix"));
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debut_enchere"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("fin_enchere"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");

		CategorieManager categorieManager = CategorieManager.getInstance();
		Categorie cat = null;
		try {
			cat = categorieManager.categorieByLibelle(categorie);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		ArticleManager articleManager = ArticleManager.getInstance();

		Article article = null;
		try {
			article = articleManager.rechercherUnArticle(noArticle);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		RetraitManager retraitManager = RetraitManager.getInstance();
		Retrait retrait = null;
		try {
			retrait = retraitManager.retraitParArticle(article);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		try {
			retrait.setCodePostal(codePostal);
			retrait.setRue(rue);
			retrait.setVille(ville);
			retraitManager.miseAJourRetrait(retrait);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		try {
			article.setLieuRetrait(retrait);
			article.setNomArticle(nomArticle);
			article.setDescription(description);
			article.setCategorieArticle(cat);
			article.setMiseAPrix(miseAPrix);
			article.setDateDebutEncheres(debutEnchere);
			article.setDateFinEncheres(finEnchere);
			articleManager.miseAJourArticle(article);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		response.sendRedirect(request.getContextPath() + "/encheres");

	}

}
