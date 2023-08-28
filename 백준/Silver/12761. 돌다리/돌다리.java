import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int A, B, N, M;
	static int[] bridge = new int[100_001];// 돌다리 0~100000

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if (N == M)
			System.out.println(0);
		else {
			bfs();
			System.out.println(bridge[M] - 1);
		}
	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		bridge[N] = 1; // 처음을 1로 했으니 답에서 -1
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int nr = cur - 1;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur + 1;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur + A;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur + B;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur - A;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur - B;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur * A;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}
			nr = cur * B;
			if (cango(nr)) {
				bridge[nr] = bridge[cur] + 1;
				if (nr == M)
					return;
				queue.add(nr);
			}

		}
	}

	static boolean cango(int n) {
		if (n >= 0 && n <= 100_000 && bridge[n] == 0)
			return true;
		else
			return false;
	}

}
