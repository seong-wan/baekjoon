import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), ":");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int gcd = GCD(N, M);

		System.out.print(N / gcd + ":" + M / gcd);
	}

	static int GCD(int a, int b) {
		if (b == 0)
			return a;

		return GCD(b, a % b);
	}
}