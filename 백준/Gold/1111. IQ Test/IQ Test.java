import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, a = 1, b;
	static int[] input;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		input = new int[N];

		if (N == 1) {
			System.out.println("A");
			return;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		if (N == 2) {
			if (input[0] == input[1]) {
				System.out.println(input[0]);
				return;
			}

			System.out.println("A");
			return;
		}

		int up = input[1] - input[2];
		int down = input[0] - input[1];

		if (down != 0) {
			if (up % down != 0) {
				System.out.println("B");
				return;
			}

			a = up / down;
			b = input[1] - input[0] * a;
		}

		for (int i = 2; i < N; i++) {
			if (input[i - 1] * a + b != input[i]) {
				System.out.println("B");
				return;
			}
		}

		System.out.println(input[N - 1] * a + b);
	}
}