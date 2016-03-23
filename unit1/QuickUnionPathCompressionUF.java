import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionPathCompressionUF {
	private int[] id;
	private int count;

	public QuickUnionPathCompressionUF(int N) {
		count = N;
		id = new int[N];

		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public void union(int p, int q) {
		validate(p);
		validate(q);
		int qID = find(q);
		int pID = find(p);
		if (pID == qID) return;

		id[qID] = pID;
		count--;
	}

	public int find(int p) {
		validate(p);

		int root = p;
		while (id[root] != root) {
			root = id[root];
		}

		//path compression
		int newp = id[p];
		while (id[newp] != root) {
			p = id[newp];
			id[newp] = root;
			newp = id[p];

		}

		return root;
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
		int N = id.length;
		if (p < 0 || p >= N) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
		} 
	}

	public void printIDs() {
		String s = "";
		for (int i = 0; i < id.length; i++) {
			s = s + id[i] + " ";
		}

		StdOut.println(s);
	}

	private static void showCase() {
		int N = StdIn.readInt();
		//StdOut.println("N = " + N);
		QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(N);
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
		QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(N);
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