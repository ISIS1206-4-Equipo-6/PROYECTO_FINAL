package mundo;

import java.util.Date;

public class Trip implements Comparable<Trip>
{

	private String id;
	
	private Date fecha;
	
	private double millas;
	
	private double dinero;
	
	
	public Trip(String pId, Date pFecha, double pMillas, double pDinero) {
		id = pId;
		fecha=pFecha;
		millas= pMillas;
		dinero=pDinero;
	}
	
	public String darId() {
		return id;
	}

	public Date darFecha() {
		return fecha;
	}

	public double darMillas() {
		return millas;
	}

	public double darDinero() {
		return dinero;
	}
	
	
	
	@Override
	public int compareTo(Trip o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
