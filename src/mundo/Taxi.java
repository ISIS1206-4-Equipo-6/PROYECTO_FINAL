package mundo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.data_structures.ArregloDinamico;
import model.data_structures.ListaEncadenada;
import model.data_structures.RedBlackTree;

public class Taxi implements Comparable<Taxi>{

	private String id;

	private Compania compa;

	private RedBlackTree<Date,Trip> rbtViajes;

	private double puntosTemp;
	
	public Taxi(String id, Compania compa) {
		this.id=id;
		this.compa=compa;
		rbtViajes = new RedBlackTree<Date,Trip>();
		puntosTemp=0;
	}



	public void agregarViaje(String pId, String pFecha, double pMillas, double pDinero) throws ParseException
	{
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(pFecha);
		Trip viaje = new Trip(pId, fecha, pMillas, pDinero);
		rbtViajes.put(fecha, viaje);

	}

	public RedBlackTree darTripTree() 
	{
		return rbtViajes;
	}

	public String darId() {
		return id;
	}

	public Compania darCompa() {
		return compa;
	}
	
	public void setPuntos(double p) {
		puntosTemp=p;
	}
	
	public double getPuntos() {
		return puntosTemp;
	}

	@Override
	public int compareTo(Taxi o) {
		// TODO Auto-generated method stub
		return id.equalsIgnoreCase(o.darId())?0:-1;
	}

}
