package it.univr.Tools;

import java.sql.Date;
import java.util.ArrayList;

public class Utente {
	
	private String email;
	private String nome;
	private String cognome;
	private String indirizzo;
	private int latitudine;
	private int longitudine;
	private String stato;
	private String password;
	private String descrizione;
	private ArrayList<Libro> listaLibriUtente;
	
	public Utente(){
		this.nome = null;
		this.cognome = null;
		this.indirizzo =null;
		this.email = null;
		this.password = null;
		this.latitudine = 0;
		this.longitudine = 0;
		this.stato = null;
		this.descrizione = null;
		this.listaLibriUtente = null;
	}
	
	
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


	public void setEmail(String email) {
		this.email = email;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public void setLatitudine(int latitudine) {
		this.latitudine = latitudine;
	}


	public void setLongitudine(int longitudine) {
		this.longitudine = longitudine;
	}


	public void setStato(String stato) {
		this.stato = stato;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<Libro> getListaLibriUtente() {
		return listaLibriUtente;
	}


	public void setListaLibriUtente(ArrayList<Libro> listaLibriUtente) {
		this.listaLibriUtente = listaLibriUtente;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	
	
	

}
