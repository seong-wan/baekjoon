import java.util.Arrays;

public class Main {
	static int N, Q;

	static class Taste {
		int spicyness;
		int sugariness;

		public Taste(int spicyness, int sugariness) {
			this.spicyness = spicyness;
			this.sugariness = sugariness;
		}

	}

	static Taste[] tastes;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		Q = in.nextInt();

		tastes = new Taste[N];

		for (int i = 0; i < N; i++) {
			tastes[i] = new Taste(in.nextInt(), in.nextInt());
		}

		Arrays.sort(tastes, (e1, e2) -> e1.spicyness - e2.spicyness);

		for (int i = 0; i < Q; i++) {
			int cnt = 0;

			int fromSpicyness = in.nextInt();
			int toSpicyness = in.nextInt();
			int fromSugariness = in.nextInt();
			int toSugariness = in.nextInt();

			int underBound = Arrays.binarySearch(tastes, new Taste(fromSpicyness, 0),
				(e1, e2) -> e1.spicyness - e2.spicyness);
			int upperBound = Arrays.binarySearch(tastes, new Taste(toSpicyness, 0),
				(e1, e2) -> e1.spicyness - e2.spicyness);

			if (underBound < 0)
				underBound = -1 * underBound - 1;
			if (upperBound < 0)
				upperBound = -1 * upperBound - 2;

			for (int j = underBound; j <= upperBound; j++) {
				if (tastes[j].sugariness >= fromSugariness && tastes[j].sugariness <= toSugariness)
					cnt++;
			}

			sb.append(cnt).append("\n");
		}

		System.out.print(sb);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}