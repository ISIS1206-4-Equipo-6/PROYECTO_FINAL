package model.data_structures;

import java.util.Comparator;

import mundo.Compania;
import mundo.Taxi;

public class OrdenarTaxisPorPuntos implements Comparator<Taxi>{

	@Override
	public int compare(Taxi o1, Taxi o2) {
		return o1.getPuntos()<o2.getPuntos()?-1:(o1.getPuntos()==o2.getPuntos())?0:1;
	}

}
