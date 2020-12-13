package mundo;

import java.text.ParseException;

import model.data_structures.ArregloDinamico;
import model.data_structures.Lista;

public class ComArea implements Comparable<ComArea>{
	
	//ATRIBUTOS
	
	
	private int id;
	
	//CONSTRUCTOR
	
	public ComArea(int pId){
		id=pId;
	}

	public int darId() {
		return id;
	}
	
	@Override
	public int compareTo(ComArea a) {
		return id<a.darId()?-1:id==a.darId()?0:1;
	}
}