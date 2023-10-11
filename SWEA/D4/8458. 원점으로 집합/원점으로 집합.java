import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N;
	static int[] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			boolean even = false;
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			input = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				input[i] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
			} // 입력값 받기

			if (input[0] % 2 == 0) {
				even = true;
			}
			for (int i = 1; i < N; i++) {
				if (even) {
					if (input[i] % 2 != 0) {
						sb.append(-1).append("\n");
						break;
					}
				} else if (!even) {
					if (input[i] % 2 != 1) {
						sb.append(-1).append("\n");
						break;
					}
				}
				if (i == N - 1)
					find();

			}

		}
		System.out.println(sb);

	}

	static void find() {
		Arrays.sort(input);
		int num = input[N - 1];
		int sum = 0;
		int i = 0;
		while (true) {
			sum += i;
			if (num == sum) {
				sb.append(i).append("\n");
				return;
			} else if (sum > num) {
				if ((sum - num) % 2 == 0) {
					sb.append(i).append("\n");
					return;
				}
			}

			i++;
		}

	}

}