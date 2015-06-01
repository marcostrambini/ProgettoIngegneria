package it.univr.Tools;

import java.sql.Date;

public class Utente {
	
	private String email;
	private String nome;
	private String cognome;
	private String indirizzo;
	private int latitudine;
	private int longitudine;
	private String stato;
	private String password;
	
	public Utente(String nome, String cognome, String indirizzo, String email, String password, int latitudine, int longitudine, String stato ){
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.email = email;
		this.password = password;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.stato = stato;
	}
	
	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public int getLatitudine() {
		return latitudine;
	}

	public int getLongitudine() {
		return longitudine;
	}

	public String getStato() {
		return stato;
	}

	public String getPassword() {
		return password;
	}

	
	
	
	

}
