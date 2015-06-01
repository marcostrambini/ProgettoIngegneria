package it.univr.Tools;

public class MyQuery {

	static String qIsertCategoria = " insert into ing_categoria (id, nome) values (?,?) ";
	
	static String qInsertLibro= " insert into ing_libro (id, nome, autore, categoria, path_img, stato) values (?,?,?,?,?,?) ";
	
	static String qInsertUtente = " insert into ing_utente (nome,cognome,indirizzo,email,password,latitudine,longitudine,stato) values (?,?,?,?,?,?,?,?) ";
	
	static String qPossessoLibero = " insert into ing_possessolibro (email_utente,id_libro) values (?,?) ";
	
	static String qCountLibri = " select count(*) from ing_libro ";
	
}
