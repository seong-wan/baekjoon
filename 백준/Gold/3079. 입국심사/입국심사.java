import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[] times = new int[100000];

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}

		long left = 1;
		long right = 1_000_000_000_000_000_000L;

		while (left <= right) {
			long mid = (left + right) >> 1;
			long cnt = 0;

			for (int i = 0; i < N; i++) {
				cnt += mid / times[i];

				if (cnt >= 1_000_000_000)
					break;
			}

			if (cnt >= M)
				right = mid - 1;
			else
				left = mid + 1;
		}

		System.out.print(left);
	}
}