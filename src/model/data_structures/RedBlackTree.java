// ESTOS METODOS ME LOS INVENTE PERRI

package model.data_structures;


public class RedBlackTree<K extends Comparable<K>, V> implements TablaSimbolosOrdenada <K, V> 
{
	public final static boolean RED=true;
	public final static boolean BLACK=false;

	private NodeRedBlack root;


	public RedBlackTree() {
	}

	public boolean isRed(NodeRedBlack nrd) {
		if (nrd==null) return false;
		return nrd.getColor()==RED;
	}

	public NodeRedBlack getRoot() {
		return root;
	}



	@Override
	public void put(K key, V value) {
		root=put(root,key,value);
		root.setColor(BLACK);

	}

	public NodeRedBlack<K, ListaEncadenada<V>> put(NodeRedBlack nrd, K key, V val ) {
		if (nrd == null) {
			ListaEncadenada<V> lV = new ListaEncadenada<V>();
			lV.addLast(val);
			NodeRedBlack nuevo=new NodeRedBlack(key, lV, 1, RED);
			
			return nuevo;
		}
		int cmp = key.compareTo((K) nrd.getKey());
		if  (cmp < 0) 
			nrd.setLeft( put(nrd.getLeft(),  key, val));
		else if (cmp > 0) 
			nrd.setRight(put(nrd.getRight(), key, val));
		else {
			((ListaEncadenada)nrd.getValue()).addLast(val);
			
		}



		if (isRed(nrd.getRight()) && !isRed(nrd.getLeft()))    
			nrd = rotateLeft(nrd);
		if (isRed(nrd.getLeft())  &&  isRed(nrd.getLeft().getLeft())) 
			nrd = rotateRight(nrd);
		if (isRed(nrd.getRight())  &&  isRed(nrd.getLeft()))     
			flipColors(nrd);
		nrd.setSize(size(nrd.getLeft()) + size(nrd.getRight()) + 1);
		return nrd;
	}

	public NodeRedBlack rotateRight(NodeRedBlack nrd) {
		// TODO Auto-generated method stub
		NodeRedBlack x = nrd.getLeft();
		nrd.setLeft(x.getRight());
		x.setRight(nrd);
		x.setColor(x.getRight().getColor());
		x.getRight().setColor(RED);
		x.setSize(nrd.getSize());
		nrd.setSize(size(nrd.getLeft()) + size(nrd.getRight()) + 1);
		return x;
	}

	public NodeRedBlack rotateLeft(NodeRedBlack nrd) {
		// TODO Auto-generated method stub
		NodeRedBlack x = nrd.getRight();
		nrd.setRight(x.getLeft());
		x.setLeft(nrd);
		x.setColor(x.getLeft().getColor());
		x.getLeft().setColor(RED);
		x.setSize(nrd.getSize());
		nrd.setSize(1 + size(nrd.getLeft()) + size(nrd.getRight()));
		return x;
	}

	private NodeRedBlack balance(NodeRedBlack h) {
		// assert (h != null);

		if (isRed(h.getRight()))                      h = rotateLeft(h);
		if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
		if (isRed(h.getLeft()) && isRed(h.getRight()))     flipColors(h);

		h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
		return h;
	}

	public void flipColors(NodeRedBlack h) {
		h.setColor(!h.getColor());
		h.getLeft().setColor(!h.getLeft().getColor());
		h.getRight().setColor(!h.getRight().getColor());
	}

	@Override
	public V get(K key) {

		return get(key,root);
	}

	public V get(K key, NodeRedBlack nrd)
	{
		while (nrd != null) {
			int cmp = key.compareTo((K) nrd.getKey());
			if (cmp < 0) 
				nrd = nrd.getLeft();
			else if (cmp > 0) 
				nrd = nrd.getRight();
			else              
				return (V) nrd.getValue();
		}
		return null;
	}

	public NodeRedBlack getNodeRedBlack(K key) {
		NodeRedBlack x=root;
		while(x!=null) {
			int cmp=key.compareTo((K) x.getKey());
			if(cmp<0) {
				x=x.getLeft();
			}else if(cmp>0) {
				x=x.getRight();
			}else {
				return x;
			}
		}
		return null;
	}

	@Override
	public int getHeight(K key) {
		int hK = height(key);
		if(hK==-1)
			return -1;
		return height()-hK;
	}


	public int height(K key) {
		NodeRedBlack x=getNodeRedBlack(key);
		return (x==null)? -1:getHeightNodeRedBlack(x);
	}

	public int getHeightNodeRedBlack(NodeRedBlack x) {
		if (x == null) return -1;
		return 1+Math.max(getHeightNodeRedBlack(x.getLeft()), getHeightNodeRedBlack(x.getRight()));
	}

	@Override
	public int height() {
		return isEmpty()?-1:getHeightNodeRedBlack(root);
	}

	@Override
	public K min() {
		if(isEmpty()) {
			return null;
		}
		NodeRedBlack x=root;
		while(x.getLeft()!=null) {
			x=x.getLeft();
		}
		return (K) x.getKey();
	}

	@Override
	public K max() {
		if(isEmpty()) {
			return null;
		}
		NodeRedBlack x=root;
		while(x.getRight()!=null) {
			x=x.getRight();
		}
		return (K) x.getKey();
	}

	@Override
	public boolean contains(K key) {
		NodeRedBlack x=getNodeRedBlack(key);
		return x!=null;
	}

	@Override
	public boolean isEmpty() {
		return root==null;
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		return root.getSize();
	}

	public int size(NodeRedBlack x) {
		if (x == null) return 0;
		else return x.getSize();
	}

	@Override
	public Lista<K> keySet() {
		Lista<K> listaNueva=new ListaEncadenada<>();
		if(isEmpty()) {
			return listaNueva;
		}
		root.inorden(listaNueva);
		return listaNueva;
	}


	public void range(NodeRedBlack x, Lista lista, K init, K end, boolean k) {
		if(x==null)
			return;
		int compLow = init.compareTo((K) x.getKey()); 
		int compHigh = end.compareTo((K) x.getKey()); 
		if (compLow < 0 ) range(x.getLeft(), lista, init, end, k);

		if(k) {
			if (compLow <= 0 && compHigh >= 0)  lista.addLast((K) x.getKey()); 
		}
		else {
			if (compLow <= 0 && compHigh >= 0) lista.addLast((V) x.getValue()); 
		}
		if (compHigh > 0 ) range(x.getRight(), lista, init,end, k);
	}

	@Override
	public Lista<K> keysInRange(K init, K end) {
		Lista<K> listaNueva=new ListaEncadenada<>();
		if(isEmpty())
			return listaNueva;
		range(root, listaNueva, init, end, true);
		return listaNueva;
	}



	public Lista<V> valuesInRange(K init, K end) {
		Lista<V> listaNueva=new ListaEncadenada<V>();
		if(isEmpty())
			return listaNueva;
		range(root, listaNueva, init, end, false);
		return listaNueva;
	}

	

}