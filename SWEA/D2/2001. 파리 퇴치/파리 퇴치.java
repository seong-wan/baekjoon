import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution
{static int[][] map;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = 0;
			int sum = 0;
			map = new int[N + 1][N + 1];
			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j - 1] + map[i - 1][j] - (map[i - 1][j - 1]);

				}

			} // 파리 영역
			for (int i = 1; i <= N - M + 1; i++) {
				for (int j = 1; j <= N - M + 1; j++) {
					sum = sum(i, j, i + M - 1, j + M - 1);
					max = Math.max(sum, max);
				}

			}
			System.out.printf("#" + t + " " + max + "\n");
		}

	}

	static int sum(int a, int b, int c, int d) {
		int ans = map[c][d] - map[c][b - 1] - map[a - 1][d] + map[a - 1][b - 1];
		return ans;
	}

}
