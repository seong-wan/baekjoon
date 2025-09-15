import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, L, max;
	static int[] restArea;
	static StringTokenizer st;
	static int[] term;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		restArea = new int[N + 2];
		term = new int[N + 1];

		restArea[0] = 0;
		restArea[1] = L;

		if (N != 0) {
			st = new StringTokenizer(br.readLine());

			for (int i = 2; i < N + 2; i++) {
				restArea[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(restArea);
		}

		for (int i = 0; i < N + 1; i++) {
			term[i] = restArea[i + 1] - restArea[i] - 1;
			max = Math.max(max, term[i] + 1);
		}

		while (max != 0) {
			int cnt = 0;

			for (int i = 0; i <= N; i++) {
				cnt += term[i] / max;
			}

			if (cnt <= M)
				max--;
			else
				break;
		}

		System.out.print(max + 1);
	}
}