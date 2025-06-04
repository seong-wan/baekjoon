import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		boolean downside = false;
		for (int i = 1; i < N; i++) {
			if (!downside && input[i] <= input[i - 1]) {
				sb.append(i).append("\n");
				downside = true;
			}

			if (downside && input[i] >= input[i - 1])
				downside = false;
		}

		if (!downside)
			sb.append(N).append("\n");

		System.out.println(sb);
	}
}