import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static boolean[][] adlist = new boolean[26][26];
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';

			adlist[from][to] = true;
		}

		floyd();

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';

			if (adlist[from][to])
				sb.append("T");
			else
				sb.append("F");

			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void floyd() {
		for (int k = 0; k < 26; k++) {
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (i == k || j == k)
						continue;

					if (adlist[i][k] && adlist[k][j])
						adlist[i][j] = true;
				}
			}
		}
	}
}