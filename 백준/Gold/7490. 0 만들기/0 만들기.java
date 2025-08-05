import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, N;
	static int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
	static char[] operators;
	static char[] choices = new char[] {' ', '+', '-'};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			operators = new char[N - 1];

			comb(0);

			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void comb(int idx) {
		if (idx == N - 1) {
			calc();
			return;
		}

		for (int i = 0; i < 3; i++) {
			operators[idx] = choices[i];
			comb(idx + 1);
		}
	}

	static void calc() {
		int front = nums[0];
		int idx = 0;

		while (idx <= N - 2) {
			if (operators[idx] != ' ')
				break;

			front *= 10;
			front += nums[idx + 1];
			idx++;
		}

		int back = 0;

		for (int i = idx; i < N - 1; i++) {
			char operator = operators[i];
			back = nums[i + 1];

			while (i < N - 2) {
				if (operators[i + 1] != ' ')
					break;

				i++;
				back *= 10;
				back += nums[i + 1];
			}

			if (operator == '-')
				front -= back;
			else if (operator == '+')
				front += back;

			back = 0;
		}

		if (front == 0) {
			check();
		}
	}

	static void check() {
		for (int i = 0; i < N - 1; i++) {
			sb.append(nums[i]).append(operators[i]);
		}

		sb.append(nums[N - 1]).append("\n");
	}
}