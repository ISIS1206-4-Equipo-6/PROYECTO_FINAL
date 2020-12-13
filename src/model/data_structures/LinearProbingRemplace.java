package model.data_structures;

public class LinearProbingRemplace <K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos <K, V> {
	private int N;
	private int M=1;
	private K[] keys;
	private int nRehash=0;
	private V[] vals;
	private K keyMayor;
	private V valMayor;


	public LinearProbingRemplace() {
		keys = (K[]) new Comparable[M];
		vals = (V[]) new Comparable[M];
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
		V[] tempV = vals;
		M=i;
		N=0;
		keys = (K[])new Comparable[M];
		vals = (V[]) new Comparable[M];
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

		if (N >= M/2)
			rehash(2*M); // double M (see text)
		int i;
		if(keyMayor==null || val.compareTo(valMayor)>0) {
			keyMayor=key;
			valMayor=val;
		}
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
			{
				vals[i]=val;
				return;
			}
		keys[i] = key;
		vals[i]= val;
		N++;
	}



	public V get(K key)
	{
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}


	public boolean contains(K key)
	{
		if(get(key)==null)
			return false;
		return true;
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
		ArregloDinamico<V> arrayKeys = new ArregloDinamico<V>(N);
		for(V v:vals)
			if(v!=null)
				arrayKeys.addLast((V) v);
		return arrayKeys;
	}

	public int getNRehash() {
		return nRehash;
	}

	@Override
	public V remove(K key) {
		if(!contains(key)) return null;
		int i= hash(key);
		while(!key.equals(keys[i]))
			i= (i+ 1) % M;
		keys[i] = null;
		vals[i] = null;
		i= (i+ 1) % M;
		while(keys[i] != null) {
			K keyToRedo= keys[i];
			V valToRedo= vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i= (i+ 1) % M;
		}
		N--;
		if(N > 0 && N == M/8)
			rehash(M/2);
		return null;
	}
	
	public K getMaxKey() {
		return keyMayor;
	}
}
