package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.Lista;
import model.data_structures.ListaEncadenada;
import model.data_structures.Node;
import model.data_structures.Vertex;

public class TestDiGraph {

	private DiGraph<Integer, Integer> grafo;
	/*
	@Before
	public void setUp1() {
		grafo= new DiGraph<Integer,Integer>(1000, 1000);
	}
	
	public void setUp2() {
		grafo= new DiGraph<Integer,Integer>(1500, 1500);
		for (int i = 0; i < 10; i++) {
			grafo.insertVertex(i, i);
		}
		grafo.addEdge(0, 1, 20,1);
		grafo.addEdge(1, 5, 10,1); // grafo.addEdge(1, 2, 7);
		grafo.addEdge(2, 4, 11,1);
		grafo.addEdge(3, 8, 5,1);
		grafo.addEdge(4, 6, 14,1);//x
		grafo.addEdge(5, 2, 21,1);
		grafo.addEdge(6, 3, 11,1); // 6 4
		grafo.addEdge(7, 4, 21,1);
		grafo.addEdge(4, 6, 31,1);//x
		grafo.addEdge(9, 3, 10,1);
		grafo.addEdge(0, 8, 10,1);
		grafo.addEdge(1, 2, 7,1);
		grafo.addEdge(2, 1, 8,1);
		grafo.addEdge(3, 2, 9,1);
		grafo.addEdge(4, 6, 10,1);//x
		grafo.addEdge(6, 4, 12,1); 
	}
	

	@Test
	public void testContainsVertex (){
		assertFalse(grafo.containsVertex(1));
		setUp2();
		for (int i = 0; i < 10; i++) {
			assertTrue(grafo.containsVertex(i));
		}
	}
	
	@Test
	public void testNumEdges (){
		assertEquals(0, grafo.numEdges());
		setUp2();
		assertEquals(14, grafo.numEdges());
	}

	@Test
	public void testAddEdge (){
		setUp2();
		
		grafo.addEdge(9, 2, 14,1);
		Edge edgeje = grafo.getEdge(9, 2);
		assertNotEquals(null,edgeje);
		assertTrue(14==edgeje.weight());

		
		grafo.addEdge(1, 2, 1.0,1);
		edgeje = grafo.getEdge(1, 2);
		assertTrue(4==edgeje.weight());

	}

	@Test
	public void testGetEdge (){
		Edge edgeje = grafo.getEdge(9, 2);
		assertEquals(null,edgeje);
		
		setUp2();
		edgeje = grafo.getEdge(3, 2);
		assertNotEquals(null,edgeje);
		assertTrue(9==edgeje.weight());
	}

	@Test
	public void testAdjacentVertex (){
		setUp2();
		Lista<Vertex<Integer, Integer>> lista = grafo.adjacentVertex(1);
		Node cabeza = ((ListaEncadenada)lista).getHead();
		Vertex ve1 = (Vertex) cabeza.getElement();
		Vertex ve2 = (Vertex) cabeza.getNext().getElement();

		assertEquals(2, ve1.getId());
		assertEquals(5, ve2.getId());
		assertEquals(null,cabeza.getNext().getNext());
		
		lista = grafo.adjacentVertex(4);
		cabeza = ((ListaEncadenada)lista).getHead();
		ve1 = (Vertex) cabeza.getElement();

		assertEquals(6, ve1.getId());
		assertEquals(null,cabeza.getNext());
			
	}

	@Test
	public void testOutDegree (){
		setUp2();
		assertEquals(2,(int)grafo.getVertex(0).outdegree());
		assertEquals(2,(int)grafo.getVertex(1).outdegree());
		assertEquals(2,(int)grafo.getVertex(2).outdegree());
		assertEquals(2,(int)grafo.getVertex(3).outdegree());
		assertEquals(1,(int)grafo.getVertex(4).outdegree());
		assertEquals(1,(int)grafo.getVertex(5).outdegree());
		assertEquals(2,(int)grafo.getVertex(6).outdegree());
	
		grafo.insertVertex(11,11);
		assertEquals(0,(int)grafo.getVertex(11).outdegree());
	}

	@Test
	public void testVertices (){
		setUp2();
		Lista<Vertex<Integer, Integer>> lista = grafo.vertices();
		assertEquals(10, lista.size());
		Node cabeza = ((ListaEncadenada)lista).getHead();
		int i=0;
		while(cabeza!=null)
		{
			assertEquals(i,(int)((Vertex)(cabeza.getElement())).getId());
			cabeza=cabeza.getNext();
			i++;
		}	
	}
	
	//Los de ortiz xd
	@Test
	public void testDiGraph() {
		setUp1();
		assertTrue(grafo!=null);
		assertEquals(grafo.numEdges(),0);
		assertEquals(grafo.numVertices(),0);
	}

	@Test
	public void testNumVertices() {
		setUp1();
		assertEquals(grafo.numVertices(),0);
		setUp2();
		assertEquals(grafo.numVertices(),10);
	}

	@Test
	public void testInsertVertex() {
		setUp1();
		for (int i = 0; i < 5; i++) {
			grafo.insertVertex(i, i);
		}
		assertEquals(grafo.numVertices(),5);
		setUp2();
		grafo.insertVertex(12, 12);
		assertEquals(grafo.numVertices(), 11);
	}

	@Test
	public void testGetVertex() {
		setUp1();
		assertEquals(grafo.getVertex(0), null);
		setUp2();
		for (int i = 0; i < grafo.numVertices(); i++) {
			assertTrue(grafo.getVertex(i).getId()==i);
		}
	}

	@Test
	public void testAdjacentEdges() {
		setUp2();
		ListaEncadenada l=(ListaEncadenada) grafo.adjacentEdges(1);
		assertTrue(l.size()==2);
		Node head=l.getHead();
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(2)==0);
		head=head.getNext();
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(5)==0);
		l=(ListaEncadenada) grafo.adjacentEdges(2);
		assertTrue(l.size()==2);
		head=l.getHead();
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(1)==0);
		head=head.getNext();
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(4)==0);
	}

	@Test
	public void testIndegree() {
		setUp2();
		assertEquals(grafo.indegree(0),0);
		assertEquals(grafo.indegree(1),2);
		assertEquals(grafo.indegree(2),3);
		assertEquals(grafo.indegree(3),2);
		assertEquals(grafo.indegree(4),3);
		assertEquals(grafo.indegree(5),1);
		assertEquals(grafo.indegree(6),1);
		assertEquals(grafo.indegree(7),0);
		assertEquals(grafo.indegree(8),2);
		assertEquals(grafo.indegree(9),0);
	}

	@Test
	public void testEdges() {
		setUp2();
		ListaEncadenada l=(ListaEncadenada) grafo.edges();
		Node head=l.getHead();
		assertTrue(l.size()==14);
		assertTrue((Integer)((Edge)head.getElement()).getSource().getId().compareTo(0)==0);
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(8)==0);
		head=head.getNext();
		assertTrue((Integer)((Edge)head.getElement()).getSource().getId().compareTo(0)==0);
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(1)==0);
		head=head.getNext();
		assertTrue((Integer)((Edge)head.getElement()).getSource().getId().compareTo(1)==0);
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(2)==0);
		head=head.getNext();
		assertTrue((Integer)((Edge)head.getElement()).getSource().getId().compareTo(1)==0);
		assertTrue((Integer)((Edge)head.getElement()).getDest().getId().compareTo(5)==0);
}
	*/
}
