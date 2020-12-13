package model.data_structures;

import java.util.Comparator;

import mundo.Compania;

public class OrdenarPorCantidadTaxis implements Comparator<Compania>{

	@Override
	public int compare(Compania o1, Compania o2) {
		return o1.darTaxis().size()<o2.darTaxis().size()?-1:(o1.darTaxis().size()==o2.darTaxis().size())?0:1;
	}

}
