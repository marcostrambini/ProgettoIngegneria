package it.univr.Tools;

public class MyQuery {

	public static String qIsertCategoria = " insert into ing_categoria (id, nome) values (?,?) ";
	
	public static String qInsertLibro= " insert into ing_libro (id, nome, autore, categoria, path_img, stato) values (?,?,?,?,?,?) ";
	
	public static String qInsertUtente = " insert into ing_utente (nome,cognome,indirizzo,email,password,latitudine,longitudine,stato) values (?,?,?,?,?,?,?,?) ";
	
	public static String qPossessoLibero = " insert into ing_possessolibro (email_utente,id_libro) values (?,?) ";
	
	public static String qDelPossessoLibero = " delete from ing_possessolibro where email_utente = ? and id_libro = ? ";
	
	public static String qCountLibri = " select count(*) from ing_libro ";
	
	static String qSelectLibriFromUtente = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id " +
										   " join ing_possessolibro p on p.id_libro = l.id join ing_utente u on u.email =p.email_utente " +
										   " where l.stato = 'A' and u.email = ? order by nome";

	public static String getqSelectLibriFromUtente() {
		return qSelectLibriFromUtente;
	}
	
	
	/**
	 * query per login
	 */
	public static String qLogin = " select * from ing_utente where stato = 'A' and email = ? and password = ? ";
	
	/**
	 * ritorna le info di tutti i libri nel db
	 */
	public static String qSelectTuttiLibri = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id where l.stato = 'A' order by nome" ;
	
	public static String qSelectTuttiLibriD = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id where l.stato = 'D' order by nome" ;
	
	public static String qSelectTuttiLibriNotUtente = " select l.*,c.nome as nome_categoria " +
														" from ing_libro l join ing_categoria c on l.categoria = c.id " + 
														" where l.nome not in (select l.nome from ing_libro l join ing_categoria c on l.categoria = c.id " + 
														" join ing_possessolibro p on p.id_libro = l.id join ing_utente u on u.email =p.email_utente " +
														" where l.stato = 'A' and u.email = ?) " +
														" order by nome ";
	
	public static String qUpdDescUtente = " update ing_utente set descrizione = ? where email = ? ";
	
	public static String qInsertNuovoLibro = " insert into ing_libro (id,nome,categoria,path_img,stato,autore) " +
											" values((select max(id)+1 from ing_libro),"
											+ "?,"
											+ "?,"
											+ "'img/libri/empty.png',"
											+ "'A',"
											+ "?)";
	
	public static String qSelIdLibro = " select id from ing_libro where nome = ?";
	
	public static String qSelCategorie = " select nome from ing_categoria order by 1";
	
	public static String qSelUtenti = " select * from ing_utente where email <> ?";
	
	public static String qSelUtentiLibro = " select u.* from ing_utente u join ing_possessolibro p "
			  							 + " on u.email = p.email_utente join ing_libro l "
			  							 + " on p.id_libro = l.id "
			  							 + " where p.id_libro = ? and u.email <> ?";
	
	public static String qSelUtente = " select * from ing_utente where email = ? ";
	
	public static String qSelPrestitiPending = " select p.id,p.email_utente_mittente,p.email_utente_destinatario,p.id_libro,p.data_i,p.data_f,p.stato,l.nome as nome_libro "
											 + " from ing_prestito p join ing_libro l on p.id_libro = l.id "
											 + " where p.email_utente_destinatario = ? and p.stato = 'R'";
	
	public static String qSelPrestitiPendingMie = " select p.id,p.email_utente_mittente,p.email_utente_destinatario,p.id_libro,p.data_i,p.data_f,p.stato,l.nome as nome_libro "
											    + " from ing_prestito p join ing_libro l on p.id_libro = l.id "
											    + " where p.email_utente_mittente = ? and p.stato = 'R'";
	
	public static String qSelPrestitiIN =  " select p.id,p.email_utente_mittente,p.email_utente_destinatario,p.id_libro,p.data_i,p.data_f,p.stato,l.nome as nome_libro "
										 + " from ing_prestito p join ing_libro l on p.id_libro = l.id "
										 + " where p.email_utente_destinatario = ? and p.stato = 'A'";
	
	public static String qSelPrestitiOUT = " select p.id,p.email_utente_mittente,p.email_utente_destinatario,p.id_libro,p.data_i,p.data_f,p.stato,l.nome as nome_libro "
			 							 + " from ing_prestito p join ing_libro l on p.id_libro = l.id "		
			 							 + " where p.email_utente_mittente = ? and p.stato = 'A'";
	
	public static String qInsertRichiestaPrestito = " insert into ing_prestito (id, email_utente_mittente, email_utente_destinatario, id_libro, data_i, data_f, stato) values " +
												   " ((select max(id)+1 from ing_prestito), ?, ?, ?, ?,?,'R')";
	
	public static String qUpdPossessoOK = " update ing_prestito set stato = 'A' ,data_i = ?"
										+ " where email_utente_mittente = ? and "
										+ " email_utente_destinatario = ? "
										+ " and id_libro = ?";
	
	public static String qUpdPossessoNO = " update ing_prestito set stato = 'D', data_f = ? "
										+ " where email_utente_mittente = ? and "
										+ " email_utente_destinatario = ? "
										+ " and id_libro = ?";
	
	public static String qDisableUser = " update ing_utente set stato = 'D' where email = ?";
	
	public static String qEnableUser = " update ing_utente set stato = 'A' where email = ?";
	
	public static String qDisableBook = " update ing_libro set stato = 'D' where id = ?";
	
	public static String qEnableBook = " update ing_libro set stato = 'A' where id = ?";
	
	public static String getqSelectTuttiLibri() {
		return qSelectTuttiLibri;
	}


	public static String getqSelectTuttiLibriNotUtente() {
		return qSelectTuttiLibriNotUtente;
	}
	
	
}
