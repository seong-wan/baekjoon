import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int M, idx = 1;
	static int N, K;
	static long P = 1_000_000_007;
	static long fac[] = new long[4_000_001];

	public static void main(String[] args) throws Exception {
		fac = new long[4000001];
		fac[0] = 1L;
		fac[1] = 1L;
		M = Integer.parseInt(br.readLine());
		makefac();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			sb.append(comb() + "\n");
		}
		System.out.println(sb);
	}

	static long comb() {// 페르마 소정리 이용
		if (K == 0 || N == K)
			return 1L;
		return (fac(N) * power(fac(K), P - 2) % P * power(fac(N - K), P - 2) % P) % P;
	}

	static void makefac() {
		fac[0] = 1L;
		for (int i = 1; i <= 4_000_000; i++) {
			fac[i] = (fac[i - 1] * i) % P;
		}
	}

	static long fac(int num) {// 팩토리얼
		return fac[num];
	}

	static long power(long num, long p) {// 분할정복을 이용한 거듭제곱
		long result = 1L;

		while (p > 0) {
			if ((p & 1) == 1)
				result = result * num % P;
			p >>= 1;
			num = num * num % P;
		}
		return result;
	}

}