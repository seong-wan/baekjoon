import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans;
	static int[] input;
	static int[] diff;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		input = new int[N];
		diff = new int[N - 1];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N - 1; i++) {
			diff[i] = input[i + 1] - input[i];
		}

		int temp = diff[0];
		for (int i = 1; i < N - 1; i++) {
			temp = gcd(temp, diff[i]);
		}

		for (int i = 0; i < N - 1; i++) {
			ans += (diff[i] / temp) - 1;
		}

		System.out.println(ans);
	}

	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}