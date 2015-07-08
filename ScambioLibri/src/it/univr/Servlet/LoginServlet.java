package it.univr.Servlet;


import it.univr.Database.DataSource;
import it.univr.Database.MyQuery;
import it.univr.Entity.Libro;
import it.univr.Entity.Utente;
import it.univr.Tools.GeoTools;

import java.io.IOException;
import java.sql.PreparedStatement;
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

		
		DataSource ds = null;
		Utente utente =  null;
	
		String act = request.getParameter("act");
		
		switch(act){
		case "r": System.out.println("sono nel caso registrazione utente");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String email_r = request.getParameter("email");
		String password_r = request.getParameter("password");
		
		System.out.println(nome);
		System.out.println(cognome);
		System.out.println(indirizzo);
		System.out.println(email_r);
		System.out.println(password_r);
		
		
			try {
				ds = new DataSource();
				if(ds.registerUser(nome, cognome, indirizzo, email_r, password_r))
					getServletConfig().getServletContext().getRequestDispatcher("/register.jsp?result=ok").forward(request,response);
				else
					getServletConfig().getServletContext().getRequestDispatcher("/register.jsp?result=no").forward(request,response);
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		
		break;
		
		case "l":
			 String email= request.getParameter("email");
	         String password= request.getParameter("password");
	         
	         System.out.println(email);
	         System.out.println(password);
	         
	         if(email.equals("admin") && email.equals("admin")){
	        	 utente = new Utente();
	        	 utente.setNome("Project");
	        	 utente.setCognome("Administrator");
	        	 session.setAttribute("utente", utente);  
	             session.setAttribute("userLogged", utente.getNome()+" "+utente.getCognome());
	             getServletConfig().getServletContext().getRequestDispatcher("/admin.jsp").forward(request,response);   		  
	   	  		}else{
	   	  
	         
	         try {
	 			ds = new DataSource();
	 			utente  = ds.login(email, password);
	 			
	         } catch (ClassNotFoundException e) {
	   			System.out.println("sono passato dalla cath del login");
	   			e.printStackTrace();
	   		}
	 			
	   	  }
	         
	 		
	         if(utente!=null && !utente.getNome().equals("admin")){
	         System.out.println("dalla servlet login vedo questo utente: "+utente.getNome()+" "+utente.getCognome());

	         session.setAttribute("utente", utente);  
	         session.setAttribute("userLogged", utente.getNome()+" "+utente.getCognome());
	         getServletConfig().getServletContext().getRequestDispatcher("/userPagePrivate.jsp").forward(request,response);
	         
	         }
	         else
	        	 getServletConfig().getServletContext().getRequestDispatcher("/accessDenied.jsp").forward(request,response);
	         
	  
	         break;
	         }
		
		
		}
		
			
		
         
         
         

}
