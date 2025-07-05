import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, bitMax;
	static long down;
	static String[] input;
	static int[] modular;
	static int[] tenMod = new int[51];
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		input = new String[N];
		modular = new int[N];
		bitMax = (int)(Math.pow(2, N));

		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
		}

		K = Integer.parseInt(br.readLine());
		dp = new long[bitMax][K];
		dp[0][0] = 1;

		calcMod();
		calcTenMod();
		calcDown();

		for (int i = 0; i < bitMax; i++) {
			for (int j = 0; j < K; j++) {
				if (dp[i][j] == 0)
					continue;
				for (int k = 0; k < N; k++) {
					if ((i & (1 << k)) == 0) {
						int nextBit = i | (1 << k);
						int nextMod = (j * tenMod[input[k].length()] + modular[k]) % K;
						dp[nextBit][nextMod] += dp[i][j];
					}
				}
			}
		}

		long gcd = gcd(down, dp[bitMax - 1][0]);

		System.out.print(dp[bitMax - 1][0] / gcd + "/" + down / gcd);
	}

	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static void calcDown() {
		down = 1;
		for (int i = 2; i <= N; i++) {
			down *= i;
		}
	}

	static void calcMod() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				modular[i] = (modular[i] * 10 + input[i].charAt(j) - '0') % K;
			}
		}
	}

	static void calcTenMod() {
		tenMod[0] = 1;
		for (int i = 1; i <= 50; i++) {
			tenMod[i] = (tenMod[i - 1] * 10) % K;
		}
	}
}