package fr.eni.ihm;
import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebFilter(
        urlPatterns = {
       
                "/detail-utilisateur",
                "/vendre",
                "/detail-vente"
              
            
               
        },
        dispatcherTypes = {
        		DispatcherType.REQUEST
        }
)
public class ConnexionFiltre  implements Filter {
	
	public void init(FilterConfig config) throws ServletException {

    }
	
	public void destroy() {
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
     
       
        if (isUserLoggedIn(httpRequest)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/se-connecter"); // Redirige vers la page d'accueil
        }
    }

	public static boolean isUserLoggedIn(HttpServletRequest request) {
		boolean isConnecte = true;
        HttpSession session = request.getSession(false);
        if (session == null	|| session.getAttribute("user")== null) {
   
        	isConnecte = false;
        } 
          return isConnecte;
    }
}