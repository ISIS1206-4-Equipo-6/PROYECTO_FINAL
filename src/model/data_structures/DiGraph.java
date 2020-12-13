package model.data_structures;


/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class DiGraph<K extends Comparable<K>,V>  {
	
	private final int maxV;
	
	private int E;
	
	public Vertex[] adj;
	
	private int n=0;
	
	private int maximiliano;
	
	private int[] arraysito;
	
	private int[] indegree;
	
	private Edge edgeMenor;
	
	private Edge edgeMayor; 
	
	private int[] rangosIN;
	
	private int[] rangosOUT;
	
	private int[] rangosEdges;

	
	public DiGraph(int V , int maxi)
	{
		 maxV = V;
		 this.maximiliano=maxi;
	     this.E = 0;
	     indegree = new int[maxV];
	     adj =  new Vertex[maxV];
	     rangosIN=new int[7];
	     rangosOUT=new int[7];
	     rangosEdges=new int[7];
	     for (int v = 0; v < V; v++) {
	         adj[v] = (Vertex) new Vertex(null,null);
	     }
	     arraysito= new int[maximiliano];
	     
	     for (int i = 0; i < rangosIN.length; i++) {
			rangosIN[i]=-1;
			rangosOUT[i]=-1;
			rangosEdges[i]=-1;
		}
	     
	     for (int i = 0; i < maximiliano; i++) {
			arraysito[i]=-1;
		}
	}
	public int darMaxV() { //TOADD
		return maxV;
	}
	
	public int darMaximiliano() { //TOADD
		return maximiliano;
	}
	public boolean containsVertex(K id)
	{
		return arraysito[(int)id]!=-1;
	}
	
	public int numVertices(){
		return n;
	}

	public int numEdges() {
		return E;
	}
	
	public void insertVertex(K id, V value) {
		arraysito[(int)id]=n;
		adj[n++]= new Vertex(id,value); 
		
	}
	
	public void addEdge(K source, K dest, double weight, int i){
		
		Vertex<K,V> vSource = getVertex((K) source);
		Vertex<K,V> vDest = getVertex((K) dest);
		Edge edgeje=getEdge(source, dest);
		if(edgeje==null) {
			edgeje=new Edge( vSource , vDest);
			edgeje.recomputeWeight(weight, i);
			vSource.addEdge(edgeje);
			E++;
		}
		else {
			edgeje.recomputeWeight(weight,i);
		}
	}
	
	public Vertex<K,V> getVertex(K id) {
		int i = arraysito[(int)id];
	    if(i!=-1)
	    	return adj[i];
		return null;
	}
	
	public Vertex<K,V> getVertex(int id) {
		if(id==-1) return null;
		int i = arraysito[id];
	    if(i!=-1)
	    	return adj[i];
		return null;
	}
	
	public Edge<K,V> getEdge(K idS, K idD) {
		if(!containsVertex(idS) || !containsVertex(idD))
			return null;
		return getVertex(idS).getEdge(idD);
	}
	
	public Lista<Edge<K,V>> adjacentEdges(K id) {
		return getVertex(id).edges();
	}
	
	public Lista<Vertex<K,V>> adjacentVertex(K id)
	{
		return getVertex(id).vertices();
	}
	
	public int indegree(K vertex) {
		return getVertex(vertex).indegree();
	}
	
	public int outdegree(K vertex){
		return getVertex(vertex).outdegree();
	}
	
	public Lista<Edge<K,V>> edges() {
		Lista listaE = new ListaEncadenada<Edge<K,V>>();
		for (Vertex vertex : adj) {
			Node head = ((ListaEncadenada)(vertex.edges())).getHead();
			while(head!=null)
			{
				listaE.addLast((Edge)head.getElement());
				head= head.getNext();
			}
		}
		return listaE;
	}
	
	public Lista<Vertex<K,V>> vertices() {
		Lista listaV = new ListaEncadenada<Vertex<K,V>>();
		for (Vertex vertex : adj) {
			if(vertex.getId()!=null)
				listaV.addLast(vertex);
		}
		return listaV;
	}

	public void setMenor(Edge pMenor) {
		edgeMenor=pMenor;
	}
	
	public void setMayor(Edge pMayor) {
		edgeMayor=pMayor;
	}
	
	public Edge darEdgeMenor() {
		return edgeMenor;
	}

	public Edge darEdgeMayor() {
		return edgeMayor;
	}
	
	public int getIndexVertex(Vertex v)
	{
		return arraysito[(int) v.getId()];
	}
	
	public int[] darRangosIN() {
		return rangosIN;
	}
	
	public int[] darRangosOUT() {
		return rangosOUT;
	}
	
	public int[] darRangosEdges() {
		return rangosEdges;
	}
}
