import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, N;
	static Map<String, Integer> seq = new HashMap<>();
	static int[] parent, cnt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			make();
			int idx = 0;

			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (!seq.containsKey(a))
					seq.put(a, idx++);

				if (!seq.containsKey(b))
					seq.put(b, idx++);

				int aSeq = seq.get(a);
				int bSeq = seq.get(b);

				sb.append(union(aSeq, bSeq)).append("\n");
			}
		}

		System.out.print(sb);
	}

	static int union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parent[rootB] = rootA;
			cnt[rootA] += cnt[rootB];
		}

		return cnt[rootA];
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static void make() {
		int maxNum = 2 * N;

		parent = new int[maxNum];
		for (int i = 0; i < maxNum; i++) {
			parent[i] = i;
		}

		cnt = new int[maxNum];
		Arrays.fill(cnt, 1);
	}
}