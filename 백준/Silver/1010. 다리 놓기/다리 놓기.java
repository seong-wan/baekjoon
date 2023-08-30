import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		int[][] memoi = new int[31][31];

		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j <= 30; j++) {
				if (j == 0 || i == j)
					memoi[i][j] = 1;
				else
					memoi[i][j] = (memoi[i - 1][j - 1] + memoi[i - 1][j]);
			}
		}
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			sb.append(memoi[N][M] + "\n");

		}
		System.out.println(sb);
	}

}
