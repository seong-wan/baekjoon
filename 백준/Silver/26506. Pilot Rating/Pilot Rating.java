import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans = Integer.MAX_VALUE;
	static int[] input;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(input);

		for (int i = 0; i < N / 2; i++) {
			int sum = input[i] + input[N - 1 - i];

			ans = Math.min(ans, sum);
		}

		System.out.print(ans);
	}
}