import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long K;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		int left = 0;
		int right = N / 2;

		while (left <= right) {
			long a = (left + right) >> 1;
			long b = N - a;

			long result = (a + 1) * (b + 1);

			if (result > K)
				right = (int)(a - 1);
			else if (result < K)
				left = (int)(a + 1);
			else {
				System.out.println("YES");
				return;
			}
		}

		System.out.println("NO");
	}
}