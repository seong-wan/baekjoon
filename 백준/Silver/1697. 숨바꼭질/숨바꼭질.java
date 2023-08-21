import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;// 수빈이 위치, 동생 위치

	static boolean[] visited = new boolean[100_001];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());// 수빈이와 동생 위치 입력

		bfs(N);
//		System.out.println(visited[K]);
	}

	static void bfs(int N) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(N);
		visited[N] = true;// 시작점을 1로 시작 결과에서 -1해줘야 함
		int depth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int x = queue.poll();
				if (x == K) {
					System.out.println(depth);
					return;
				} // K에 도달하면 bfs종료
				int nx = x + 1;

				if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
					visited[nx] = true;
					queue.add(nx);
				}
				nx = x - 1;
				if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
					visited[nx] = true;
					queue.add(nx);
				}
				nx = x * 2;
				if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
					visited[nx] = true;
					queue.add(nx);
				}

			}
			depth++;

		}

	}

}
