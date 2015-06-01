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
		String nomeFile = "libri.txt";
		String nomeFileCategorie = "categoria.txt";
		ArrayList<String> arrayLibri = new ArrayList<String>();
	
			
			
	

		ArrayList<String> arrayCategorie = tools.leggiFileRitorna(nomeFileCategorie);
		
		Connection con = null;
		
		try {
			DataSource ds = new DataSource();
			con = ds.getConnection();
			PreparedStatement pstm = con.prepareStatement(MyQuery.qIsertCategoria);
			Statement stm = con.createStatement();
			
			System.out.println("Delete tabella libri: "+stm.executeUpdate(" delete from ing_libro"));
			System.out.println("Delete tabella categoraia: "+stm.executeUpdate("delete from ing_categoria"));
						
			for(int i =0; i<arrayCategorie.size();i++){
				pstm.clearParameters();
				pstm.setInt(1, (i+1));
				pstm.setString(2, arrayCategorie.get(i));
				pstm.execute();
				System.out.println("inserita categoria: "+arrayCategorie.get(i));
				
				
			}
			
			pstm.close();
			
			
			PreparedStatement pstm2 = con.prepareStatement(MyQuery.qInsertLibro);
			
            arrayLibri =  tools.leggiFileRitorna(nomeFile);
			
			
			for(int i=0; i<arrayLibri.size();i++){
			String string = arrayLibri.get(i);
			String[] parts = string.split("-");
			String part1 = parts[0]; // 004
			String part2 = parts[1]; // 034556
//			String part2 = "parte2"; // 034556
			System.out.println((i+1)+") Nome: "+part1+" [autore:  "+part2+"]");
			
			double randIDCorso = Math.random();
			int id_cat = (int) (randIDCorso * 29)+1;
			
			
			
			pstm2.clearParameters();
			pstm2.setInt(1, (i+1));
			pstm2.setString(2, part1);
			pstm2.setString(3, part2);
			pstm2.setInt(4, id_cat);
			pstm2.setString(5, "test");
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
		
		
		
		
	}

}
