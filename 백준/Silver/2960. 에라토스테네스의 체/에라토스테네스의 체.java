import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, seq;
	static boolean[] nums;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new boolean[N + 1];

		for (int i = 2; i <= N; i++) {
			if (nums[i])
				continue;

			seq++;
			if (seq == K) {
				System.out.println(i);
				return;
			}

			nums[i] = true;
			int multi = 2;
			int temp = i * multi;

			while (temp <= N) {
				if (!nums[temp]) {
					seq++;
					nums[temp] = true;

					if (seq == K) {
						System.out.println(temp);
						return;
					}
				}

				multi++;
				temp = i * multi;
			}
		}
	}
}