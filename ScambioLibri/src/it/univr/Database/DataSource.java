package it.univr.Database;

import it.univr.Tools.Libro;
import it.univr.Tools.MyQuery;
import it.univr.Tools.Utente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

  // -- definizione delle query ------------------------------------------------

  // recupera le principali info su tutti i corsi di studi
//  private String css =
//    "SELECT id, Codice, Nome FROM corsostudi ORDER BY Nome";

  // recupera tutte le informazioni di un particolare corso di studi
//  private String cs =
//    "SELECT id,nome,codice,abbreviazione,durataanni,sede,informativa "
//    + "FROM corsostudi "
//    + "WHERE id=?";

  // recupera la/e facolta' di un particolare corso di studi
//  private String csf =
//    "SELECT DISTINCT f.nome "
//    + "FROM facolta f INNER JOIN corsoinfacolta csf "
//    + "ON (f.id=csf.id_facolta) "
//    + "WHERE csf.id_corsostudi=?";

  //recupera il nome, codice, nome preside delle facolta
//  private String q2 = 
//		  "select f.nome as nomef, f.codice, f.indirizzo,p.nome as nomep,p.cognome as cognomep "
//		+ "from facolta f join persona p "
//        + "on p.id = f.id_preside_persona";
//
//  private String dettaglioPreside = 
//		  "select p.nome as nomep,p.cognome as cognomep "
//		  +"from facolta f join persona p "
//		  +"on p.id = f.id_preside_persona "
//		  +"where f.nome = ?";
  
  
  // === Methods ===============================================================

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

  /**
   * Il metodo costruisce un bean a partire dal record attuale del ResultSet
   * passato come parametro.
   *
   * @param rs
   * @return
   * @throws SQLException
   */
//  private CorsoStudi makeCorsoStudiBean( ResultSet rs ) throws SQLException {
//    CorsoStudi bean = new CorsoStudi();
//    bean.setId( rs.getInt( "id" ) );
//    bean.setNomeCorsoStudi( rs.getString( "Nome" ) );
//    bean.setCodice( rs.getString( "Codice" ) );
//    bean.setAbbreviazione( rs.getString( "Abbreviazione" ) );
//    bean.setDurataAnni( rs.getInt( "Durataanni" ) );
//    bean.setSede( rs.getString( "Sede" ) );
//    bean.setInformativa( rs.getString( "Informativa" ) );
//    return bean;
//  }

  /**
   * Il metodo costruisce un bean a partire dal record attuale del ResultSet
   * passato come parametro.
   *
   * @param rs
   * @return
   * @throws SQLException
   */
//  private CorsoStudi makeCSBean( ResultSet rs ) throws SQLException {
//    CorsoStudi bean = new CorsoStudi();
//    bean.setId( rs.getInt( "id" ) );
//    bean.setNomeCorsoStudi( rs.getString( "Nome" ) );
//    bean.setCodice( rs.getString( "Codice" ) );
//    return bean;
//  }
//  
//
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

  private Libro makeLibroBean( ResultSet rs ) throws SQLException {
	  Libro bean = new Libro();
	  bean.setId(rs.getInt("id"));
	  bean.setNome(rs.getString("nome"));
	  bean.setPath_img(rs.getString("path_img"));
	  bean.setAutore(rs.getString("autore"));
	  bean.setCategoria(rs.getString("nome_categoria"));
	  
      return bean;
  }
  
  
  // ===========================================================================

  /**
   * Metodo per il recupero delle informazioni del corso di studi con l'id
   * specificato.
   *
   * @param id
   * @return
   */
//  public CorsoStudi getCorsoStudi( int id ) {
//    // Dichiarazione delle variabili necessarie
//    Connection con = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    CorsoStudi result = null;
//    try {
//      // tentativo di connessione al database
//      con = DriverManager.getConnection( url, user, passwd );
//      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
//      pstmt = con.prepareStatement( cs );
//      pstmt.clearParameters();
//      // imposto i parametri della query
//      pstmt.setInt( 1, id );
//      // eseguo la query
//      rs = pstmt.executeQuery();
//      // memorizzo il risultato dell'interrogazione in Vector di Bean
//      if( rs.next() ) {
//        result = makeCorsoStudiBean( rs );
//      }
//
//    } catch( SQLException sqle ) { // Catturo le eventuali eccezioni
//      sqle.printStackTrace();
//
//    } finally { // alla fine chiudo la connessione.
//      try {
//        con.close();
//      } catch( SQLException sqle1 ) {
//        sqle1.printStackTrace();
//      }
//    }
//    return result;
//  }


  /**
   * Metodo per il recupero delle principali informazioni di tutti i corsi di
   * studi
   *
   * @return
   */
