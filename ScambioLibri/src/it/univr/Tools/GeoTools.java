package it.univr.Tools;

public class GeoTools {
	
	private int maxLatitudine = 100;
	private int maxLongitudine = 100;
	
	public GeoTools(){
		
	}
	
	public int getLatitudine(){
		double random = Math.random();
		int res = (int) (random * maxLatitudine);
		return res;
	}
	
	public int getLongitudine(){
		double random = Math.random();
		int res = (int) (random * maxLongitudine);
		return res;
	}
	
	public double getDistance(int latitudine1, int longitudine1, int latitudine2, int longitudine2){
	
		
		
		return Math.sqrt(Math.pow((latitudine1 - latitudine2), 2)+ Math.pow((longitudine1 - longitudine2), 2));
	}

}
