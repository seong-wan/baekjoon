import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] memoi = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			memoi[i][0] = 1;
			memoi[N][N] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N - 1; j++) {
				memoi[i][j] = (memoi[i - 1][j - 1] + memoi[i - 1][j]) % 10007;
			}
		}
		System.out.println(memoi[N][K]);
	}

}
