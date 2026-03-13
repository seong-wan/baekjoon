import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long sum;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		long expect = (long)N * (N - 1) / 2;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sum += Long.parseLong(st.nextToken());
		}

		System.out.println(sum - expect);
	}
}