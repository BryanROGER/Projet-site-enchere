package fr.eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		
		
		
		try {
			Enchere enchere;
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int prixEnchère = Integer.parseInt(request.getParameter("prix_enchere"));
		var utilisateurEncherisseur = (Utilisateur)request.getSession().getAttribute("user");
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
		
		
		try {
			enchere.setUtilisateur(utilisateurEncherisseur);
			enchere.setMontantEnchere(prixEnchère);
			enchereManager.updateEnchere(enchere);
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/encheres");
		
		
	}

}
