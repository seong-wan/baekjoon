import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, P, X, ans;
	static String curFloor;
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

		curFloor = String.format("%0" + K + "d", X);

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			check(String.format("%0" + K + "d", i));
		}

		System.out.print(ans);
	}

	static void check(String temp) {
		int totalCount = 0;

		for (int i = 0; i < K; i++) {
			int before = curFloor.charAt(i) - '0';
			int after = temp.charAt(i) - '0';

			totalCount += count[before][after];
		}

		if (totalCount <= P)
			ans++;
	}
}