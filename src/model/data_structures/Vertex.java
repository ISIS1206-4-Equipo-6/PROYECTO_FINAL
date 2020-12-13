package model.data_structures;

public class Vertex<K extends Comparable<K>, V> implements Comparable <Vertex>{

	private K id;
	
	private V value;

	private boolean mark;

	private int outdegree;

	private int indegree;

	private ListaEncadenada edges;

	private ListaEncadenada vertices;
	
	private int[] rangosIN;
	private int[] rangosOUT;


	public Vertex(K id, V value) {
		this.id=id;
		this.value=value;
		edges = new ListaEncadenada<Edge>();
		vertices = new ListaEncadenada<Vertex>();
		rangosIN= new int[7];
		rangosOUT= new int[7];
	}

	public K getId() {
		return id;
	}


	public V getInfo() {
		return value;
	}

	public boolean getMark() {
		return mark;
	}

	public void addEdge( Edge<K,V> edge ){
		edges.addFirst(edge);
		vertices.addFirst(edge.getDest());
		edge.getDest().sumarInDegree();
		outdegree++;
	}

	public void mark(){
		mark=true;
	}

	public void unmark() {
		mark=false;
	}

	public int outdegree() {
		return outdegree;
	}

	public int indegree() {
		return indegree;
	}

	public Edge<K,V> getEdge(K vertex) {

		Node nodito = edges.getHead();
		while(nodito!=null) {
			if((Integer)((Edge)nodito.getElement()).getDest().getId().compareTo((Integer)vertex)==0) {
				return (Edge)nodito.getElement();
			}

			nodito=nodito.getNext();
		}
		return null;
	}

	public Lista<Vertex<K,V>> vertices() {
		return vertices;
	}

	public Lista<Edge<K,V>> edges() {
		return edges;
	}

	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void sumarInDegree() {
		indegree++;
	}
	
	public void sumarRangoIN(int pos) {
		rangosIN[pos]++;
	}
	
	public int darRangoIN(int pos) {
		return rangosIN[pos];
	}
	
	public void sumarRangoOUT(int pos) {
		rangosOUT[pos]++;
	}
	
	public int darRangoOUT(int pos) {
		return rangosOUT[pos];
	}
}
