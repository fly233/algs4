/******************************************************************************
 *  Compilation:  javac QuickFindUF.java
 *  Execution:  java QuickFindUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Quick-find algorithm.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class QuickFindUF {
	private int[] id;		// id[i] = component identifier of i
	private int count;	// number of componets

	public QuickFindUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		validate(p);
		return id[p];
	}

	private void validate(int p) {
		int N = id.length;
		if (p < 0 || p >= N) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
		}
	}

	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return id[p] == id[q];
	}

	public void union(int p, int q) {
		validate(p);
		validate(q);
		int pID = id[p];
		int qID = id[q];

		if (pID == qID) return;

		for (int i = 0; i < id.length; i++)
			if (id[i] == pID) id[i] = qID;
		count--;
	}

	public void printIDs() {
		String s = "";
		for (int i = 0; i < id.length; i++) {
			s = s + id[i] + " ";
		}

		StdOut.println(s);
	}

	public static void showCase() {
		int N = StdIn.readInt();
		//StdOut.println("N = " + N);
		QuickFindUF uf = new QuickFindUF(N);
		uf.printIDs();
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			//StdOut.println("read in : " + p);
			int q = StdIn.readInt();
			//StdOut.println("read in : " + q);
			StdOut.println("read in : " + p + " " + q);
			if (uf.connected(p, q)) {
				//StdOut.println(p + " and " + q + " is connected.");
				continue;
			}
			uf.union(p, q);
			//StdOut.println("connect " + p + " and " + q);
			//StdOut.println(p + " " + q);
			uf.printIDs();
		}
		StdOut.println(uf.count() + " components");
	}

	public static void analysCase() {
		int N = StdIn.readInt();
		//StdOut.println("N = " + N);
		QuickFindUF uf = new QuickFindUF(N);
		//uf.printIDs();
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			//StdOut.println("read in : " + p);
			int q = StdIn.readInt();
			//StdOut.println("read in : " + q);
			//StdOut.println("read in : " + p + " " + q);
			if (uf.connected(p, q)) {
				//StdOut.println(p + " and " + q + " is connected.");
				continue;
			}
			uf.union(p, q);
			//StdOut.println("connect " + p + " and " + q);
			StdOut.println(p + " " + q);
			uf.printIDs();
		}
		StdOut.println(uf.count() + " components");
	}

	public static void main(String[] args) {
		showCase();
		//analysCase();
	}

}
















