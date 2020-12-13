package model.data_structures;


/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Edge<K extends Comparable<K>,V>  {
	
	private Vertex<K,V> source;
	
	private Vertex<K,V> dest;
	
	private int[] nTimes;
	
	private double[] weights;
	
	private int[] rangosEdges;
	
	public Edge(Vertex<K,V> source, Vertex<K,V> dest)
	{
		weights = new double[96];
		nTimes = new int[96];
		
		for (int i =0; i<96; i++) {
			weights[i]=Double.POSITIVE_INFINITY;
			nTimes[i]=0;
			
		}
		this.source = source;
		this.dest = dest;
	}
	
	public Vertex<K,V> getSource(){
		return source;
	}

	public Vertex<K,V> getDest()  {
		return dest;
	}
	
	public double weight(int pos) {
		return weights[pos];
	}
		
	public void recomputeWeight(double newWeight, int i)
	{
		if(weights[i]==Double.POSITIVE_INFINITY) {
			weights[i]=0;
		}
		weights[i]=(weights[i]*nTimes[i] + newWeight)/(nTimes[i]+1);
		nTimes[i]++;
	}
	
	
    public Vertex other(Vertex vertex) {
    	
        if      (vertex.getId() == source.getId()) return dest;
        else if (vertex.getId() == dest.getId()) return source;
        else throw new IllegalArgumentException("Illegal endpoint");
    }
}
