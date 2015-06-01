package it.univr.Tools;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.print.Collation;

public class Test2 {

	public static void main(String[] args) throws ParseException {

		GeoTools gtools = new GeoTools();
		int lat1 = gtools.getLatitudine();
		int lon1 = gtools.getLongitudine();
		
		int lat2 = gtools.getLatitudine();
		int lon2 = gtools.getLongitudine();
	
		int lat3 = gtools.getLatitudine();
		int lon3 = gtools.getLongitudine();
	
		System.out.println("Latitudine e longitudine 1: "+lat1+" - "+lon1);
		System.out.println("Latitudine e longitudine 2: "+lat2+" - "+lon2);
		System.out.println("Latitudine e longitudine 3: "+lat3+" - "+lon3);
		
		System.out.println("Distanza tra 1 e 2: "+gtools.getDistance(lat1, lon1, lat2, lon2));
		System.out.println("Distanza tra 1 e 3: "+gtools.getDistance(lat1, lon1, lat3, lon3));
		System.out.println("Distanza tra 2 e 3: "+gtools.getDistance(lat2, lon2, lat3, lon3));
		
		
		

	}

}
