package it.univr.Entity;

public class Libro {
	
	private int id;
	private String nome;
	private String categoria;
	private String path_img;
	private String stato;
	private String autore;
	
	public Libro(){
		this.id = 0;
		this.nome = null;
		this.categoria = null;
		this.path_img = null;
		this.stato = null;
		this.autore = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

}
