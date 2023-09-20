package fr.eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.CategorieManager;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;

@WebServlet("/encheres")
public class EncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager articleManager = ArticleManager.getInstance();
		
		List<Article> articles = articleManager.tousLesArticles();
		request.setAttribute("articles", articles);
		
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> categories = categorieManager.selectionnerToutesLesCategories();
		request.setAttribute("categories", categories);
		
		
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
		
		
	}

	
	
	

}
