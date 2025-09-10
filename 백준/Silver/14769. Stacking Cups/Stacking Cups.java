import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Map<Integer, String> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();

			int aFirstValue = a.charAt(0) - '0';

			//숫자값이 아닌 경우
			if (aFirstValue < 0 || aFirstValue > 9)
				map.put(Integer.parseInt(b), a);
			else
				map.put(Integer.parseInt(a) / 2, b);
		}

		List<Integer> keys = new ArrayList<>(map.keySet());
		keys.sort((e1, e2) -> e1 - e2);

		for (int i = 0; i < N; i++) {
			sb.append(map.get(keys.get(i))).append("\n");
		}

		System.out.print(sb);
	}
}