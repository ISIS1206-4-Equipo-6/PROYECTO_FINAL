package model.data_structures;

import java.util.Comparator;

import mundo.Compania;

public class OrdenarPorCantidadServicios implements Comparator<Compania>{

	@Override
	public int compare(Compania o1, Compania o2) {
		return o1.darNumServicios()<o2.darNumServicios()?-1:(o1.darNumServicios()==o2.darNumServicios())?0:1;
	}

}
