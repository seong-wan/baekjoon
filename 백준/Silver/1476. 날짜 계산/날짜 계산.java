import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int E, S, M;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int ans = 1;

		while (true) {
			int A = (ans - 1) % 15 + 1;
			int B = (ans - 1) % 28 + 1;
			int C = (ans - 1) % 19 + 1;

			if (A == E && B == S && C == M) {
				System.out.println(ans);
				break;
			}
			ans++;
		}
	}
}