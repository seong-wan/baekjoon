import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[] input;
	static Map<Integer, List<int[]>> partSum;

	public static void main(String[] args) throws Exception {
		while (true) {
			N = Integer.parseInt(br.readLine());
			int ans = Integer.MIN_VALUE;

			if (N == 0)
				break;

			input = new int[N];
			partSum = new HashMap<>();

			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(br.readLine());
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;

					int sum = input[i] + input[j];

					if (!partSum.containsKey(sum))
						partSum.put(sum, new ArrayList<>());

					partSum.get(sum).add(new int[] {i, j});
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;

					int diff = input[j] - input[i];

					if (!partSum.containsKey(diff))
						continue;

					for (int[] index : partSum.get(diff)) {
						int a = index[0];
						int b = index[1];

						if (a != i && a != j && b != i && b != j) {
							ans = Math.max(ans, input[j]);
						}
					}
				}
			}

			sb.append(ans == Integer.MIN_VALUE ? "no solution" : ans).append("\n");
		}

		System.out.print(sb);
	}
}