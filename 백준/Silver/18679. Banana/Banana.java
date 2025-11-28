import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Map<String, String> map = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " = ");
			map.put(st.nextToken(), st.nextToken());
		}

		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			int cnt = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < cnt; j++) {
				sb.append(map.get(st.nextToken())).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}