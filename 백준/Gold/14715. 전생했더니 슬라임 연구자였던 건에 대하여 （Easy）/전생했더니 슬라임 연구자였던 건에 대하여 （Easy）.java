import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, cnt;
	static boolean[] isNotPrime = new boolean[1000001];

	public static void main(String[] args) throws Exception {
		init();

		N = Integer.parseInt(br.readLine());

		calc();

		System.out.print((int)Math.ceil(Math.log(cnt) / Math.log(2)));
	}

	static void calc() {
		for (int i = 2; i <= 1000000; i++) {
			if (isNotPrime[i])
				continue;

			while (N % i == 0) {
				cnt++;
				N /= i;
			}

			if (N == 1)
				break;
		}
	}

	static void init() {
		isNotPrime[0] = isNotPrime[1] = true;

		for (int i = 2; i <= Math.sqrt(1000000); i++) {
			if (isNotPrime[i])
				continue;

			for (int j = i * 2; j <= 1000000; j += i)
				isNotPrime[j] = true;
		}
	}
}
