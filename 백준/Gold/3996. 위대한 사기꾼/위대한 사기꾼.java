import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int k;
	static long N, ans = 1;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int cnt = 1;

		while (N > 0) {
			long value = N % k;

			if (cnt % 2 == 1) {
				ans += (long)(value * Math.pow(k, cnt / 2));
			} else {
				if (value >= 1)
					ans = (long)Math.max(ans, Math.pow(k, cnt / 2));
			}

			N /= k;
			cnt++;
		}

		System.out.print(ans);
	}
}