package it.univr.Servlet;

import it.univr.Database.DataSource;
import it.univr.Tools.Libro;
import it.univr.Tools.MyQuery;
import it.univr.Tools.Utente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();

		
		DataSource ds;
		Utente utente =  null;
	
			
		 String email= request.getParameter("email");
         String password= request.getParameter("password");
         
         System.out.println(email);
         System.out.println(password);
         
         try {
 			ds = new DataSource();
 			utente  = ds.login(email, password);
 			
         } catch (ClassNotFoundException e) {
   			System.out.println("sono passato dalla cath del login");
   			e.printStackTrace();
   		}
 			
         
         
 		
         if(utente!=null){
         System.out.println("dalla servlet login vedo questo utente: "+utente.getNome()+" "+utente.getCognome());

         session.setAttribute("utente", utente);  
         session.setAttribute("userLogged", utente.getNome()+" "+utente.getCognome());
         getServletConfig().getServletContext().getRequestDispatcher("/userPagePrivate.jsp").forward(request,response);
         
         }
         else
        	 getServletConfig().getServletContext().getRequestDispatcher("/accessDenied.jsp").forward(request,response);
         
  
          
         }
         
         
         

}
