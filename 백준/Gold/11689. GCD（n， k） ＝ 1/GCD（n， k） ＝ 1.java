import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long N, ans = 1;
	static int maxVal;
	static boolean[] notP;
	static HashMap<Integer, Integer> primeFacto = new HashMap<>();

	public static void main(String[] args) throws Exception {
		N = Long.parseLong(br.readLine());
		maxVal = (int)Math.sqrt(N);

		notP = new boolean[maxVal + 1];
		calcP();

		for (int i = 2; i <= maxVal; i++) {
			if (notP[i])
				continue;

			while (N % i == 0) {
				N /= i;
				primeFacto.putIfAbsent(i, 0);
				primeFacto.put(i, primeFacto.get(i) + 1);
			}
		}

		if (N != 1)
			ans = N - 1;

		for (Integer p : primeFacto.keySet()) {
			int exp = primeFacto.get(p);
			long oilVal = (long)(Math.pow(p, exp) - Math.pow(p, exp - 1));

			ans *= oilVal;
		}

		System.out.print(ans);
	}

	static void calcP() {
		notP[0] = notP[1] = true;
		for (int i = 2; i <= maxVal; i++) {
			if (notP[i])
				continue;

			for (int j = i * 2; j <= maxVal; j += i) {
				notP[j] = true;
			}
		}
	}
}