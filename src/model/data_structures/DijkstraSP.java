package model.data_structures;

/******************************************************************************
 *  Compilation:  javac DijkstraSP.java
 *  Execution:    java DijkstraSP input.txt s
 *  Dependencies: DiGraph.java IndexMinPQ.java Stack.java Edge.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/largeEWD.txt
 *
 *  Dijkstra's algorithm. Computes the shortest path tree.
 *  Assumes all weights are nonnegative.
 *
 *  % java DijkstraSP tinyEWD.txt 0
 *  0 to 0 (0.00)  
 *  0 to 1 (1.05)  0->4  0.38   4->5  0.35   5->1  0.32   
 *  0 to 2 (0.26)  0->2  0.26   
 *  0 to 3 (0.99)  0->2  0.26   2->7  0.34   7->3  0.39   
 *  0 to 4 (0.38)  0->4  0.38   
 *  0 to 5 (0.73)  0->4  0.38   4->5  0.35   
 *  0 to 6 (1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
 *  0 to 7 (0.60)  0->2  0.26   2->7  0.34   
 *
 *  % java DijkstraSP mediumEWD.txt 0
 *  0 to 0 (0.00)  
 *  0 to 1 (0.71)  0->44  0.06   44->93  0.07   ...  107->1  0.07   
 *  0 to 2 (0.65)  0->44  0.06   44->231  0.10  ...  42->2  0.11   
 *  0 to 3 (0.46)  0->97  0.08   97->248  0.09  ...  45->3  0.12   
 *  0 to 4 (0.42)  0->44  0.06   44->93  0.07   ...  77->4  0.11   
 *  ...
 *
 ******************************************************************************/



/**
 *  The {@code DijkstraSP} class represents a data type for solving the
 *  single-source shortest paths problem in edge-weighted digraphs
 *  where the edge weights are nonnegative.
 *  <p>
 *  This implementation uses <em>Dijkstra's algorithm</em> with a
 *  <em>binary heap</em>. The constructor takes
 *  &Theta;(<em>E</em> log <em>V</em>) time in the worst case,
 *  where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges. Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the
 *  edge-weighted digraph).
 *  <p>
 *  For additional documentation,    
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of    
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DijkstraSP {
	private double[] distTo;          // distTo[v] = distance  of shortest s->v path
	private Edge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Double> pq;    // priority queue of vertices
	private DiGraph G;
	/**
	 * Computes a shortest-paths tree from the source vertex {@code s} to every other
	 * vertex in the edge-weighted digraph {@code G}.
	 *
	 * @param  G the edge-weighted digraph
	 * @param  s the source vertex
	 * @throws IllegalArgumentException if an edge weight is negative
	 * @throws IllegalArgumentException unless {@code 0 <= s < V}
	 */
	public DijkstraSP(DiGraph G, int s, int i ) {

		distTo = new double[G.numVertices()];
		edgeTo = new Edge[G.numVertices()];
		this.G=G;
		for (int v = 0; v < G.numVertices(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(G.numVertices());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = (int) G.adj[pq.delMin()].getId();
			Node cabeza =  ((ListaEncadenada)(G.getVertex(v)).edges()).getHead();
			while(cabeza!=null)
			{	
				Edge wert = (Edge)(cabeza.getElement());
				relax(wert,i);
				cabeza=cabeza.getNext();
			}

		}
	}

	// relax edge e and update pq if changed
	private void relax(Edge e, int i) {
		Vertex vert = e.getSource(), wert = e.getDest();
		int v=G.getIndexVertex(vert);
		int w=G.getIndexVertex(wert);
		//System.out.println("v: "+ v+" w: "+w+ " vert: "+vert.getId()+" wert: "+wert.getId());
		if (distTo[w] > distTo[v] + e.weight(i)) {
			distTo[w] = distTo[v] + e.weight(i);
			edgeTo[w] = e;
			if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else                pq.insert(w, distTo[w]);
		}
	}

	/**
	 * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
	 * @param  v the destination vertex
	 * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
	 *         {@code Double.POSITIVE_INFINITY} if no such path
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public double distTo(int v) {
		return distTo[v];
	}

	/**
	 * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
	 *
	 * @param  v the destination vertex
	 * @return {@code true} if there is a path from the source vertex
	 *         {@code s} to vertex {@code v}; {@code false} otherwise
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/**
	 * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
	 *
	 * @param  v the destination vertex
	 * @return a shortest path from the source vertex {@code s} to vertex {@code v}
	 *         as an iterable of edges, and {@code null} if no such path
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Edge> pathTo(int v, int i) {
		if (!hasPathTo(v)) return null;
		StackEdge<Edge> path = new StackEdge<Edge>();
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[G.getIndexVertex(e.getSource())]) {
			path.push(e, i);
		}
		return path;
	}
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
