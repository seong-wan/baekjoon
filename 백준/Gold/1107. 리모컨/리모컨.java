import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, ans;
	static boolean[] brokenKey = new boolean[10];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int key = Integer.parseInt(st.nextToken());
				brokenKey[key] = true;
			}
		}

		ans = Math.abs(N - 100);
		int diff = 0;
		while (diff <= ans) {
			boolean avail = false;

			if (check(N + diff)) {
				int length = Integer.toString(N + diff).length();
				ans = Math.min(ans, length + diff);

				avail = true;
			}

			if (N - diff >= 0 && check(N - diff)) {
				int length = Integer.toString(N - diff).length();
				ans = Math.min(ans, length + diff);

				avail = true;
			}

			if (avail)
				break;

			diff++;
		}

		System.out.print(ans);
	}

	static boolean check(int num) {
		String s = Integer.toString(num);
		for (int i = 0; i < s.length(); i++) {
			if (brokenKey[s.charAt(i) - '0'])
				return false;
		}
		return true;
	}
}