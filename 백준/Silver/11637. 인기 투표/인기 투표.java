import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int T, N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			int sum = 0;
			int max = 0;
			int winner = 0;
			boolean isWin = false;

			for (int i = 1; i <= N; i++) {
				int temp = Integer.parseInt(br.readLine());

				sum += temp;

				if (max == temp)
					isWin = false;
				else if (temp > max) {
					max = temp;
					winner = i;
					isWin = true;
				}
			}

			if (!isWin)
				sb.append("no winner");
			else {
				if (max > sum / 2)
					sb.append("majority winner ");
				else
					sb.append("minority winner ");

				sb.append(winner);
			}

			sb.append("\n");
		}

		System.out.print(sb);
	}
}