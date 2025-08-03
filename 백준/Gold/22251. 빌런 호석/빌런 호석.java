import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, P, X, ans;
	static int[][] count = new int[][] {
		{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
		{4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
		{3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
		{3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
		{4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
		{3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
		{2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
		{3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
		{1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
		{2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
	};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			check(i);
		}

		System.out.print(ans);
	}

	static void check(int temp) {
		int totalCount = 0;
		int origin = X;

		for (int i = K - 1; i >= 0; i--) {
			int div = (int)Math.pow(10, i);

			int before = origin / div;
			int after = temp / div;

			totalCount += count[before][after];

			origin %= div;
			temp %= div;
		}

		if (totalCount <= P)
			ans++;
	}
}