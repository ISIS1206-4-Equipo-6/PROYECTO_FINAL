package view;

import model.logic.Modelo;

public class View
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	public void printMenu()
	{
		System.out.println("\nPor favor inserte una opcion: ");
		System.out.println("1. Cargar informacion de las estaciones tripilentas.");
		System.out.println("2. REQ. A: Mejorar estandares de Companias ");
		System.out.println("3. REQ. B.1: Conocer rankings de taxis por puntos en una fecha. ");
		System.out.println("4. REQ. B.2: Conocer rankings de taxis por puntos en un rango de fechas. ");
		System.out.println("5. REQ. C: Consultar mejor horario ");
		System.out.println("0. Exit");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}
}
