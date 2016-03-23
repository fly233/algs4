import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
	private int[] id;
	private int count;

	public QuickUnionUF(int N) {
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

		int index = p;
		//int times = 0;
		while(id[index] != index 
			//&& times < (id.length+1)
			) {
			index = id[index];
			//times++;
		}

		return index;
	}

	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return find(p) == find(q);
	}

	public int count() {
		/*int count = 0;
		for (int i = 0; i < id.length; i++) {
			if (id[i] == i) count++;
		}*/
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
		QuickUnionUF uf = new QuickUnionUF(N);
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
		QuickUnionUF uf = new QuickUnionUF(N);
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