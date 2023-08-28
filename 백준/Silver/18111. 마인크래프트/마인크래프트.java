import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, B, time, max, ans, anstime = Integer.MAX_VALUE, min = Integer.MAX_VALUE;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);// 높이의 최대와 최소를 구함
			}
		}
		simul();
		System.out.println(anstime + " " + ans);
	}

	static void simul() {
		for (int i = max; i >= min; i--) {
			int inven = B;
			time = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if (map[j][j2] == i)
						continue;
					else if (map[j][j2] < i) {
						int recnt = i - map[j][j2];
						inven -= recnt;

						time += recnt;

					} else if (map[j][j2] > i) {
						int adcnt = map[j][j2] - i;
						inven += adcnt;
						time += 2 * adcnt;
					}
				}

			}
			if (inven < 0)
				continue;
			if (anstime > time) {
				ans = i;
				anstime = time;
			}
		}
	}

}
