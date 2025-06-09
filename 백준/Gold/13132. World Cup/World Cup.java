import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, B;
	static double M, P, R, ans;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(st.nextToken()) / 100;
		B = Integer.parseInt(st.nextToken());

		calc();

		System.out.print(ans * 100);
	}

	static void calc() throws IOException {
		Deque<double[]> queue = new ArrayDeque<>();
		queue.add(new double[] {1, 100});

		for (int i = 0; i < N; i++) {
			int size = queue.size();
			st = new StringTokenizer(br.readLine());
			P = Double.parseDouble(st.nextToken()) / 100;
			R = Double.parseDouble(st.nextToken());

			for (int j = 0; j < size; j++) {
				double[] cur = queue.poll();
				double betting = cur[1] * M;
				double rest = cur[1] - betting;

				double successP = cur[0] * P;
				double failP = cur[0] * (1 - P);
				double nextR = rest + betting * R;

				queue.add(new double[] {successP, nextR});

				if (rest > B)
					queue.add(new double[] {failP, rest});
			}
		}

		while (!queue.isEmpty()) {
			double[] cur = queue.poll();

			if (cur[1] > 100)
				ans += cur[0];
		}
	}
}