package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import com.opencsv.exceptions.CsvValidationException;

import model.logic.Modelo;
import mundo.Compania;
import view.View;

public class Controller {
	public static final String tapsi_small ="data/taxi-trips-wrvz-psew-subset-small.csv";
	public static final String tapsi_medio ="data/taxi-trips-wrvz-psew-subset-medium.csv";
	public static final String tapsi_grande ="data/taxi-trips-wrvz-psew-subset-large.csv";

	public final static String[] DATA = {tapsi_small, tapsi_medio, tapsi_grande}; 

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
	}

	public Modelo darModelo()
	{
		return modelo;
	}

	public void run() throws CsvValidationException, IOException, NumberFormatException, ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			lector.nextLine();
			switch(option){
			case 1:
				view.printMessage("Escoja el archivo que va a cargar: "
						+"\n 1 para el archivo de datos pequeños."
						+ "\n 2 para el archivo de datos medianos."
						+ "\n 3 para el archivo de datos completos.");
				view.printMessage("Primer archivo: \n");
				int tipoDatos = Integer.parseInt(lector.nextLine());
				String data1 = DATA[tipoDatos-1];
				view.printMessage("----------------------- La informacion se esta cargando -----------------------");
				modelo=null;
				modelo=new Modelo();
				if(data1!=null) {
					modelo.cargarArchivos(data1);
				}
				else{
					view.printMessage("Digite un numero permitido");
					break;
				}
				view.printMessage("-- La informacion se ha cargado exitosamente.\n");
				view.printMessage("----------------- !BIENVENIDO¡ -----------------");
				view.printMessage("--------- Explorando la magia de los Tapsis jeje --------- ");
				break;
			case 2:
				view.printMessage("Escriba el tamaño de la busqueda para las compañias con más taxis.");
				int M= Integer.parseInt(lector.nextLine());
				view.printMessage("\n Escriba el tamaño de la busqueda para compañias con más servicios");
				int N= Integer.parseInt(lector.nextLine());
				modelo.graciasPorLaInfoCrack();
				modelo.darCompaniasMasTaxis(M);
				modelo.darCompaniasMasServicios(N);
				break;
			case 3:
				view.printMessage("Escriba el tamaño del ranking de taxis con más puntos para una fecha.");
				int M3= Integer.parseInt(lector.nextLine());
				view.printMessage("Escriba la fecha de interes con el siguiente formato YYYY-MM-DD");
				String strFecha3 = lector.nextLine();
				Date fecha3 = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha3);
				modelo.taxisConMasPuntosUnaFecha(fecha3, M3);
				break;
			case 4:
				
				view.printMessage("Escriba el tamaño del ranking de taxis con más puntos para un rango de fechas.");
				int N4= Integer.parseInt(lector.nextLine());
				//int N4 = 10;
				
				view.printMessage("Escriba la fecha inicial de interes con el siguiente formato YYYY-MM-DD");
				String strFechaIn4 = lector.nextLine();
//				String strFechaIn4 = "2019-07-20";
				Date fechaIn4 = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaIn4);
				
				view.printMessage("Escriba la fecha inicial de interes con el siguiente formato YYYY-MM-DD");
				String strFechaFin4 = lector.nextLine();
//				String strFechaFin4 =  "2019-08-08";
				Date fechaFin4 = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaFin4);

				modelo.taxisConMasPuntosRangoFecha(fechaIn4, fechaFin4,N4);
				
				break;
		
				
			case 5: 
				view.printMessage("Digite la Comunity Area inicial:");
				int caIn= Integer.parseInt(lector.nextLine());
//				int caIn = 8;
				view.printMessage("Digite la Comunity Area final:");
				int caFin= Integer.parseInt(lector.nextLine());
//				int caFin = 28;
				view.printMessage("Digite la hora inicial con el siguiente formato:  HH:mm");
				String str51 = lector.nextLine();
				int horaIn = Integer.parseInt(str51.substring(0, 2));
				int minIn = Integer.parseInt(str51.substring(3, 5));

				view.printMessage("Digite la hora final con el siguiente formato:  HH:mm");
				String str52 = lector.nextLine();
				int horaFin = Integer.parseInt(str52.substring(0, 2));
				int minFin = Integer.parseInt(str52.substring(3, 5));

		//		System.out.println(horaFin+" : "+minFin);
				modelo.buscarRutas(horaIn, minIn, horaFin, minFin, caIn, caFin );
				break;
			case 0:
				view.printMessage("!Adieu! Gracias por usar nuestros servicios :)");
				lector.close();
				fin=true;
				break;
			default: 
				view.printMessage("------------------- \n Â¡Opcion Invalida! \n-------------------");
				break;
			}
		}	
	}	
}
