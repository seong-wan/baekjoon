import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;// 물품의 수, 제한 무게
	static int memo[][];// [N][K]가 구하는 가치합의 최댓값
	static int item[][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		item = new int[N + 1][2];
		memo = new int[N + 1][K + 1];// 0은 더미

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			item[i][0] = w;
			item[i][1] = v;
		}

		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				if (item[i][0] <= w) {
					// 선택
					int sel = memo[i][w] = memo[i - 1][w - item[i][0]] + item[i][1];
					int nosel = memo[i - 1][w];
					memo[i][w] = Math.max(sel, nosel);

				} else {
					memo[i][w] = memo[i - 1][w];
				}

			}

		}
		System.out.println(memo[N][K]);

	}

}
