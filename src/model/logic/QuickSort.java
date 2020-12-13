/**
 * ESTA CLASE JUNTO A SUS MÉTODOS HAN SIDO TOMADOS Y MODIFICADOS DEL LIBRO 
 * 'Algorithhms 4th Edition by Robert Sedgewick, Kevin Wayne'.
 * Todas las librerías se pueden encontrar en https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/
 */
package model.logic;

import java.util.Comparator;
import java.util.Random;
public class QuickSort
{
    private static long seed;        // pseudo-random number generator seed
    private static Random random;    // pseudo-random number generator

    public static void shuffle(Comparable a[])
    {
    	seed = System.currentTimeMillis();
	    random = new Random(seed);
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n-i);     // between i and n-1
			Object temp = a[i];
			a[i] = a[r];
			a[r] = (Comparable) temp;
		}
    }
    
	private static int partition(Comparable[] a, int lo, int hi, Comparator c, boolean ascending)
	{ 
		int i = lo, j = hi+1; 
		Comparable v = a[lo]; 
		while (true)
		{
			while (less(a[++i], v, c, ascending)) if (i == hi) break;
			while (less(v, a[--j], c, ascending)) if (j == lo) break;
			if (i >= j) break;
			exchange(a, i, j);
		}
		exchange(a, lo, j); 
		return j; 
	}
	public static void sort(Comparable[] a, Comparator c, boolean ascending) {
	    shuffle(a);
		sort(a, 0, a.length - 1, c, ascending);
	}

	private static void sort(Comparable[] a, int lo, int hi, Comparator c, boolean ascending) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi, c, ascending);
		sort(a, lo, j-1, c, ascending); // Sort left part a[lo .. j-1].
		sort(a, j+1, hi, c, ascending); // Sort right part a[j+1 .. hi].
	}



	private static boolean less(Comparable a, Comparable b, Comparator c, boolean ascending){
		if(ascending) {
			return c==null?a.compareTo(b)<0:c.compare(a, b)<0;
		}
		return c==null?a.compareTo(b)>0:c.compare(a, b)>0;
	}

	private static void exchange(Comparable[] array, int i, int j)
	{
		Comparable temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	public static int uniform(int n) {
		if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
		return random.nextInt(n);
	}
	public static void shuffle(char a[])
    {
    	seed = System.currentTimeMillis();
	    random = new Random(seed);
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int r = i + uniform(n-i);     // between i and n-1
			char temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
    }
    
}
