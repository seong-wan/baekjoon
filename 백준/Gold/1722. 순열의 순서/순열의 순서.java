import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		check = new boolean[N + 1];
		int cmd = Integer.parseInt(st.nextToken());

		if (cmd == 1) {
			long k = Long.parseLong(st.nextToken()) - 1;

			int start = 1;

			for (int i = 1; i <= N; i++) {
				long subCount = factorial(N - i);

				long div = k / subCount;
				k %= subCount;

				if (div == 0) {
					sb.append(start).append(" ");

					for (int j = start + 1; j <= N; j++) {
						if (!check[j]) {
							start = j;
							break;
						}
					}
				} else {
					int temp = 0;
					for (int j = start + 1; j <= N; j++) {
						if (!check[j]) {
							temp++;

							if (temp == div) {
								sb.append(j).append(" ");
								check[j] = true;
								break;
							}
						}
					}
				}
			}
		} else {
			int[] input = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			long ans = 1;
			int start = 1;
			for (int i = 1; i <= N; i++) {
				if (start == input[i]) {
					for (int j = start + 1; j <= N; j++) {
						if (!check[j]) {
							start = j;
							break;
						}
					}
				} else {
					int temp = 0;
					for (int j = start + 1; j <= input[i]; j++) {
						if (!check[j])
							temp++;
					}

					check[input[i]] = true;

					ans += temp * factorial(N - i);
				}
			}

			sb.append(ans);
		}

		System.out.println(sb);
	}

	static long factorial(int num) {
		long result = 1;

		for (int i = num; i >= 1; i--) {
			result *= i;
		}

		return result;
	}
}