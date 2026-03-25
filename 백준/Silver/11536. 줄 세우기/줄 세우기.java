import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static String[] input, sort;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		input = new String[N];
		sort = new String[N];

		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
			sort[i] = input[i];
		}

		Arrays.sort(sort);

		boolean isAsc = true, isDesc = true;

		for (int i = 0; i < N; i++) {
			if (!isAsc && !isDesc) {
				break;
			}

			if (!input[i].equals(sort[N - 1 - i])) {
				isDesc = false;
			}

			if (!input[i].equals(sort[i])) {
				isAsc = false;
			}
		}

		if (isAsc)
			System.out.print("INCREASING");
		else if (isDesc)
			System.out.print("DECREASING");
		else
			System.out.print("NEITHER");
	}
}