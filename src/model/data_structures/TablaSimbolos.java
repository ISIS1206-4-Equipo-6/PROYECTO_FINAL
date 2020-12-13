package model.data_structures;

public interface TablaSimbolos<K extends Comparable<K>, V extends Comparable<V>> {

	public void put(K key, V value);

	public V get(K key);

	public V remove (K key);

	public boolean contains(K key);

	public boolean isEmpty();

	public int size();

	public Lista<K> keySet();

	public Lista<V> valSet();

	public int capacity();
	
	public int getNRehash();

	public int hash(K key);
}
