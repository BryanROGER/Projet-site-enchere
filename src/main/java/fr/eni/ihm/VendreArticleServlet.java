package fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.eni.bll.CategorieManager;
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
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> categories = categorieManager.selectionnerToutesLesCategories();
		request.setAttribute("categories", categories);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/vendreArticle.jsp");
				dispatcher.forward(request, response);
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
		Categorie categorieArticle = categorieManager.categorieByLibelle(categorie);
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");

		
		
		// Création de l'article
		Article article = new Article(nomArticle, description, debutEnchere, finEnchere, miseAPrix, utilisateur, categorieArticle);
		// ajout de l'article en BDD
		
		//récupération de l'article tour juste inséré
		
		// récupération du numero du nouvel article pour insérer le retrait en bdd
		Retrait retrait = new Retrait(rue, codePostal, ville);
		// récupé du retrait
		
		// article set retrait avec nouveau retrait
		
		// insert enchere
		Enchere enchere = new Enchere(utilisateur, article, finEnchere, miseAPrix);
		
	}
}
