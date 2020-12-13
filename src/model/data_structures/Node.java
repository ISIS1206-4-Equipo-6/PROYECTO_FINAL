package model.data_structures;

public class Node<T> implements Comparable<Node<T>>{

	private T element;
	private Node<T> next;

	public Node() {
		element=null;
		next=null;
	}
	
	public Node(T element) {
		this.element=element;
		next=null;
	}
	
	public T getElement( ) {
		return element;
	}
	public Node<T> getNext( ) {
		return next;
	}
	public void setNext(Node<T> n) {
		next = n; 
	}
	
	public void changeElement(T element) {
		this.element=element;
	}
	@Override
	public int compareTo(Node<T> o) {
		return 0;
	}
}
