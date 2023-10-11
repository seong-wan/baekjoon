import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static long P = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(comb());
	}

	static long comb() {// 페르마 소정리 이용
		if (K == 0 || N == K)
			return 1L;
		return (fac(N) * power(fac(K), P - 2) % P * power(fac(N - K), P - 2) % P) % P;
	}

	static long fac(int num) {// 팩토리얼
		long result = 1;
		for (int i = 1; i <= num; i++) {
			result = result * i % P;
		}
		return result;
	}

	static long power(long num, long p) {// 분할정복을 이용한 거듭제곱
		long result = 1L;

		while (p > 0) {
			if (p % 2 == 1)
				result = result * num % P;
			p /= 2;
			num = num * num % P;
		}
		return result;
	}

}