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
import fr.eni.bo.Utilisateur;

@WebServlet("/detail-vente")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		var articleManager = ArticleManager.getInstance();
		var article = articleManager.rechercherUnArticle(noArticle);
		
		
		var enchereManager = EnchereManager.getInstance();
		var enchere = enchereManager.enchereParArticle(article);
		request.setAttribute("enchere", enchere);
		
		
		request.getRequestDispatcher("/WEB-INF/pages/detail-vente.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int prixEnchère = Integer.parseInt(request.getParameter("prix_enchere"));
		var utilisateurEncherisseur = (Utilisateur)request.getSession().getAttribute("user");
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		var articleManager = ArticleManager.getInstance();
		var article = articleManager.rechercherUnArticle(noArticle);
		
		
		var enchereManager = EnchereManager.getInstance();
		var enchere = enchereManager.enchereParArticle(article);
		
		enchere.setUtilisateur(utilisateurEncherisseur);
		enchere.setMontantEnchere(prixEnchère);
		enchereManager.updateEnchere(enchere);
		
		response.sendRedirect(request.getContextPath()+"/encheres");
		
		
	}

}
