package view;

public class Tempo 
{
	long tInicio = 0;
	long tTotal = 0;
	
	public Tempo()
	{
	tInicio=System.nanoTime();
	//System.out.println("Tiempo de inicio: "+tInicio/1000000 + " milisegundos");
	}
	public long stopTempo()
	{
		long tFinal = System.nanoTime();
		//System.out.println("Tiempo de finalizaciï¿½n: "+(tFinal)/1000000 + " milisegundos");
		System.out.println("Duración de la acción: "+(tFinal-tInicio)/1000000 + " milisegundos.\n");
		return (tFinal-tInicio)/1000000;
	}
	public long stopTempoMicro()
	{
		long tFinal = System.nanoTime();
		//System.out.println("Tiempo de finalizaciï¿½n: "+(tFinal)/1000000 + " milisegundos");
		//System.out.println("Duraciï¿½n de la acciï¿½n: "+(tFinal-tInicio)/1000 + " milisegundos.\n");
		return (tFinal-tInicio)/1000;
	}
	public long stopTempoNano()
	{
		long tFinal = System.nanoTime();
		return (tFinal-tInicio)/1;
	}
}
