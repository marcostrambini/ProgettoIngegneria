package it.univr.Servlet;

import it.univr.Database.DataSource;
import it.univr.Tools.Utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		System.out.println("Sono nel metodo get");
		String act = request.getParameter("act");
		int idLibro = Integer.parseInt(request.getParameter("id"));
		Utente utente = (Utente) session.getAttribute("utente");
		
		switch(act){
		
		case "al": System.out.println("caso di aggiunta libro ad utente");
			try {
				DataSource ds = new DataSource();
				System.out.println("email: " +utente.getEmail());
				System.out.println("id libro: "+idLibro);
				ds.setUserBook(utente.getEmail(), idLibro );
				utente.setListaLibriUtente(ds.getListaLibriUtente(utente.getEmail()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case "dl": System.out.println("caso di delete libro da utente");
		try {
			DataSource ds = new DataSource();
			System.out.println("email: " +utente.getEmail());
			System.out.println("id libro: "+idLibro);
			ds.delUserBook(utente.getEmail(), idLibro );
			utente.setListaLibriUtente(ds.getListaLibriUtente(utente.getEmail()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
		
		
		
		
		}
		
		
		
		
		String test = request.getParameter("id");
		System.out.println("id ="+test);
		
		response.sendRedirect("userPagePrivate.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel metono doPost");
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		String act = request.getParameter("act");
		
		switch(act){
		
		case "md":
			
			String desc = request.getParameter("desc");
			System.out.println("descrizione arrivata: "+desc);
			try {
				
				DataSource ds = new DataSource();
				ds.updDescrizioneUtente(desc, utente.getEmail());
				utente.setDescrizione(desc);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "anl":
			String nomeLibro = request.getParameter("nomeLibro");
			String autoreLibro = request.getParameter("autoreLibro");
			String categoria = request.getParameter("categoria");
			System.out.println("titolo libro: "+nomeLibro);
			System.out.println("autore libro: "+autoreLibro);
			System.out.println("categoria libro: "+categoria);
			try {
				
				DataSource ds = new DataSource();
				ds.addLibroNewUtente(nomeLibro, autoreLibro, categoria, utente.getEmail());
				utente.setListaLibriUtente(ds.getListaLibriUtente(utente.getEmail()));
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("userPagePrivate.jsp");
			
		break;
		}
		
		
		
	}

}
