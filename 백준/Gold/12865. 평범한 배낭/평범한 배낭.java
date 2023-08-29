import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;// 물품의 수, 제한 무게
	static int memo[];// [N][K]가 구하는 가치합의 최댓값

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		memo = new int[K + 1];// 0은 더미

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			for (int w1 = K; w1 >= w; w1--) {

				memo[w1] = Math.max(memo[w1], memo[w1 - w] + v);

			}
		}

		System.out.println(memo[K]);

	}

}
