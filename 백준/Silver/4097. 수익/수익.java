import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			long temp = Integer.parseInt(br.readLine());
			long ans = temp;

			for (int i = 1; i < N; i++) {
				int input = Integer.parseInt(br.readLine());
				if (temp < 0)
					temp = input;
				else
					temp += input;

				ans = Math.max(temp, ans);
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}
}