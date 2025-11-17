import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] input;
	static StringBuilder sb = new StringBuilder();
	static boolean[] checked;

	public static void main(String[] args) throws Exception {
		input = br.readLine().toCharArray();
		checked = new boolean[input.length];

		solve(0);
		System.out.print(sb);
	}

	static void solve(int idx) {
		while (true) {
			int minValue = Integer.MAX_VALUE;
			int state = -1;
			for (int i = idx; i < input.length; i++) {
				if (checked[i])
					continue;

				if (input[i] < minValue) {
					minValue = input[i];
					state = i;
				}
			}

			if (state == -1)
				return;

			checked[state] = true;
			print();

			if ((state + 1) < input.length && !checked[state + 1])
				solve(state + 1);
		}
	}

	static void print() {
		for (int i = 0; i < input.length; i++) {
			if (checked[i])
				sb.append(input[i]);
		}
		sb.append("\n");
	}
}