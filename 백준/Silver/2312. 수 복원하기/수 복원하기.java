import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			int max = (int)Math.sqrt(num);

			for (int j = 2; j <= max; j++) {
				int cnt = 0;
				while (num % j == 0) {
					cnt++;
					num /= j;
				}

				if (cnt != 0)
					sb.append(j).append(" ").append(cnt).append("\n");

				if (num == 1)
					break;
			}

			if (num != 1)
				sb.append(num).append(" ").append(1).append("\n");
		}

		System.out.print(sb);
	}
}
