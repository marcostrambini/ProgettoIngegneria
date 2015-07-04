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
	public static String qSelectTuttiLibri = " select l.*,c.nome as nome_categoria from ing_libro l join ing_categoria c on l.categoria = c.id order by nome" ;
	
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
			  
	
	public static String getqSelectTuttiLibri() {
		return qSelectTuttiLibri;
	}


	public static String getqSelectTuttiLibriNotUtente() {
		return qSelectTuttiLibriNotUtente;
	}
	
	
}
