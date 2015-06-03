package it.univr.Tools;



import it.univr.Database.DataSource;
import it.univr.Tools.*;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Simulatore {

	public static String appendString(String nomeStringa, String messaggio){
		return nomeStringa + "\n" + messaggio ;
	}

	public static void main(String[] args) throws FileNotFoundException, java.text.ParseException, ClassNotFoundException {
		
		String nomeFile = "ListaNomi.txt";
		
		Tools.creaFile(nomeFile);
		Tools.clearFile(nomeFile);
	

		int nUtentiDaCreare = 50; //max 200
		int maxLibriPerUtente = 15;

		System.out.println("-----------------------------------------------------------");
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		ArrayList<String> listaNomi = new ArrayList<String>();
		ArrayList<String> listaIndirizzi = new ArrayList<String>();
		ArrayList<String> listaCoordinate = new ArrayList<String>();
		
		while(listaNomi.size()<nUtentiDaCreare){
			String nome = 	GestioneNomi.getNome();
			String cognome = GestioneNomi.getCognome();
			String mail = GestioneNomi.getMail(nome, cognome);
			String pwd = GestioneNomi.getPassword();
			String indirizzo = GestioneNomi.getIndirizzo();
			int latitudine = GeoTools.getLatitudine();
			int longitudine = GeoTools.getLongitudine();
			String testCoordinate = latitudine+"-"+longitudine;
	
			
			
			while(listaNomi.contains(mail)){
				nome = 	GestioneNomi.getNome();
				cognome = GestioneNomi.getCognome();
				mail = GestioneNomi.getMail(nome, cognome);
				
			}
			listaNomi.add(mail);
			
			
			while(listaIndirizzi.contains(indirizzo)){
				indirizzo = GestioneNomi.getIndirizzo();
				
			}
			listaIndirizzi.add(indirizzo);
			
			
			while(listaCoordinate.contains(testCoordinate)){
				 latitudine = GeoTools.getLatitudine();
				 longitudine = GeoTools.getLongitudine();
				 testCoordinate = latitudine+"-"+longitudine;
				
			}
			listaCoordinate.add(testCoordinate);
			
			

			
			listaUtenti.add(new Utente(nome,cognome,indirizzo,mail,pwd,latitudine,longitudine,"A"));
			

		}

		String msg ="";
		


		for(int i =0;i<listaUtenti.size();i++){
	


				msg = appendString(msg, "");
			System.out.println("Utente ["+(i+1)+"] :");
				msg = appendString(msg, "Utente ["+(i+1)+"] :");
			System.out.println("Nome............: "+listaUtenti.get(i).getNome());
				msg = appendString(msg, "Nome............: "+listaUtenti.get(i).getNome());
			System.out.println("Cognome.........: "+listaUtenti.get(i).getCognome());
				msg = appendString(msg, "Cognome.........: "+listaUtenti.get(i).getCognome());
			System.out.println("Indirizzo.......: "+listaUtenti.get(i).getIndirizzo());
				msg = appendString(msg, "Indirizzo........: "+listaUtenti.get(i).getIndirizzo());
			System.out.println("Mail............: "+listaUtenti.get(i).getEmail());
				msg = appendString(msg, "Mail............: "+listaUtenti.get(i).getEmail());
			System.out.println("Password........: "+listaUtenti.get(i).getPassword());
				msg = appendString(msg, "Password........: "+listaUtenti.get(i).getPassword());
			System.out.println("Latitudine......: "+listaUtenti.get(i).getLatitudine());
				msg = appendString(msg, "Latitudine......: "+listaUtenti.get(i).getLatitudine());
			System.out.println("Longitudine.....: "+listaUtenti.get(i).getLongitudine());
				msg = appendString(msg, "Longitudine.....: "+listaUtenti.get(i).getLongitudine());
			System.out.println("Stato...........: "+listaUtenti.get(i).getStato());
				msg = appendString(msg, "Stato...........: "+listaUtenti.get(i).getStato());
			
			
			


		}

		System.out.println("dimensioni dell'array utente: "+listaUtenti.size());
		
		Tools.scriviFile(nomeFile, msg);

		/* Parte di inserimento nel database  */
		DataSource ds =  new DataSource();
		Connection con = ds.getConnection();
		java.sql.Statement stm;

		System.out.println("Connessione: "+con);

		/* Inserisco utenti */
		try {
			stm = con.createStatement();

			PreparedStatement pstm = con.prepareStatement(MyQuery.qInsertUtente);
			
			System.out.println("Cancellazione tabella possessolibro: " + !stm.execute(" delete from ing_possessolibro "));
			System.out.println("Cancellazione tabella libro: " + !stm.execute(" delete from ing_utente "));
		
			
//			System.out.println("Cancellazione tabella iscrizioni: " + !stm.execute(" truncate table iscrizione "));
//			System.out.println("Cancellazione tabella studenti: " + !stm.execute("delete from studente"));

			
			//(nome,cognome,indirizzo,email,password,latitudine,longitudine,stato)
			for(int i = 0;i<listaUtenti.size();i++){
				
				pstm.setString(1, listaUtenti.get(i).getNome());
				pstm.setString(2, listaUtenti.get(i).getCognome());
				pstm.setString(3, listaUtenti.get(i).getIndirizzo()); 
				pstm.setString(4, listaUtenti.get(i).getEmail());
				pstm.setString(5, listaUtenti.get(i).getPassword());
				pstm.setInt(6, listaUtenti.get(i).getLatitudine());
				pstm.setInt(7, listaUtenti.get(i).getLongitudine());
				pstm.setString(8, listaUtenti.get(i).getStato());
				System.out.println("Inserimento utente # "+(i)+": "+listaUtenti.get(i).getNome()+" "+listaUtenti.get(i).getCognome()+" -> esito = "+!pstm.execute());
				pstm.clearParameters();
			}
			pstm.close();
			
			/* fine di inserimento utenti */
			
		/* Scrivo le relazione di possesso libri */
		
			
			
			PreparedStatement pstmPossessoLibro = con.prepareStatement(MyQuery.qPossessoLibero);
			ArrayList<Integer> listaIdLibro = new ArrayList<Integer>();
			
			int n_libri = 0;
			ResultSet rs_corsi = stm.executeQuery(MyQuery.qCountLibri);
			if(rs_corsi.next())
				n_libri = rs_corsi.getInt(1);
			System.out.println("N record nella tabella libro: "+n_libri);
			
			for(int i = 0;i<listaUtenti.size();i++){
				
				double randNVolte = Math.random();
				int id_nVolte = (int) (randNVolte * maxLibriPerUtente)+1;
				listaIdLibro.clear();
				listaIdLibro.add(0);
				
				double randIDLibro = Math.random();
				int id_libro= (int) (randIDLibro * n_libri)+1; 
				
				for(int n=0;n<id_nVolte;n++){
					
					boolean verificatore = true;
					
					while(verificatore){
					if(listaIdLibro.contains(id_libro)){
//						System.out.println("l'id : "+id_libro+" e' contenuto nell'array, ne genero un altro");
						randIDLibro = Math.random();
						id_libro = (int) (randIDLibro * n_libri)+1;
//						System.out.println("Ho generato l'id: "+id_libro);
						
					}
					else {
						verificatore = false;
						listaIdLibro.add(id_libro);
//						System.out.println("Aggiungo l'id: "+id_libro+" all'array");
					}
					}
				
				
				
				
				
				
				pstmPossessoLibro.clearParameters();
				pstmPossessoLibro.setString(1, listaUtenti.get(i).getEmail());
				pstmPossessoLibro.setInt(2, id_libro);
				
				System.out.println("Creao relazione tra utente ["+(i+1)+"] : "+listaUtenti.get(i).getNome()+" "+listaUtenti.get(i).getCognome()+" e libro con id="+id_libro+" -> esito = "+!pstmPossessoLibro.execute());
				}
			}
			
			pstmPossessoLibro.close();
			pstm.close();
			
			
			
		/* fine relazione di possesso libri */
			
	
		
			
			
		
		/* iscrivo gli utenti ai corsi */
		

/*			
			int n_corsi = 0;
	
				
		PreparedStatement pstm2 = con.prepareStatement(MyQuery.qIscrizione);
		
		ArrayList<Integer> listaIdCorso = new ArrayList<Integer>();
		
		for(int i =0; i<listaUtenti.size();i++){
			
			double randNVolte = Math.random();
			
			int id_nVolte = (int) (randNVolte * maxIscrizioniConsentite)+1;
			listaIdCorso.clear();
			
			listaIdCorso.add(0);
			
			double randIDCorso = Math.random();
			int id_corso = (int) (randIDCorso * n_corsi)+1; //da 1 a 4
//			System.out.println("id corso random prima del ciclo: "+id_corso);
			
			for(int n=0;n<id_nVolte;n++){
				
				boolean verificatore = true;
				
				while(verificatore){
				if(listaIdCorso.contains(id_corso)){
//					System.out.println("l'id : "+id_corso+" e' contenuto nell'array, ne genero un altro");
					randIDCorso = Math.random();
					id_corso = (int) (randIDCorso * n_corsi)+1;
//					System.out.println("Ho generato l'id: "+id_corso);
					
				}
				else {
					verificatore = false;
					listaIdCorso.add(id_corso);
//					System.out.println("Aggiungo l'id: "+id_corso+" all'array");
				}
				}
				

				
				
				pstm2.clearParameters();
				pstm2.setInt(1,id_corso);
				pstm2.setString(2, listaUtenti.get(i).getMail());
				pstm2.setDate(3, new java.sql.Date(listaUtenti.get(i).getDn().getTime()));
				pstm2.execute();
				System.out.println("Iscrizione avvenuta per: "+listaUtenti.get(i).getNome()+" "+listaUtenti.get(i).getCognome());
				
				
			}
			
			
			
		}
		
		pstm2.close();
	
		
	
		
			
			*/
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
			try {
				
				
				con.close();
				
				System.out.println("connessione chiusa");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* fine alimentazione tabella */
		}
	}
}
