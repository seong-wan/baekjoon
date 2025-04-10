import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int from, to;
	static StringBuilder sb = new StringBuilder();
	static boolean[] notPrime;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());

		notPrime = new boolean[to + 1];
		checkPrime();

		for (int i = from; i <= to; i++) {
			if (!notPrime[i])
				sb.append(i).append("\n");
		}

		System.out.println(sb);
	}

	static void checkPrime() {
		int end = (int)Math.sqrt(to);
		notPrime[1] = true;

		for (int i = 2; i <= end; i++) {
			if (notPrime[i])
				continue;

			for (int j = 2 * i; j <= to; j += i) {
				notPrime[j] = true;
			}
		}
	}
}