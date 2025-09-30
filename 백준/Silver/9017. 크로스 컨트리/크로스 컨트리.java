import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N;
	static int[] cnt, score, fifth;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			cnt = new int[201];
			score = new int[201];
			fifth = new int[201];
			int ans = 0;
			int ansScore = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());
			int[] result = new int[N];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int team = Integer.parseInt(st.nextToken());

				cnt[team]++;
				result[i] = team;
			}

			for (int i = 1; i <= 200; i++) {
				if (cnt[i] != 6)
					cnt[i] = 0;
			}

			int point = 1;
			for (int i = 0; i < N; i++) {
				int team = result[i];

				if (cnt[team] > 2) {
					score[team] += point++;
					cnt[team]--;
				} else if (cnt[team] == 2) {
					fifth[team] = point++;
					cnt[team]--;
				} else if (cnt[team] == 1) {
					point++;
				}
			}

			for (int i = 1; i <= 200; i++) {
				if (score[i] == 0)
					continue;

				if (score[i] < ansScore) {
					ans = i;
					ansScore = score[i];
				} else if (score[i] == ansScore) {
					if (fifth[i] < fifth[ans])
						ans = i;
				}
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}
}