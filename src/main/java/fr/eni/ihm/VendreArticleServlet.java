package fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/vendreArticle.jsp");
				dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int miseAPrix = Integer.parseInt( request.getParameter("prix"));
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debut_enchère"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("fin_enchere"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		
	}
}
