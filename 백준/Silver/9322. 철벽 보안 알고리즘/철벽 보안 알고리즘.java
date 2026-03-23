import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, n;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			Map<String, Integer> seq = new HashMap<>();
			String[] input = new String[n];
			String[] ans = new String[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				seq.put(st.nextToken(), i);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				input[i] = st.nextToken();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int tempSeq = seq.get(input[i]);

				ans[tempSeq] = st.nextToken();
			}

			for (int i = 0; i < n; i++) {
				sb.append(ans[i]).append(" ");
			}

			sb.append("\n");
		}

		System.out.print(sb);
	}
}