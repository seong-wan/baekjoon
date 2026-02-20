import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int min = K * (K + 1) / 2;

		if (N < min) {
			System.out.print(-1);
			return;
		}

		int remain = N - min;

		if (remain % K == 0)
			System.out.print(K - 1);
		else
			System.out.println(K);
	}
}