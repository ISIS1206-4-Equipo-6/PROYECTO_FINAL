package model.data_structures;

import mundo.Compania;

public class ListaEncadenada <T> implements Lista<T>{

	private Node<T> head;

	private Node<T> tail;

	private int tamano;

	public ListaEncadenada() {
		head = null;
		tamano=0;
	}

	@Override
	public void addFirst(T element) {
		Node<T> nElement=new Node<T>(element);
		nElement.setNext(head);
		head=nElement;
		if(tamano==0) {
			tail=head;
		}
		tamano++;
	}

	@Override
	public void addLast(T element) {
		if(tail!=null) {
			tail.setNext(new Node<T>(element));
			tail=tail.getNext();
		}else {
			tail=new Node<T>(element);
			head=tail;
		}
		tamano++;
	}

	@Override
	public void insertElement(T element, int pos) {
		pos--;
		Node<T> actual=head;
		for (int i = 0; i < pos-1; i++) {
			actual=actual.getNext();
		}
		Node<T> agregar=new Node<T>(element);
		agregar.setNext(actual.getNext());
		actual.setNext(agregar);
		if(pos==tamano-1) {
			tail=agregar;
		}else if(pos==0) {
			head=agregar;
		}
		tamano++;
	}

	@Override
	public T removeFirst() {
		if(head!=null) {
			Node<T> first = head;
			head = first.getNext();
			first.setNext(null);
			--tamano;
			return first.getElement();
		}else {
			return null;
		}
	}
	@Override
	public T removeLast() {
		if(head!=null) {
			T res=tail.getElement();
			Node<T> actual=head;
			while(actual.getNext()!=tail) {
				actual=actual.getNext();
			}
			tail=actual;
			tail.setNext(null);
			tamano--;
			return res;
		}else {
			return null;
		}
	}

	@Override
	public T deleteElement(int pos) {
		pos--;
		if(pos>1 && pos<=tamano) {
			Node<T> node = head;
			for(int i=0; i<pos-1; i++)
				node=node.getNext();
			T element = node.getNext().getElement();
			node.setNext( node.getNext().getNext() );
			--tamano;
			return element;
		}else
			return null;
	}

	@Override
	public T firstElement() {
		return head!=null?head.getElement():null;
	}

	@Override
	public T lastElement() {
		return (tail!=null)?tail.getElement():null;
	}

	@Override
	public T getElement(int pos) {
		if(pos<=tamano) {
			pos--;
			Node<T> actual=head;
			for (int i = 0; i < pos; i++) {
				actual=actual.getNext();
			}
			return actual.getElement();
		}else {
			return null;
		}
	}
	


	@Override
	public int size() {
		return tamano;
	}

	@Override
	public boolean isEmpty() {
		return (tamano==0)?true:false;
	}

	@Override
	public int isPresent(T element) {
		Node<T> actual=head;
		int pos=0;
		while(actual.getNext()!=null) {
			if(actual.getElement().equals(element)) {
				return pos+1;
			}
			actual=actual.getNext();
			pos++;
		}
		if(actual.getElement().equals(element)) {
			return pos+1;
		}
		return -1;
	}

	@Override
	public void exchange(int pos1, int pos2) {
		pos1--;
		pos2--;
		Node<T> n1;
		Node<T> n2;
		T temp;
		int menor=Math.min(pos1,pos2);
		int mayor=Math.max(pos1,pos2);
		Node<T> actual=head;
		for (int i = 0; i < menor; i++) {
			actual=actual.getNext();
		}
		n1=actual;
		temp=n1.getElement();
		for (int i = 0; i < mayor-menor ; i++) {
			actual=actual.getNext();
		}
		n2=actual;
		n1.changeElement(n2.getElement());
		n2.changeElement(temp);
	}

	@Override
	public void changeInfo(int pos, T elem) {
		pos--;
		int i=1;
		Node<T> node = head;
		while(i<pos) {
			node=node.getNext(); 
			i++;
		}
		node.getNext().changeElement(elem);
	}

	public void imprimir() {
		Node<T> actual=head;
		while(actual!=null) {
			String s=(String) actual.getElement();
			System.out.println(s);
			actual=actual.getNext();
		}
	}

	@Override
	public T[] darArreglo() {
		T[] respuesta=(T[]) new Comparable[tamano];
		Node<T> actual=head;
		for (int i = 0; i < tamano; i++) {
			respuesta[i]=actual.getElement();
			actual=actual.getNext();
		}
		return respuesta;
	}
	
	public Node getHead() {
		return head;
	}



}
