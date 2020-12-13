package model.data_structures;


public class NodeRedBlack<K,V> implements Comparable<NodeRedBlack<K,V>>{

	private K key;
	private V value;
	private int[] categories;
	private NodeRedBlack<K,V> left;
	private NodeRedBlack<K,V> right;
	private NodeRedBlack<K,V> next;
	private int size;
	private boolean color;

	public NodeRedBlack(K key, V value, int size, boolean color) {
		this.key=key;
		this.value=value;
		this.size=size;
		this.color = color;
		this.categories=new int[4];
	}
	
	public void setColor(boolean color) {
		this.color=color;
	}
	
	public boolean getColor() {
		return color;
	}

	public K getKey( ) {
		return key;
	}

	public V getValue() {
		return value;
	}

	public NodeRedBlack<K,V> getRight( ) {
		return right;
	}

	public NodeRedBlack<K,V> getLeft( ) {
		return left;
	}

	public void setRight(NodeRedBlack<K,V> n) {
		right = n; 
	}

	public void setLeft(NodeRedBlack<K,V> n) {
		left = n; 
	}

	public void setValue(V value)
	{
		this.value = value;
	}

	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size=size;
	}

	public void changeElement(K key, V value) {
		this.key=key;
		this.value=value;
	}
	@Override
	public int compareTo(NodeRedBlack<K,V> o) {
		return 0;
	}

	public void inorden(Lista<K> lista) {
		if(this.left!=null) {
			this.left.inorden(lista);
		}
		lista.addLast(this.key);
		if(this.right!=null) {
			this.right.inorden(lista);
		}
	}

	public void setNext(NodeRedBlack nodeTree) {
		this.next = nodeTree;
	}

	public NodeRedBlack getNext() {
		return next;
	}

}
