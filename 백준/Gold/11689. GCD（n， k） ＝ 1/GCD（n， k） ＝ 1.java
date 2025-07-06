import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long N, ans;
	static int maxVal;

	public static void main(String[] args) throws Exception {
		N = Long.parseLong(br.readLine());
		ans = N;
		maxVal = (int)Math.sqrt(N);

		for (int i = 2; i <= maxVal; i++) {
			if (N == 1)
				break;

			if (N % i != 0)
				continue;

			ans -= ans / i;

			while (N % i == 0) {
				N /= i;
			}
		}

		if (N != 1)
			ans -= ans / N;

		System.out.print(ans);
	}
}