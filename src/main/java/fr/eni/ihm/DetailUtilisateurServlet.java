package fr.eni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.bll.UtilisateurManager;

@WebServlet("/detail-utilisateur")
public class DetailUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int id = Integer.parseInt((String) request.getParameter("id"));
		var utilisateurManager = UtilisateurManager.getInstance();
		
		var utilisateur = utilisateurManager.unUtilisateurParID(id);
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/pages/detail-utilisateur.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
