import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());

		sb.append(N);
		for (int i = 1; i < N; i++) {
			sb.append(" ").append(i);
		}

		System.out.print(sb);
	}
}