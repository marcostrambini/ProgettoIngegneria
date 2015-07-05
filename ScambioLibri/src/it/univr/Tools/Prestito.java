package it.univr.Tools;

import java.sql.Date;

public class Prestito {
	
	private int id;
	private String email_utente_mittente;
	private String email_utente_destinatario;
	private int idLibro;
	private Date data_i;
	private Date data_f;
	private String stato;
	private String nome_libro;
	
	
	public Prestito(){
		this.id=0;
		this.email_utente_mittente=null;
		this.email_utente_destinatario=null;
		this.idLibro=0;
		this.data_i=null;
		this.data_f=null;
		this.stato=null;
		this.nome_libro=null;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail_utente_mittente() {
		return email_utente_mittente;
	}


	public void setEmail_utente_mittente(String email_utente_mittente) {
		this.email_utente_mittente = email_utente_mittente;
	}


	public String getEmail_utente_destinatario() {
		return email_utente_destinatario;
	}


	public void setEmail_utente_destinatario(String email_utente_destinatario) {
		this.email_utente_destinatario = email_utente_destinatario;
	}


	public int getIdLibro() {
		return idLibro;
	}


	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}


	public Date getData_i() {
		return data_i;
	}


	public void setData_i(Date data_i) {
		this.data_i = data_i;
	}


	public Date getData_f() {
		return data_f;
	}


	public void setData_f(Date data_f) {
		this.data_f = data_f;
	}


	public String getStato() {
		return stato;
	}


	public void setStato(String stato) {
		this.stato = stato;
	}


	public String getNome_libro() {
		return nome_libro;
	}


	public void setNome_libro(String nome_libro) {
		this.nome_libro = nome_libro;
	}

}
