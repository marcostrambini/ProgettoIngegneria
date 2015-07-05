package it.univr.Servlet;

import it.univr.Database.DataSource;
import it.univr.Tools.Libro;
import it.univr.Tools.Utente;

import java.io.IOException;
import java.util.ArrayList;

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
		String email = null;
		int idLibro = 0;
		Utente utente = (Utente) session.getAttribute("utente");
		
		switch(act){
		
		case "al": System.out.println("caso di aggiunta libro ad utente");
			try {
				DataSource ds = new DataSource();
				idLibro = Integer.parseInt(request.getParameter("id"));
				System.out.println("email: " +utente.getEmail());
				System.out.println("id libro: "+idLibro);
				ds.setUserBook(utente.getEmail(), idLibro );
				utente.setListaLibriUtente(ds.getListaLibriUtente(utente.getEmail()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "dl": System.out.println("caso di delete libro da utente");
		try {
			DataSource ds = new DataSource();
			idLibro = Integer.parseInt(request.getParameter("id"));
			System.out.println("email: " +utente.getEmail());
			System.out.println("id libro: "+idLibro);
			ds.delUserBook(utente.getEmail(), idLibro );
			utente.setListaLibriUtente(ds.getListaLibriUtente(utente.getEmail()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "su": System.out.println("caso seleziona utente ");
		try {
			DataSource ds = new DataSource();
			email = request.getParameter("email");
			Utente utenteSelezionato = ds.getUtente(email);
			session.setAttribute("utenteSelezionato", utenteSelezionato);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		response.sendRedirect("userPagePublic.jsp");
		break;
		
		case "rp": System.out.println("caso richiesta prestito libro ");
		try {
			DataSource ds = new DataSource();
			String emailDest = request.getParameter("emailDest");
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.insertRichiestPrestito(utente.getEmail(), emailDest, idLibro);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "pok": System.out.println("caso prestito accettato ");
		try {
			DataSource ds = new DataSource();
			String emailMittente = request.getParameter("emailMittente");
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.updPrestitoOK(emailMittente, utente.getEmail(), idLibro);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "pno": System.out.println("caso prestito rifiutato ");
		try {
			DataSource ds = new DataSource();
			String emailMittente = request.getParameter("emailMittente");
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.updPrestitoNO(emailMittente, utente.getEmail(), idLibro);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "pr": System.out.println("caso prestito restituito al mittente ");
		try {
			DataSource ds = new DataSource();
			String emailMittente = request.getParameter("emailMittente");
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.updPrestitoNO(utente.getEmail(),emailMittente ,idLibro);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("userPagePrivate.jsp");
		break;
		
		case "ru": System.out.println("caso ricerca utenti che hanno un determinato libro ");
		String nomeLibro = "";
		try {
			DataSource ds = new DataSource();
			
			idLibro = Integer.parseInt(request.getParameter("id"));
			ArrayList<Utente> listaUtentiLibro = ds.getListaUtentiViciniLibroScelto(utente.getEmail(), utente.getLatitudine(), utente.getLongitudine(), idLibro);
			session.setAttribute("listaUtentiLibro", listaUtentiLibro);
			nomeLibro = ds.getNomeLibro(idLibro);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("listaUtentiLibro.jsp?nomeLibro="+nomeLibro+"&idLibro="+idLibro);
		break;
		
		case "du": System.out.println("caso disabilitazione utente da admin ");
		try {
			DataSource ds = new DataSource();
			email = request.getParameter("email");
			ds.disableUser(email);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("admin.jsp");
		break;
		
		case "au": System.out.println("caso abilitazione utente da admin ");
		try {
			DataSource ds = new DataSource();
			email = request.getParameter("email");
			ds.enableUser(email);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("admin.jsp");
		break;
		
		case "db": System.out.println("caso disabilitazione libro da admin ");
		try {
			DataSource ds = new DataSource();
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.disableBook(idLibro);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("admin.jsp");
		break;
		
		case "ab": System.out.println("caso abilitazione libro da admin ");
		try {
			DataSource ds = new DataSource();
			idLibro = Integer.parseInt(request.getParameter("idLibro"));
			ds.enableBook(idLibro);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("admin.jsp");
		break;
		
		
		}
		
		
		
		
		

		
		
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
