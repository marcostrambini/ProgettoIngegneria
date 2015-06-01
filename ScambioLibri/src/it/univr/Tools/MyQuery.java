package it.univr.Tools;

public class MyQuery {

	static String qIsertCategoria = " insert into ing_categoria (id, nome) values (?,?) ";
	
	static String qInsertLibro= " insert into ing_libro (id, nome, autore, categoria, path_img, stato) values (?,?,?,?,?,?) ";
	
}
