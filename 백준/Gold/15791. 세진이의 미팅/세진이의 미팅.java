import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static long P = 1000000007;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		System.out.println(comb(N, M));

	}

	static long comb(int N, int M) {
		if (N == M || M == 0)
			return 1;

		return fac(N) * power((fac(M) * fac(N - M) % P), P - 2) % P;
	}

	static long fac(int N) {
		long result = 1;
		if (N <= 1)
			return result;
		for (int i = 2; i <= N; i++) {
			result = i * result % P;
		}
		return result;
	}

	static long power(long num, long e) {// 거듭제곱
		long result = 1;

		while (e > 0) {
			if ((e & 1) == 1)
				result = result * num % P;
			e >>= 1;
			num = num * num % P;
		}
		return result;
	}
}