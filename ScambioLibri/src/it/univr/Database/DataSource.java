package it.univr.Database;

import it.univr.Entity.Libro;
import it.univr.Entity.Prestito;
import it.univr.Entity.Utente;
import it.univr.Tools.GeoTools;
import it.univr.Tools.Tools;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DataSource implements Serializable {

  // === Properties ============================================================

  // dati di identificazione dell'utente (da personalizzare)
  private String user = "userlab90";
  private String passwd = "novantaZX";

  // URL per la connessione alla base di dati e' formato dai seguenti
  // componenti: <protocollo>://<host del server>/<nome base di dati>.
  private String url = "jdbc:postgresql://dbserver.scienze.univr.it/dblab90";

  // Driver da utilizzare per la connessione e l'esecuzione delle query.
  private String driver = "org.postgresql.Driver";

  /**
   * Costruttore della classe. Carica i driver da utilizzare per la connessione
   * alla base di dati.
   *
   * @throws ClassNotFoundException Eccezione generata nel caso in cui i driver
   * per la connessione non siano trovati nel CLASSPATH.
   */
  public DataSource() throws ClassNotFoundException {
    Class.forName( driver );
  }

  private Utente makeUserBean( ResultSet rs ) throws SQLException {
	  Utente bean = new Utente();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setEmail(rs.getString("email"));
	  bean.setIndirizzo(rs.getString("indirizzo"));
	  bean.setLatitudine(rs.getInt("latitudine"));
	  bean.setLongitudine(rs.getInt("longitudine"));
	  bean.setDescrizione(rs.getString("descrizione"));
	  
      return bean;
  }
  
  private Utente makeUserBeanDistanza( ResultSet rs , int latitudine, int longitudine) throws SQLException {
	  Utente bean = new Utente();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setEmail(rs.getString("email"));
	  bean.setIndirizzo(rs.getString("indirizzo"));
	  bean.setLatitudine(rs.getInt("latitudine"));
	  bean.setLongitudine(rs.getInt("longitudine"));
	  bean.setDistanza(GeoTools.getDistance(latitudine, longitudine, bean.getLatitudine(), bean.getLongitudine()));
	  
      return bean;
  }

  /**
   * contruttore di bean Libro
   * @param rs
   * @return
   * @throws SQLException
   */
  private Libro makeLibroBean( ResultSet rs ) throws SQLException {
	  Libro bean = new Libro();
	  bean.setId(rs.getInt("id"));
	  bean.setNome(rs.getString("nome"));
	  bean.setPath_img(rs.getString("path_img"));
	  bean.setAutore(rs.getString("autore"));
	  bean.setCategoria(rs.getString("nome_categoria"));
	  
      return bean;
  }
  
  /**
   * contruttore di bean Prestito
   * @param rs
   * @return
   * @throws SQLException
   */
  private Prestito makePrestitoBean( ResultSet rs ) throws SQLException {
	  Prestito bean = new Prestito();
	  bean.setId(rs.getInt("id"));
	  bean.setEmail_utente_mittente(rs.getString("email_utente_mittente"));
	  bean.setEmail_utente_destinatario(rs.getString("email_utente_destinatario"));
	  bean.setIdLibro(rs.getInt("id_libro"));
	  bean.setData_i(rs.getDate("data_i"));
	  bean.setData_f(rs.getDate("data_f"));
	  bean.setStato(rs.getString("stato"));
	  bean.setNome_libro(rs.getString("nome_libro"));
	  
      return bean;
  }
  
  
  /**
   * ritorna la lista di tutti i libri presenti nel db
   * @return
   */
  public ArrayList<Libro> getListaLibri(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Libro> result = new ArrayList<Libro>();
	  
	  con = getConnection();
	  
	  try {
		stm = con.createStatement();
		rs = stm.executeQuery(MyQuery.getqSelectTuttiLibri());
		while(rs.next())
			result.add(makeLibroBean(rs));
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  /**
   * seleziona tutti i libri disabilitati
   * @return
   */
  public ArrayList<Libro> getListaLibriD(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Libro> result = new ArrayList<Libro>();
	  
	  con = getConnection();
	  
	  try {
		stm = con.createStatement();
		rs = stm.executeQuery(MyQuery.qSelectTuttiLibriD);
		while(rs.next())
			result.add(makeLibroBean(rs));
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  /**
   * restituisce la lista dei libri che un utente non ha
   * @param email
   * @return
   */
  public ArrayList<Libro> getListaLibri(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Libro> result = new ArrayList<Libro>();
	  
	  con = getConnection();
	  
	  try {
		pstm = con.prepareStatement(MyQuery.getqSelectTuttiLibriNotUtente());
		pstm.setString(1, email);
		rs = pstm.executeQuery();
		while(rs.next())
			result.add(makeLibroBean(rs));
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  
   /**
   * ritorna la lista dei libri di un determinato utente
   * @param email
   * @return
   */
  public ArrayList<Libro> getListaLibriUtente(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Libro> result = new ArrayList<Libro>();
	  
	  con = getConnection();
	  
	  try {
		pstm = con.prepareStatement(MyQuery.getqSelectLibriFromUtente());
		pstm.setString(1, email);
		rs = pstm.executeQuery();
		while(rs.next())
			result.add(makeLibroBean(rs));
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  /**
   * lista di utenti in ordine di distanza dall'utente loggato
   * @param email
   * @param latitudine
   * @param longitudine
   * @return
   */
  public ArrayList<Utente> getListaUtentiVicini(String email, int latitudine, int longitudine){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  
	  con = getConnection();
	  
	  try {
		
		pstm = con.prepareStatement(MyQuery.qSelUtenti);
		pstm.setString(1, email);
		rs = pstm.executeQuery();
		while(rs.next())
			result.add(makeUserBeanDistanza(rs, latitudine, longitudine));
		
		Collections.sort(result);
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  /**
   * restituisce la lista di tutti gli utenti abilitati
   * @return
   */
  public ArrayList<Utente> getListaUtentiA(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  con = getConnection();
	  try {
		stm = con.createStatement();
		rs = stm.executeQuery("select * from ing_utente where stato = 'A' order by cognome");
		while(rs.next())
			result.add(makeUserBean(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return result;
  }
  
  /**
   * restituisce la lista degli utenti disabilitati
   * @return
   */
  public ArrayList<Utente> getListaUtentiD(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  con = getConnection();
	  try {
		stm = con.createStatement();
		rs = stm.executeQuery("select * from ing_utente where stato = 'D' order by cognome");
		while(rs.next())
			result.add(makeUserBean(rs));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return result;
  }
  
  public ArrayList<Utente> getListaUtentiViciniLibroScelto(String email, int latitudine, int longitudine, int idLibro){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  
	  con = getConnection();
	  
	  try {
		
		pstm = con.prepareStatement(MyQuery.qSelUtentiLibro);
		pstm.setInt(1, idLibro);
		pstm.setString(2, email);
		pstm.setInt(3, idLibro);
		pstm.setInt(4, idLibro);
		rs = pstm.executeQuery();
		while(rs.next())
			result.add(makeUserBeanDistanza(rs, latitudine, longitudine));
		
		Collections.sort(result);
		
	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return result;
  }
  
  /**
   * metodo per verificare il login
   * @param email
   * @param password
   * @return
   */
  public Utente login(String email, String password){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  Utente result = null;
	 
	 
	  try {
		 con = getConnection();
		pstm = con.prepareStatement(MyQuery.qLogin);
		pstm.setString(1, email);
		pstm.setString(2, password);
		rs = pstm.executeQuery();
		if(rs.next()){
			result = makeUserBean(rs);
			result.setListaLibriUtente(getListaLibriUtente(email));
			System.out.println("ho recuperato l'utente: "+result.getNome()+" "+result.getCognome());
		
		}
		
		
	} catch (SQLException e) {
		System.out.println("Problemi con il recupero dello user richiesto");
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
  }
  
  /**
   * associa il possesso di un libro ad un utente
   * @param email
   * @param idLibro
   */
  public void setUserBook(String email, int idLibro){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  Boolean result = false;
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qPossessoLibero);
		pstm.setString(1, email);
		pstm.setInt(2, idLibro);
		result = !pstm.execute();
	  }catch(SQLException e){
		  e.printStackTrace();
	  }finally{
		  try {
			pstm.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	  }
	  
  }
  
  /**
   * cancello la relazione tra utente e libro posseduto
   * @param email
   * @param idLibro
   */
  public void delUserBook(String email, int idLibro){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  Boolean result = false;
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qDelPossessoLibero);
		pstm.setString(1, email);
		pstm.setInt(2, idLibro);
		result = !pstm.execute();
	  }catch(SQLException e){
		  e.printStackTrace();
	  }finally{
		  try {
			pstm.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	  }
	  
  }
  
  public void updDescrizioneUtente(String descrizione, String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  Boolean result = false;
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qUpdDescUtente);
		pstm.setString(1, descrizione);
		pstm.setString(2, email);
		result = !pstm.execute();
	  }catch(SQLException e){
		  e.printStackTrace();
	  }finally{
		  try {
			pstm.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
  }
  
  public void addLibroNewUtente(String nomeLibro, String autoreLibro, String categoria, String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  Boolean result = false;
	  int idLibro = 0;
	  int idCategoria = 0;
	  try {
		con = getConnection();
		con.setAutoCommit(false);
		stm = con.createStatement();
		rs=stm.executeQuery("select id from ing_categoria where nome = '"+categoria+"'");
		if(rs.next())
			idCategoria = rs.getInt("id");
		
		System.out.println("id della categoria "+categoria+" = "+idCategoria);
		
		pstm = con.prepareStatement(MyQuery.qInsertNuovoLibro);
		pstm.setString(1, nomeLibro);
		pstm.setInt(2, idCategoria);
		pstm.setString(3, autoreLibro);
		result = !pstm.execute();
		con.commit();
		con.setAutoCommit(true);
		
		stm = con.createStatement();
		rs=stm.executeQuery("select id from ing_libro where nome = '"+nomeLibro+"'");
		if(rs.next())
			idLibro = rs.getInt("id");
		
		setUserBook(email, idLibro);
		
		
	  }catch(SQLException e){
		  e.printStackTrace();
	  }finally{
		  try {
			pstm.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
  }
  
  public ArrayList<String> getCategorie(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<String > result = new ArrayList<String>();
	  
	  
	  try {
		con = getConnection();
		stm = con.createStatement();
		rs=stm.executeQuery(MyQuery.qSelCategorie);
		while(rs.next())
			result.add(rs.getString(1));
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
	  
	  
	  return result;
  }
  
  /**
   * ritorna le info di un utente data la mail
   * @param email
   * @return
   */
  public Utente getUtente(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  Utente result = null;
	 
	  
	  try {
		 con = getConnection();
		pstm = con.prepareStatement(MyQuery.qSelUtente);
		pstm.setString(1, email);
	
		rs = pstm.executeQuery();
		if(rs.next()){
			result = makeUserBean(rs);
			result.setListaLibriUtente(getListaLibriUtente(email));
			System.out.println("ho recuperato l'utente: "+result.getNome()+" "+result.getCognome());
		
		}
		
		
	} catch (SQLException e) {
		System.out.println("Problemi con il recupero dello user richiesto");
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
  }
  
  /**
   * restituisce una lista di prestiti in attesa per un utente
   * @param email
   * @return
   */
  public ArrayList<Prestito> getPrestitiPending(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Prestito> result = new ArrayList<Prestito>();
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qSelPrestitiPending);
		pstm.setString(1, email);
		rs = pstm.executeQuery();
		while(rs.next()){
			result.add( makePrestitoBean(rs));
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
	  
  }
  
  /**
   * richieste di prestito che ho fatto e sono ancora in sospeso
   * @param email
   * @return
   */
  public ArrayList<Prestito> getPrestitiPendingMie(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Prestito> result = new ArrayList<Prestito>();
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qSelPrestitiPendingMie);
		pstm.setString(1, email);
		rs = pstm.executeQuery();
		while(rs.next()){
			result.add( makePrestitoBean(rs));
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
	  
  }
  
  public ArrayList<Prestito> getPrestitiIN(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Prestito> result = new ArrayList<Prestito>();
	 	  
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qSelPrestitiIN);
		pstm.setString(1, email);
	
		rs = pstm.executeQuery();
		while(rs.next()){
			result.add( makePrestitoBean(rs));
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
	  
  }
  
  public ArrayList<Prestito> getPrestitiOUT(String email){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Prestito> result = new ArrayList<Prestito>();
	 	  
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qSelPrestitiOUT);
		pstm.setString(1, email);
	
		rs = pstm.executeQuery();
		while(rs.next()){
			result.add( makePrestitoBean(rs));
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
	  
  }
  
  public void insertRichiestPrestito(String email_mittente, String email_destinatario, int idLibro){
	  Connection con = null;
	  PreparedStatement pstm = null;
	 
	  try {
		con = getConnection();
		pstm = con.prepareStatement(MyQuery.qInsertRichiestaPrestito);
		pstm.setString(1, email_mittente);
		pstm.setString(2, email_destinatario);
		pstm.setInt(3, idLibro);
		pstm.setDate(4, new java.sql.Date(Tools.getDate().getTime()));
		pstm.setDate(5, null);
		pstm.execute();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }
	 
	  public void updPrestitoOK(String email_mittente, String email_destinatario, int idLibro){
		  Connection con = null;
		  PreparedStatement pstm = null;
		 
		  try {
			con = getConnection();
			pstm = con.prepareStatement(MyQuery.qUpdPossessoOK);
			pstm.setDate(1, new java.sql.Date(Tools.getDate().getTime()));
			pstm.setString(2, email_mittente);
			pstm.setString(3, email_destinatario);
			pstm.setInt(4, idLibro);
			pstm.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
  }

	  public void updPrestitoNO(String email_mittente, String email_destinatario, int idLibro){
		  Connection con = null;
		  PreparedStatement pstm = null;
		 
		  try {
			con = getConnection();
			pstm = con.prepareStatement(MyQuery.qUpdPossessoNO);
			pstm.setDate(1, new java.sql.Date(Tools.getDate().getTime()));
			pstm.setString(2, email_mittente);
			pstm.setString(3, email_destinatario);
			pstm.setInt(4, idLibro);
			pstm.execute();
			
		} catch (SQLException | ParseException e) {
			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
	}  

	  public String getNomeLibro(int idLibro){
		  Connection con = null;
		  Statement stm = null;
		  ResultSet rs = null;
		  String result = null;
		  try {
			 con = getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery("select nome from ing_libro where id = "+idLibro);
			if(rs.next()){
				result = rs.getString("nome");
			}
		
		} catch (SQLException e) {
			System.out.println("Problemi con il recupero dello user richiesto");
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  return result;
	  }
	
	  /**
	   * disabilitazione di un utente da parte dell'admin
	   * @param email
	   */
   public void disableUser(String email){
	      Connection con = null;
		  PreparedStatement pstm = null;
		  try {
			 con = getConnection();
			pstm = con.prepareStatement(MyQuery.qDisableUser);
			pstm.setString(1, email);
			pstm.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   
   /**
    * abilitazione di utente da parte dell'admin
    * @param email
    */
   public void enableUser(String email){
	      Connection con = null;
		  PreparedStatement pstm = null;
		  try {
			 con = getConnection();
			pstm = con.prepareStatement(MyQuery.qEnableUser);
			pstm.setString(1, email);
			pstm.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   
   /**
    * disabilitazione libro da parte dell'utente
    * @param idLibro
    */
   public void disableBook(int idLibro){
	      Connection con = null;
		  PreparedStatement pstm = null;
		  try {
			 con = getConnection();
			pstm = con.prepareStatement(MyQuery.qDisableBook);
			pstm.setInt(1,idLibro);
			pstm.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   
   /**
    * abilitazione libro da parte dell'admin
    * @param idLibro
    */
   public void enableBook(int idLibro){
	      Connection con = null;
		  PreparedStatement pstm = null;
		  try {
			 con = getConnection();
			pstm = con.prepareStatement(MyQuery.qEnableBook);
			pstm.setInt(1,idLibro);
			pstm.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   
   /**
    * recupera i nome dei libri con i relativi nuemri di possesso
    * @return
    */
   public ResultSet getNumeroPossessi(){
	   ResultSet rs = null;
	   Connection con = null;
	   try {
		   con = getConnection();
		   Statement stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qSelNumeroPossessi);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   return rs;
   }
   
   /**
    * restituisce il numero degli utenti nel db
    * @return
    */
   public int getNumeroUtenti(){
	   int result =0;
	   ResultSet rs = null;
	   Connection con = null;
	   Statement stm = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountUtenti);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * ritorna il numero degli utenti abilitati
    * @return
    */
   public int getNumeroUtentiA(){
	   int result =0;
	   ResultSet rs = null;
	   Connection con = null;
	   Statement stm = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountUtentiA);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * ritorna il numero degli utenti disabilitati
    * @return
    */
   public int getNumeroUtentiD(){
	   int result =0;
	   ResultSet rs = null;
	   Statement stm = null;
	   Connection con = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountUtentiD);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * ritorna il numero dei libri abilitati
    * @return
    */
   public int getNumeroLibriA(){
	   int result =0;
	   ResultSet rs = null;
	   Connection con = null;
	   Statement stm = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountLibriA);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * ritorna il numero dei libri disabilitati
    * @return
    */
   public int getNumeroLibriD(){
	   int result =0;
	   ResultSet rs = null;
	   Connection con = null;
	   Statement stm = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountLibriD);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * ritorna il numero di libri presenti nel database
    * @return
    */
   public int getNumeroLibri(){
	   int result =0;
	   ResultSet rs = null;
	   Connection con = null;
	   Statement stm = null;
	   try {
		   con = getConnection();
		   stm = con.createStatement();
		   rs = stm.executeQuery(MyQuery.qCountLibri);
		   if(rs.next())
			   result = rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return result;
   }
   
   /**
    * registrazione utente
    * @param nome
    * @param cognome
    * @param indirizzo
    * @param email
    * @param password
    * @return
    */
   public boolean registerUser(String nome, String cognome, String indirizzo, String email, String password){
	   Connection con = null;
	   PreparedStatement pstm = null;
	   boolean result = false;
	   try {
		   con = getConnection();
		   pstm = con.prepareStatement(MyQuery.qInsertUtente);
		   pstm.setString(1, nome);
		   pstm.setString(2, cognome);
		   pstm.setString(3, indirizzo); 
		   pstm.setString(4, email);
		   pstm.setString(5, password);
		   pstm.setInt(6, GeoTools.getLatitudine());
		   pstm.setInt(7, GeoTools.getLongitudine());
		   pstm.setString(8, "A");
		   pstm.execute();
		   result = true;
		   
		   
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			pstm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return result;
	   
   }
	  
  /**
   * ritorna l'oggetto connessione al DAtaSource
   * @return
   */
  public Connection getConnection(){
	  
	  try {
		return DriverManager.getConnection( url, user, passwd );
	} catch (SQLException e) {
		System.out.println("non riesco a creare la connessione");
		e.printStackTrace();
		return null;
	}
	  
  }
  
 

}
