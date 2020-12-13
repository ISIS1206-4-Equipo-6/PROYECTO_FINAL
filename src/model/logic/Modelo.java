package model.logic;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import controller.Controller;
import model.data_structures.ArregloDinamico;
import model.data_structures.DiGraph;
import model.data_structures.DijkstraSP;
import model.data_structures.Edge;
import model.data_structures.LinearProbingRemplace;
import model.data_structures.Lista;
import model.data_structures.ListaEncadenada;
import model.data_structures.Node;
import model.data_structures.OrdenarPorCantidadServicios;
import model.data_structures.OrdenarPorCantidadTaxis;
import model.data_structures.OrdenarTaxisPorPuntos;
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaSimbolos;
import model.data_structures.Vertex;
import mundo.ComArea;
import mundo.Compania;
import mundo.Taxi;
import mundo.Trip;
import view.Tempo;

/**
 * Definicion del modelo del mundo
 *
 */
@SuppressWarnings("hiding")
public class Modelo{
	/**
	 * Atributos del modelo del mundo
	 */
	private int maxCompas=53;

	private int datosLeidos = 0;
	private final int maximiliano = 78;
	private final int maxVertices = 77;
	
	public ArregloDinamico datosCompanias;
	
	public TablaSimbolos datosTaxisTotales;
	
	public DiGraph datosComArea;
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo()
	{
		datosCompanias =new ArregloDinamico<Compania>(maxCompas);
		datosTaxisTotales = new TablaHashLinearProbing<String, Taxi>();
		datosComArea =new DiGraph<Integer, ComArea>(maxVertices, maximiliano);
	}

	public void cargarArchivos(String data1) throws CsvValidationException, IOException, NumberFormatException, ParseException {
		Tempo t1 = new Tempo();
		Compania nuevaCompany=null;
		CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
		CSVReader reader = new CSVReaderBuilder(new FileReader(data1)).withCSVParser(csvParser).build();
		String [] nextLine;
		nextLine = reader.readNext();
	
		while ((nextLine = reader.readNext()) != null) {
			datosLeidos++;
			nuevaCompany = new Compania(nextLine[12]);
			int i = datosCompanias.isPresent(nuevaCompany);
			Taxi taxi = null;
			if(i==-1) {
				 taxi = new Taxi(nextLine[1],nuevaCompany);
				datosCompanias.addLast(nuevaCompany);
				nuevaCompany.addTaxi(taxi);
			}
			else {
				nuevaCompany = (Compania) datosCompanias.getElement(i);
				taxi = new Taxi(nextLine[1],nuevaCompany);
				nuevaCompany.addTaxi(taxi);
			}
			
			datosTaxisTotales.put(taxi.darId(), taxi);
			taxi=(Taxi) ((Node)datosTaxisTotales.get(taxi.darId())).getElement();
			if(nextLine[5].equals("") || nextLine[5].equals("0") || nextLine[10].equals("") || nextLine[10].equals("0"))
				continue;
			taxi.agregarViaje( nextLine[0], nextLine[2], Double.parseDouble(nextLine[5]), Double.parseDouble(nextLine[10]));
			
			if(nextLine[15].equals("")||nextLine[16].equals("")|| nextLine[4].equals("")||nextLine[2].equals("")|| nextLine[4].equals("0.0"))continue;
			int startId = (int)(Double.parseDouble(nextLine[15]));
			int endId =  (int)(Double.parseDouble(nextLine[16]));
			if( !datosComArea.containsVertex(startId)) {
				datosComArea.insertVertex(startId, new ComArea(startId));
			}

			if( !datosComArea.containsVertex(endId))
				datosComArea.insertVertex(endId, new ComArea(endId));
			datosComArea.addEdge(startId, endId, Double.parseDouble(nextLine[4]), calcularPosicion(nextLine[2]));
			
		}
		t1.stopTempo();

	}

	private int calcularPosicion(String str) {
		int hora = Integer.parseInt(str.substring(11, 13));
		int min = Integer.parseInt(str.substring(14, 16));
		return hora*4+(min/15);
	}

