package mundo;

import java.text.ParseException;

import model.data_structures.ArregloDinamico;
import model.data_structures.Lista;

public class Compania implements Comparable<Compania>{
	
	//ATRIBUTOS
	
	
	private String name;
	
	private  int numServicios;
	
	private ArregloDinamico<Taxi> taxis;
	
	//CONSTRUCTOR
	
	public Compania(String pName){
		name=pName;
		numServicios=0;
		taxis = new ArregloDinamico<>(10);
	}

	public String darName() {
		return name;
	}
	
	public int darNumServicios() {
		return numServicios;
	}
	
	//true si lo creó
	public boolean addTaxi(Taxi amarillo) {
		numServicios++;
		if(taxis.isPresent(amarillo)==-1) {
			taxis.addLast(amarillo);
			return true;
		}
		return false;
	}
	
	public Lista darTaxis() {
		return taxis;
		
	}
	
	public int darCantidadTaxis() {
		return taxis.size();
	}
	
	@Override
	public int compareTo(Compania a) {
		return name.compareTo(a.darName());
	}
}