//  public List<CorsoStudi> getCorsiStudi() {
//    // dichiarazione delle variabili
//    Connection con = null;
//    Statement stmt = null;
//    ResultSet rs = null;
//    List<CorsoStudi> result = new ArrayList<CorsoStudi>();
//
//    try {
//      // tentativo di connessione al database
//      con = DriverManager.getConnection( url, user, passwd );
//      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
//      stmt = con.createStatement();
//      // eseguo l'interrogazione desiderata
//      rs = stmt.executeQuery( css );
//      // memorizzo il risultato dell'interrogazione nel Vector
//      while( rs.next() ) {
//        result.add( makeCSBean( rs ) );
//      }
//
//    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//      sqle.printStackTrace();
//
//    } finally { // alla fine chiudo la connessione.
//      try {
//        con.close();
//      } catch( SQLException sqle1 ) {
//        sqle1.printStackTrace();
//      }
//    }
//    return result;
//  }

  /**
   * Metodo per il recupero della/e facolta' di appartenenza del corso di studi
   * con l'id specificato.
   *
   * @param id
   * @return
   */
//  public String getFacoltaCorso( int id ) {
//    // dichiarazione delle variabili
//    Connection con = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    String result = null;
//
//    try {
//      // tentativo di connessione al database
//      con = DriverManager.getConnection( url, user, passwd );
//      // Connessione riuscita, ottengo l'oggetto per l'esecuzione
//      // dell'interrogazione.
//      pstmt = con.prepareStatement( csf );
//      pstmt.clearParameters();
//      pstmt.setInt( 1, id );
//      rs = pstmt.executeQuery();
//
//      // memorizzo il risultato dell'interrogazione nel Bean
//      if( rs.next() ) {
//        result = rs.getString( "Nome" );
//      }
//
//    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//      sqle.printStackTrace();
//
//    } finally { // alla fine chiudo la connessione.
//      try {
//        con.close();
//      } catch( SQLException sqle1 ) {
//        sqle1.printStackTrace();
//      }
//    }
//    return result;
//  }
//  
//  public List<PresideFacolta> getPresideFacolta() {
//	    // dichiarazione delle variabili
//	    Connection con = null;
//	    Statement stmt = null;
//	    ResultSet rs = null;
//	    List<PresideFacolta> result = new ArrayList<PresideFacolta>();
//
//	    try {
//	      // tentativo di connessione al database
//	      con = DriverManager.getConnection( url, user, passwd );
//	      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
//	      stmt = con.createStatement();
//	      // eseguo l'interrogazione desiderata
//	      rs = stmt.executeQuery( q2 );
//	      // memorizzo il risultato dell'interrogazione nel Vector
//	      while( rs.next() ) {
//	        result.add( makePFBean( rs ) );
//	      }
//
//	    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//	      sqle.printStackTrace();
//
//	    } finally { // alla fine chiudo la connessione.
//	      try {
//	        con.close();
//	      } catch( SQLException sqle1 ) {
//	        sqle1.printStackTrace();
//	      }
//	    }
//	    return result;
//	  }
//  
//  public String getDettaglioPreside( String nomef ) {
//	    // dichiarazione delle variabili
//	    Connection con = null;
//	    PreparedStatement pstmt = null;
//	    ResultSet rs = null;
//	    String result = null;
//
//	    try {
//	      // tentativo di connessione al database
//	      con = DriverManager.getConnection( url, user, passwd );
//	      // Connessione riuscita, ottengo l'oggetto per l'esecuzione
//	      // dell'interrogazione.
//	      pstmt = con.prepareStatement( dettaglioPreside );
//	      pstmt.clearParameters();
//	      pstmt.setString( 1, nomef );
//	      rs = pstmt.executeQuery();
//
//	      // memorizzo il risultato dell'interrogazione nel Bean
//	      if( rs.next() ) {
//	        result = rs.getString( "nomep" ) + " "+rs.getString("cognomep");
//	      }
//
//	    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//	      sqle.printStackTrace();
//
//	    } finally { // alla fine chiudo la connessione.
//	      try {
//	        con.close();
//	      } catch( SQLException sqle1 ) {
//	        sqle1.printStackTrace();
//	      }
//	    }
//	    return result;
//	  }
  
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
