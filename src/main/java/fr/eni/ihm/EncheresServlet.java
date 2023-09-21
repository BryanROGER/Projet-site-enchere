package fr.eni.ihm;

import java.io.IOException;
import java.util.List;

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

		
		List<Enchere> encheres = enchereManager.tousLesArticles();
		request.setAttribute("encheres", encheres);
		
		
		



		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> categories = categorieManager.selectionnerToutesLesCategories();
		request.setAttribute("categories", categories);

		if (request.getParameter("categorie") != null) {
			int noCategorie = Integer.parseInt(request.getParameter("categorie"));
			List<Enchere> encheres = enchereManager.encheresParCategorie(noCategorie);
			request.setAttribute("encheres", encheres);
		}

		if (request.getParameter("string_filter") != null) {

			// recherche par String
		} else {

			List<Enchere> encheres = enchereManager.tousLesArticles();
			request.setAttribute("encheres", encheres);

		}
		request.getRequestDispatcher("/WEB-INF/pages/encheres.jsp").forward(request, response);
	}

}
