package fr.eni.ihm;

import java.io.IOException;
import java.util.List;

import fr.eni.bll.EnchereManager;
import fr.eni.bo.Enchere;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import java.io.IOException;
import java.util.List;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.CategorieManager;
import fr.eni.bo.Article;
import fr.eni.bo.Categorie;
=======
>>>>>>> 8aa9f7d46ee74a5066daa48b7a7ee6e5d69f82da

@WebServlet("/encheres")
public class EncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EnchereManager enchereManager = EnchereManager.getInstance();
		
		List<Enchere> encheres = enchereManager.tousLesArticles();
		request.setAttribute("encheres", encheres);
		
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> categories = categorieManager.selectionnerToutesLesCategories();
		request.setAttribute("categories", categories);
		
		
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
		
		
	}

	
	
	

}
