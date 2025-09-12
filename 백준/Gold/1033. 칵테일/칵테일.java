//각 재료들의 무게를 1로 잡고 각각의 집합들을 합칠 때마다 비율을 곱해가며 최소공배수를 구함
//무게의 최소값을 구하기 위해 각 재료의 무게는 gcd로 나눔
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static int[] parent;
	static List<Integer>[] childList;
	static int[] weights;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		make();

		for (int i = 0; i < N - 1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int p = in.nextInt();
			int q = in.nextInt();

			union(a, b, p, q);
		}

		int gcd = gcd(weights[0], weights[1]);
		for (int i = 2; i < N; i++) {
			gcd = gcd(gcd, weights[i]);
		}

		for (int i = 0; i < N; i++) {
			System.out.print(weights[i] / gcd + " ");
		}
	}

	static void union(int a, int b, int p, int q) {
		int aRoot = find(a);
		int bRoot = find(b);

		int aMultiple = weights[b] * p;
		int bMultiple = weights[a] * q;

		calc(aRoot, aMultiple);
		calc(bRoot, bMultiple);

		parent[bRoot] = aRoot;
		childList[aRoot].add(bRoot);
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static void calc(int num, int multiple) {
		weights[num] *= multiple;

		for (int child : childList[num]) {
			calc(child, multiple);
		}
	}

	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static void make() {
		parent = new int[N];
		childList = new List[N];
		weights = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
			childList[i] = new ArrayList<>();
			weights[i] = 1;
		}
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