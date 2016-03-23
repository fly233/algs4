import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
	// data[i][0] : parent;
	// data[i][1] : 树的大小;
	private int[][] data;
	private int count;

	public WeightedQuickUnionUF(int N) {
		count = N;
		data = new int[N][2];
		for (int i = 0; i < N; i++) {
			data[i][0] = i;
			data[i][1] = 0;
		}
	}

	public void union(int p, int q) {
		validate(p);
		validate(q);
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot) return;

		if (data[pRoot][1] > data[qRoot][1]) {
			data[qRoot][0] = pRoot;
			
			//data[pRoot][1] = data[qRoot][1] + 1;
		} else if (data[pRoot][1] < data[qRoot][1]) {
			data[pRoot][0] = qRoot;
			data[qRoot][1] = data[pRoot][1] + 1;
		} else {
			data[qRoot][0] = pRoot;
			data[pRoot][1] = data[qRoot][1] + 1;
		}
		
		count--;
	}

	public int find(int p) {
		validate(p);
		while (data[p][0] != p) {
			p = data[p][0];
		}
		return p;
	}

	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return find(p) == find(q);
	}

	public int count() {
		return count;
	}

	private void validate(int p) {
		if (p < 0 || p > (data.length-1)) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (data.length-1));
		}
	}
	
	public void printIDs() {
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < data.length; i++) {
			s1 = s1 + data[i][0] + " ";
			s2 = s2 + data[i][1] + " ";
		}

		StdOut.println(s1);
		StdOut.println(s2);
		StdOut.println("+++++++++++++++");
	}

	private static void showCase() {
		int N = StdIn.readInt();
		//StdOut.println("N = " + N);
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
	}
}