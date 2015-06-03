package it.univr.Tools;

public class MyQuery {

	static String qIsertCategoria = " insert into ing_categoria (id, nome) values (?,?) ";
	
	static String qInsertLibro= " insert into ing_libro (id, nome, autore, categoria, path_img, stato) values (?,?,?,?,?,?) ";
	
	static String qInsertUtente = " insert into ing_utente (nome,cognome,indirizzo,email,password,latitudine,longitudine,stato) values (?,?,?,?,?,?,?,?) ";
	
	static String qPossessoLibero = " insert into ing_possessolibro (email_utente,id_libro) values (?,?) ";
	
	static String qCountLibri = " select count(*) from ing_libro ";
	
	static String qSelectLibriFromUtente = " select l.id,l.nome,l.path_img,l.autore,c.nome,u.nome,u.cognome,u.indirizzo,u.descrizione from ing_libro l join ing_categoria c on l.categoria = c.id " +
										   " join ing_possessolibro p on p.id_libro = l.id join ing_utente u on u.email =p.email_utente " +
										   " where l.stato = 'A' and u.email = ?";

	public static String getqSelectLibriFromUtente() {
		return qSelectLibriFromUtente;
	}
	

	
}
