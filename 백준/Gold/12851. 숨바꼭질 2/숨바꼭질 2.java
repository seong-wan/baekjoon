import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[200001];

		if (N == K)
			System.out.println(0 + "\n" + 1);
		else
			bfs();

	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		Set<Integer> temp = new HashSet<>();
		queue.add(N);
		visit[N] = true;
		int cnt = 0;
		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			temp.clear();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				int[] next = new int[3];
				next[0] = cur - 1;
				next[1] = cur + 1;
				next[2] = 2 * cur;
				for (int j = 0; j < 3; j++) {
					if (next[j] == K)
						count++;
					if (cango(next[j])) {
						temp.add(next[j]);
						queue.add(next[j]);
					}
				}
			}
			for (Integer i : temp) {
				visit[i] = true;
			}
			cnt++;

			if (visit[K]) {
				System.out.println(cnt);
				System.out.println(count);
				return;
			}

		}
	}

	static boolean cango(int next) {
		if (next >= 0 && next <= 100000 && !visit[next])
			return true;
		return false;
	}

}