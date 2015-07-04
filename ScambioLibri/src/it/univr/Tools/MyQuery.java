package it.univr.Tools;

public class MyQuery {

	public static String qIsertCategoria = " insert into ing_categoria (id, nome) values (?,?) ";
	
	public static String qInsertLibro= " insert into ing_libro (id, nome, autore, categoria, path_img, stato) values (?,?,?,?,?,?) ";
	
	public static String qInsertUtente = " insert into ing_utente (nome,cognome,indirizzo,email,password,latitudine,longitudine,stato) values (?,?,?,?,?,?,?,?) ";
	
	public static String qPossessoLibero = " insert into ing_possessolibro (email_utente,id_libro) values (?,?) ";
	
	public static String qCountLibri = " select count(*) from ing_libro ";
	
	static String qSelectLibriFromUtente = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id " +
										   " join ing_possessolibro p on p.id_libro = l.id join ing_utente u on u.email =p.email_utente " +
										   " where l.stato = 'A' and u.email = ?";

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
	public static String qSelectTuttiLibri = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id order by nome" ;
			  

	public static String getqSelectTuttiLibri() {
		return qSelectTuttiLibri;
	}
	
}
