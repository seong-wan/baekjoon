import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int q, ans;
	static Map<String, Integer> enterCnt = new HashMap<>();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		q = Integer.parseInt(br.readLine());

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			String cmd = st.nextToken();

			if (!enterCnt.containsKey(name))
				enterCnt.put(name, 0);

			int cnt = enterCnt.get(name);

			if (cmd.equals("+"))
				enterCnt.put(name, cnt + 1);
			else {
				if (cnt > 0)
					enterCnt.put(name, cnt - 1);
				else
					ans++;
			}
		}

		enterCnt.values().forEach(e -> ans += e);

		System.out.print(ans);
	}
}
