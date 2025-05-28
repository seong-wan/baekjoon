import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans;
	static int[] input;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		int temp = N;

		for (int i = N - 1; i >= 0; i--) {
			if (input[i] == temp)
				temp--;
			else
				ans++;
		}

		System.out.println(ans);
	}
}