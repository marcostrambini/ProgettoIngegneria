package it.univr.Tools;

import it.univr.Database.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws IOException {
		Tools tools = new Tools();
		String nomeFile = "libri_completo.txt";
		String nomeFileCategorie = "categoria.txt";
		ArrayList<String> arrayLibri = new ArrayList<String>();
		ArrayList<String> arrayCategorie = tools.leggiFileRitorna(nomeFileCategorie);
		
		Connection con = null;
		
		try {
			DataSource ds = new DataSource();
			con = ds.getConnection();
			PreparedStatement pstm = con.prepareStatement(MyQuery.qIsertCategoria);
			Statement stm = con.createStatement();
			
			System.out.println("Delete tabella possessolibro: "+stm.executeUpdate(" delete from ing_possessolibro"));
			System.out.println("Delete tabella libri: "+stm.executeUpdate(" delete from ing_libro"));
			System.out.println("Delete tabella categoraia: "+stm.executeUpdate("delete from ing_categoria"));
						
			for(int i =0; i<arrayCategorie.size();i++){
				pstm.clearParameters();
				pstm.setInt(1, (i+1));
				pstm.setString(2, arrayCategorie.get(i).trim());
				pstm.execute();
				System.out.println("inserita categoria: "+arrayCategorie.get(i));
				
				
			}
			
			pstm.close();
			
			
			PreparedStatement pstm2 = con.prepareStatement(MyQuery.qInsertLibro);
			
            arrayLibri =  tools.leggiFileRitorna(nomeFile);
			
			
			for(int i=0; i<arrayLibri.size();i++){
			String string = arrayLibri.get(i);
			String[] parts = string.split("-");
			String part1 = parts[0].trim(); // 004
			String part2 = parts[1].trim(); // 034556
			String part3 = parts[2].trim();
//			String part2 = "parte2"; // 034556
			System.out.println((i+1)+") Nome: "+part1+" [autore:  "+part2+"]");
			
//			double randIDCorso = Math.random();
//			int id_cat = (int) (randIDCorso * 29)+1;
			
			String pathImg = "img/libri/"+(i+1)+".jpg";
			
			pstm2.clearParameters();
			pstm2.setInt(1, (i+1));
			pstm2.setString(2, part1.trim());
			pstm2.setString(3, part2.trim());
			pstm2.setInt(4, Integer.parseInt(part3));
			pstm2.setString(5, pathImg);
			pstm2.setString(6, "A");
			
			System.out.println("Inserimento libor: "+part1+" : "+ !	pstm2.execute());
			
						
			}
			
					
			pstm2.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				con.close();
				System.out.println("Connesione chiusa");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("Dimensione array indirizzi: "+GestioneNomi.indirizzi.length);
		for(int i=0;i<10;i++)
			System.out.println("indirizzo a caso n "+(i+1)+" : "+GestioneNomi.getIndirizzo());
		
		
		
	}

}
