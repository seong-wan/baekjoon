import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, M;
	static StringBuilder sb = new StringBuilder();
	static Map<String, Map> root;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int cnt = 0;
			root = new HashMap<>();
			Map<String, Map> temp;

			//기존 폴더 제작
			for (int i = 0; i < N; i++) {
				temp = root;

				st = new StringTokenizer(br.readLine(), "/");

				while (st.hasMoreTokens()) {
					String dir = st.nextToken();

					if (!temp.containsKey(dir))
						temp.put(dir, new HashMap<>());

					temp = temp.get(dir);
				}
			}

			//폴더 추가 생성
			for (int i = 0; i < M; i++) {
				temp = root;

				st = new StringTokenizer(br.readLine(), "/");

				while (st.hasMoreTokens()) {
					String dir = st.nextToken();

					if (!temp.containsKey(dir)) {
						temp.put(dir, new HashMap<>());
						cnt++;
					}

					temp = temp.get(dir);
				}
			}

			sb.append("Case #").append(t).append(": ").append(cnt).append("\n");
		}

		System.out.print(sb);
	}
}
