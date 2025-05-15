import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		if (N == 0)
			System.out.println(0 + " " + 0);
		else
			bfs();
	}

	static void bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, 0});
		int day = 0;
		boolean check = false;
		while (!queue.isEmpty()) {
			int size = queue.size();

			if (check) {
				System.out.println(day + " " + dp[N]);
				return;
			}

			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int length = cur[0];
				int totalWater = cur[1];
				int[] next = new int[3];

				next[0] = length + 1;
				next[1] = 3 * length;
				next[2] = length * length;

				for (int j = 0; j < 3; j++) {
					if (next[j] == N)
						check = true;

					if (j == 2 && length > 1000)
						continue;

					int nextWater = 2 * j + 1 + totalWater;

					if (next[j] <= N && dp[next[j]] > nextWater) {
						dp[next[j]] = nextWater;
						queue.add(new int[] {next[j], nextWater});
					}
				}
			}
			day++;
		}
	}
}