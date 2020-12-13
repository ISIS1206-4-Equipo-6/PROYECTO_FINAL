package model.data_structures;

public class TablaHashLinearProbing<K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos <K, V> 
{
	private int N;
	private int M=1;
	private K[] keys;
	private int nRehash=0;
	private ListaEncadenada<V>[] miniListas;


	public TablaHashLinearProbing() {
		keys = (K[]) new Comparable[M];
		miniListas =  new ListaEncadenada[M];
	}

	public int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int size()
	{
		return N;
	}
	public int capacity()
	{
		return M;
	}

	public void rehash(int i) {
		K[] tempK = keys;
		ListaEncadenada<V>[] tempV = miniListas;
		M=i;
		N=0;
		keys = (K[])new Comparable[M];
		miniListas = new ListaEncadenada[M];
		for (int j = 0; j < tempV.length; j++) {
			if(tempK[j]!=null) {
				put(tempK[j],tempV[j]);
			}
		}
		nRehash++;
	}


	@Override
	public void put(K key, V val)
	{

		if (N >= 3*M/4)
			rehash(2*M); // double M (see text)
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
			{
				//(miniListas[i]).addLast(val);
				return;
			}
		keys[i] = key;
		miniListas[i]= new ListaEncadenada<>();
		(miniListas[i]).addLast(val);
		N++;
	}

	public void put(K key, ListaEncadenada<V> listaV)
	{
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
			{
				miniListas[i]=listaV;
				return;
			}
		keys[i] = key;
		miniListas[i] = listaV;
		N++;
	}

	public V get(K key)
	{
		//TODO mejorar!!!!!
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return (V)(miniListas[i].getHead());
		return null;
	}


	@Override
	public V remove(K key) {
		//TODO otro más
		if (!contains(key)) return null;
		int i = hash(key);
		while (!key.equals(keys[ i]))
			i = (i + 1) % M;

		V valorDeleted = miniListas[i].firstElement();
		keys[i] = null;
		miniListas[i] = null;
		i = (i + 1) % M;
		while (keys[i] != null) {
			K keyToRedo = keys[i];
			ListaEncadenada<V> nodoToRedo = miniListas[i];
			keys[i] = null;
			miniListas[i] = null;
			N--;
			put(keyToRedo, nodoToRedo); 
			i = (i + 1) % M;
		}
		N--;
		if (N > 0 && N == 3*M/4) 
			rehash(M/2);
		return valorDeleted;
	}

	public boolean contains(K key)
	{
		if(get(key)==null)
			return false;
		V val = (V) ((Node)get(key)).getElement();
		return val!=null?true:false;
	}

	public boolean isEmpty()
	{
		return (size()==0)?true:false;
	}

	@Override
	public Lista<K> keySet()
	{
		ArregloDinamico<K> arrayKeys = new ArregloDinamico<K>(N);
		for(K k:keys)
			if(k!=null)
				arrayKeys.addLast((K) k);

		return arrayKeys;
	}


	@Override
	public Lista<V> valSet() {
		// TODO Auto-generated method stub
		Lista<V> listaNodos = new ListaEncadenada<V>();
		for(ListaEncadenada le:miniListas)
		{	
			if(le!=null) {
				Node cabeza = le.getHead();
				V v = (V) cabeza.getElement();
				listaNodos.addLast(v);


			}
		}
		return listaNodos;
	}

	public int getNRehash() {
		return nRehash;
	}
}