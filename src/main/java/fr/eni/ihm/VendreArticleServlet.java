package fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.bo.Utilisateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/vendre")
public class VendreArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			CategorieManager categorieManager = CategorieManager.getInstance();
			List<Categorie> categories;
			categories = categorieManager.selectionnerToutesLesCategories();
			categories.remove(0);// on enlève le libelle "tous les articles"
			request.setAttribute("categories", categories);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/vendreArticle.jsp");
			dispatcher.forward(request, response);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int miseAPrix = Integer.parseInt( request.getParameter("prix"));
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debut_enchere"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("fin_enchere"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		
		
		
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		Categorie categorieArticle=null;
		try {
			categorieArticle = categorieManager.categorieByLibelle(categorie);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");

		
		
		// Création de l'article
		var articleManager = ArticleManager.getInstance();
		Article article = new Article(nomArticle, description, debutEnchere, finEnchere, miseAPrix, utilisateur, categorieArticle);
		// ajout de l'article en BDD
		try {
			articleManager.ajouterArticle(article);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		// Le numéro de l'article tout juste inséré est modifié, on l'intègre dans le constructeur du retrait
		Retrait retrait = null;
		try {
			retrait = new Retrait(article, rue, codePostal, ville);
			var retraitManager = RetraitManager.getInstance();
			retraitManager.ajouterRetrait(retrait);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		// ajout du retrait dans l'article
		article.setLieuRetrait(retrait);
		// insert enchere
		
		try {
			Enchere enchere = new Enchere(utilisateur, article, finEnchere);
			var enchereManager = EnchereManager.getInstance();
			enchereManager.ajouterEnchere(enchere);
			
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}

		response.sendRedirect(request.getContextPath()+"/enchere");
		

		
		
	}
}
