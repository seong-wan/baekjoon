import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				br.readLine();

			}
			sb.append(N - 1 + "\n");

		}
		System.out.println(sb);

	}
}
