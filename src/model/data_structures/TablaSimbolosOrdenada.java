package model.data_structures;

public interface TablaSimbolosOrdenada<K extends Comparable<K>, V> {

	public void put(K key, V value);

	public V get(K key);
	
	public int getHeight(K key);

	public int height();

	public K min();
	
	public K max();
	
	public boolean contains(K key);

	public boolean isEmpty();

	public int size();

	public Lista<K> keySet();

	public Lista<K> keysInRange(K init, K end);
	
	public Lista<V> valuesInRange(K init, K end);

}
