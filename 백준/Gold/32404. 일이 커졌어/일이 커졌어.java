import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());

		int multiplyNum = N / 2 + 1;
		int sumNum = multiplyNum - 1;

		for (int i = 1; i <= N; i++) {
			if (i % 2 != 0)
				sb.append(multiplyNum++).append(" ");
			else
				sb.append(sumNum--).append(" ");
		}

		System.out.print(sb);
	}
}