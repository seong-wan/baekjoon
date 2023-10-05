import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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

		dijkstra(N, 0);
	}

	static void dijkstra(int N, int cnt) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((n1, n2) -> n1[1] - n2[1]);// cnt로 시간을 따로 계산 시간이 적게 걸린 이동부터
																						// 처리
		pq.add(new int[] { N, cnt });
		visited[N] = true;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0];
			int time = cur[1];
			if (x == K) {
				System.out.println(time);
				return;
			} // K에 도달하면 bfs종료
			visited[x] = true;
			int nx = x * 2;
			if (nx >= 0 && nx <= Math.min(2 * K, 100000) && !visited[nx]) {
				pq.add(new int[] { nx, time });
			}
			nx = x - 1;
			if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
				pq.add(new int[] { nx, time + 1 });
			}
			nx = x + 1;
			if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
				pq.add(new int[] { nx, time + 1 });
			}

		}

	}

}