	public int size()
	{
		return datosLeidos;
	}

	public int darNumCompanias() {
		return datosCompanias.size();
	}

	public int darNumTaxis() {
		return datosTaxisTotales.size();
	}

	public void graciasPorLaInfoCrack() {
		System.out.println("Información de carga de datos: \n" );
		System.out.println("	El numero de datos leidos fue de "+ size()+".\n"
				+ "	El numero de total de taxis registrados fue de : "+ darNumTaxis()+ ".\n"
				+ "	El numero de total de companias registradas fue de:"+ darNumCompanias() +"\n ");
		}

	public void darCompaniasMasTaxis(int M) {
		
		Comparable[] arreglo=datosCompanias.darArreglo();
		QuickSort.sort(arreglo, new OrdenarPorCantidadTaxis(), false);
		System.out.println("Top "+M+" de compañias con más taxis \n");
		int i=0;
		while(i<M) {
			Compania compa  = (Compania)arreglo[i];
			System.out.println("\t"+(i+1)+". "+compa.darName()+" con "+ compa.darCantidadTaxis()+" taxis afiliados.");
			i++;
		}
	}
	
public void darCompaniasMasServicios(int N) {
		
		Comparable[] arreglo=datosCompanias.darArreglo();
		QuickSort.sort(arreglo, new OrdenarPorCantidadServicios(), false);
		System.out.println("\nTop "+N+" de compañias con más servicios prestados \n");
		int i=0;
		while(i<N) {
			Compania compa  = (Compania)arreglo[i];
			System.out.println("\t"+(i+1)+". "+compa.darName()+" con "+ compa.darNumServicios()+" servicios prestados.");
			i++;
		}
	}


public void taxisConMasPuntosUnaFecha(Date fecha, int M) {
	Tempo t1 = new Tempo();
	Comparable[] taxisSort = new Comparable[datosTaxisTotales.size()];
	ListaEncadenada taxis = (ListaEncadenada) datosTaxisTotales.valSet();
	Node cabeza = taxis.getHead();
	int i =0;
	while(cabeza != null) {
		Taxi tapsi = (Taxi) cabeza.getElement();
		ListaEncadenada lol = (ListaEncadenada) tapsi.darTripTree().get(fecha);
		if(lol==null)
		{
			cabeza = cabeza.getNext();
			tapsi.setPuntos(0);
			taxisSort[i]=tapsi;
			i++;
			continue;
		}
		tapsi.setPuntos(0);
		tapsi.setPuntos(calcularPuntosUnaFecha(lol));
		taxisSort[i]=tapsi;
		i++;
		cabeza = cabeza.getNext();
	}
	QuickSort.sort(taxisSort, new OrdenarTaxisPorPuntos(), false);
	
	
	System.out.println("Top "+M+" de taxis con más puntos para la fecha ingresada");
	for (int j = 0; j < M; j++) {
		Taxi taxi  = (Taxi)taxisSort[j];
		System.out.println("\t"+(j+1)+". Taxi con la id: "+taxi.darId().substring(0, 10)+" con "+taxi.getPuntos()  +" puntos conseguidos.");
	}
	t1.stopTempo();
}




public void taxisConMasPuntosRangoFecha(Date fechaIn4, Date fechaFin4, int M) {
	// TODO Auto-generated method stub
	
	Tempo t1 = new Tempo();
	Comparable[] taxisSort = new Comparable[datosTaxisTotales.size()];
	ListaEncadenada taxis = (ListaEncadenada) datosTaxisTotales.valSet();
	Node cabeza = taxis.getHead();
	int i =0;
	while(cabeza != null) {
		Taxi tapsi = (Taxi) cabeza.getElement();
		ListaEncadenada listaRangos = (ListaEncadenada) tapsi.darTripTree().valuesInRange(fechaIn4, fechaFin4);
		if(listaRangos==null)
		{
			cabeza = cabeza.getNext();
			tapsi.setPuntos(0);
			taxisSort[i]=tapsi;
			i++;
			continue;
		}
		tapsi.setPuntos(0);
		tapsi.setPuntos(calcularPuntosRangoFecha(listaRangos));
		taxisSort[i]=tapsi;
		i++;
		cabeza = cabeza.getNext();
	}
	QuickSort.sort(taxisSort, new OrdenarTaxisPorPuntos(), false);
	
	
	System.out.println("Top "+M+" de taxis con más puntos para la fecha ingresada");
	for (int j = 0; j < M; j++) {
		Taxi taxi  = (Taxi)taxisSort[j];
		System.out.println("\t"+(j+1)+". Taxi con la id: "+taxi.darId().substring(0, 10)+" con "+taxi.getPuntos()  +" puntos conseguidos.");
	}
	t1.stopTempo();
	
}

public double calcularPuntosUnaFecha(ListaEncadenada listaTrips) {
	double millas = 0;
	double dinero = 0;
	Node cabeza = listaTrips.getHead();
	while(cabeza != null) {
		Trip viaje = (Trip) cabeza.getElement();
		millas+=viaje.darMillas();
		dinero+=viaje.darDinero();
		cabeza = cabeza.getNext();
	}
	return (millas/dinero)*listaTrips.size();
	
}

public double calcularPuntosRangoFecha(ListaEncadenada listaRangos) {
	double viajesTotales = 0;	
	double millas = 0;
	double dinero = 0;
	Node cabezaListas = listaRangos.getHead();
	while(cabezaListas != null) {
		ListaEncadenada listaTrips= ((ListaEncadenada)cabezaListas.getElement());
		Node cabeza = listaTrips.getHead();
		while(cabeza != null) {
			Trip viaje = (Trip) cabeza.getElement();
			millas+=viaje.darMillas();
			dinero+=viaje.darDinero();
			cabeza = cabeza.getNext();
		}
		viajesTotales+=listaTrips.size();
		cabezaListas = cabezaListas.getNext();
	}
	return (millas/dinero)*viajesTotales;
}


public void buscarRutas(int horaInicio, int minInicio,int horaFin, int minFin, int comAreaIn, int comArea2) {
	DijkstraSP bestDusp = null;
	double menor = -1;
	int posIni = horaInicio*4 + minInicio/15;
	int posFin = horaFin*4 + minFin/15;
	int iMin = 0;
	for ( int j= posIni; j <= posFin; j++) {
		
		DijkstraSP dusp= new DijkstraSP(datosComArea, datosComArea.getIndexVertex(datosComArea.getVertex(comAreaIn)),j);
		double d= dusp.distTo( datosComArea.getIndexVertex(datosComArea.getVertex(comArea2)));
			
		if((menor==-1) || d<menor) //&&  d!=0.0
		{
			bestDusp = dusp;
			menor = d;
			iMin=j;
		}	
	}
	
	Iterable<Edge> i=bestDusp.pathTo(datosComArea.getIndexVertex(datosComArea.getVertex(comArea2)), iMin);
	if(i==null)
	{
		System.out.println("No existe una ruta posible entre las dos estaciones más cercanas a esas coordenadas");
		return;
	}
	String hora = iMin/4+"";
	String minutos = (iMin%4)*15+"";
	if(hora.length()==1) {
		hora="0"+hora;
	}
	if(minutos.length()==1) {
		minutos="0"+minutos;
	}
	System.out.println("\nEl mejor horario para dicho desplazamiento es a las "+ hora+ ":"+minutos + " con un tiempo estimado de: "+bestDusp.distTo(datosComArea.getIndexVertex(datosComArea.getVertex(comArea2)))+ " segundos.");
	
	System.out.println("\nLa ruta que debe tomar se describe a continuación: ");
	for(Edge s: i){
		System.out.println("\nDirijase desde al area "+((ComArea)s.getSource().getInfo()).darId()+" hacía el área "+((ComArea)s.getDest().getInfo()).darId()+".\n");
	}
}

}