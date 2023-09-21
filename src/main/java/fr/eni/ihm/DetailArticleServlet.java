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

@WebServlet("/detail-vente")
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		var articleManager = ArticleManager.getInstance();
		var Article = articleManager.rechercherUnArticle(noArticle);
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		var utilisateurManager = UtilisateurManager.getInstance();
		try {
			var utilisateur = utilisateurManager.unUtilisateurParID(noUtilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var enchereManager = EnchereManager.getInstance();
		var enchere = enchereManager.enchereParArticleUtilisateur();
		
		request.setAttribute("enchere", enchere);
		request.getRequestDispatcher("/WEB-INF/pages/detail-vente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
