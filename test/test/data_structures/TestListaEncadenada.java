package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.DiGraph;
import model.data_structures.ListaEncadenada;

public class TestListaEncadenada {

	private DiGraph<Integer, Integer> grafo;
	
	private ListaEncadenada<Integer> arreglo;
	private static int TAMANO=100;

	@Before
	public void setUp1() {
		arreglo= new ListaEncadenada();
	}

	public void setUp2() {
		for (int i = 0; i < TAMANO; i++) {
			arreglo.addLast(i);
		}
	}

	
	
	@Test
	public void testArregloDinamico() {
		setUp1();
		assertTrue(arreglo!=null);
		assertEquals(arreglo.firstElement(),null);
		assertEquals(arreglo.size(),0);
	}

	@Test
	public void testAddFirst() {
		for (int i = 0; i <  TAMANO; i++) {
			arreglo.addFirst(i);
			assertTrue(arreglo.firstElement()==i);
			assertTrue(arreglo.size()==i+1);
			assertTrue(arreglo.isPresent(new Integer(i))!=-1);
		}
	}

	@Test
	public void testInsertElement() {
		setUp2();
		arreglo.insertElement(777, 1);
		assertTrue(arreglo.firstElement()==777);
		assertTrue(arreglo.size()==101);
		assertTrue(arreglo.isPresent(777)!=-1);
		arreglo.insertElement(555, 50);
		assertTrue(arreglo.getElement(50)==555);
		assertTrue(arreglo.size()==102);
		assertTrue(arreglo.isPresent(777)!=-1);
		arreglo.insertElement(222, 100);
		assertTrue(arreglo.getElement(100)==222);
		assertTrue(arreglo.size()==103);
		assertTrue(arreglo.isPresent(222)!=-1);

	}

	@Test
	public void testRemoveLast() {
		setUp2();
		for (int i = 0; i < arreglo.size(); i++) {
			arreglo.removeLast();
			assertTrue(arreglo.size()==100-(i+1));
			assertEquals(arreglo.isPresent(100-(i+1)), -1);
		}
	}

	@Test
	public void testFirstElement() {
		setUp1();
		assertTrue(arreglo.firstElement()==null);
		setUp2();
		assertTrue(arreglo.firstElement()==0);
	}

	@Test
	public void testGetElement() {
		setUp2();
		for (int i = 0; i < arreglo.size(); i++) {
			assertTrue(arreglo.getElement(i+1)==i);
		}
	}

	@Test
	public void testIsEmpty() {
		assertTrue(arreglo.isEmpty());
		setUp2();
		assertFalse(arreglo.isEmpty());
	}
	
	public void testExchange() {
		setUp2();
		arreglo.exchange(1, 100);
		assertTrue(arreglo.getElement(1)==99);
		assertTrue(arreglo.getElement(100)==0);
		arreglo.exchange(30, 50);
		assertTrue(arreglo.getElement(30)==49);
		assertTrue(arreglo.getElement(50)==29);
		arreglo.exchange(10, 11);
		assertTrue(arreglo.getElement(11)==9);
		assertTrue(arreglo.getElement(10)==10);
	}
	
	@Test
    public void testChangeInfo() {
        setUp2();
        for (int i = 1; i <= arreglo.size(); i++) {
            arreglo.changeInfo(i, 0);
        }
        for (int i = 1; i <= arreglo.size(); i++) {
            assertTrue((int)arreglo.getElement(i)==0);
        }
	}

 

    @Test
    public void testIsPresent() {
        setUp2();
        for (int i = 0; i < arreglo.size(); i++) {
            assertTrue(arreglo.isPresent(i)==i+1);
            
        }
        assertTrue(arreglo.isPresent(300)==-1);
    }

 

    @Test
    public void testAddLast()
    {
        arreglo.addLast(999);
        assertEquals(arreglo.isPresent(999),1);
        assertTrue(arreglo.firstElement()==999);
        assertEquals(arreglo.size(),1);
        arreglo.addLast(777);
        assertEquals(arreglo.isPresent(777),2);
        assertEquals(arreglo.size(),2);
    }

 


    @Test
    public void testRemoveFirst() {
        setUp2();
        arreglo.removeFirst();
        assertEquals(arreglo.isPresent(0),-1);
        assertEquals((int)arreglo.firstElement(),1);
        assertEquals(arreglo.size(),99);
        arreglo.removeFirst();
        assertEquals(arreglo.isPresent(1),-1);
        assertEquals((int)arreglo.firstElement(),2);
        assertEquals(arreglo.size(),98);
    }

    @Test

    public void testDeleteElement(){

        setUp2();
        arreglo.deleteElement(3);
        assertEquals((int)arreglo.getElement(3),3);
        assertEquals((int)arreglo.getElement(4),4);
        arreglo.deleteElement(50);
        assertEquals((int)arreglo.getElement(50),51);

    }

    @Test
    public void testLastElement() {
        setUp2();
        assertTrue(arreglo.lastElement()==99);
    }

}


