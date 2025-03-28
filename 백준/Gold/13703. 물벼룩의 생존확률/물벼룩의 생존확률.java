import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int k, n;
	static long ans;
	static long[][] check;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		//바로 잡아먹히는 상태
		if (k == 0) {
			System.out.println(0);
			return;
		}

		check = new long[n + k][n + 1];
		check[n][0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < n + k; j++) {
				if (j != 0)
					check[j][i] += check[j - 1][i - 1];

				if (j != n + k - 1)
					check[j][i] += check[j + 1][i - 1];
			}
		}

		for (int i = 0; i < n + k; i++) {
			ans += check[i][n];
		}

		System.out.println(ans);
	}
}