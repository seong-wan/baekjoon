import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, ans;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };// 상,좌,하,우

	static int V;// 정점
	static PriorityQueue<Edge> pqueue = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

	// 서로소
	static int[] parent;

	static class Edge {
		int v1, v2, cost;

		public Edge(int v1, int v2, int cost) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력

		find();
//		for (int i = 0; i < N; i++) {
//
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	static void find() {
		int cnt = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					bfs(i, j, cnt);
					cnt++;
				}

			}
		}
		V = cnt - 2;// bfs 후 마지막 증가분+원래 2부터 시작 고려
		parent = new int[V + 1];
		makeset();

		// map[][]로부터 간선 정보 계산

		// 가로로 쭉
		hr();
		// 세로로 쭉
		vr();

		int count = 0;
		while (!pqueue.isEmpty()) {
			Edge e = pqueue.poll();
			// 사이클 여부
			if (union(e.v1, e.v2)) {// 선택해도 된다.
				count++;
				ans += e.cost;// 최소값 누적 계산
			}
			if (count == V - 1)
				break;
		}
		if (count != V - 1 || ans == 0)
			ans = -1;
		System.out.println(ans);
	}

	static void vr() {
		for (int i = 0; i < M; i++) {
			int prev = 0;
			int curr = 0;
			int v1 = 0;
			int v2 = 0;
			int cost = 0;

			// 옆으로 쭉 가면서
			for (int j = 0; j < N; j++) {
				curr = map[j][i];
				if (prev == 0 && curr != 0) {// 0->0이 아닌 좌표(바다->섬,최초 시작점-> 섬)
					if (v1 == 0)
						v1 = curr;
					else {
						v2 = curr;
						if (cost > 1) {
							// 간선 추가(v1,v2,cost)
							addEdge(v1 - 1, v2 - 1, cost);

						}
						v1 = v2;
						v2 = 0;
						cost = 0;
					}
				} else if (v1 != 0 && curr == 0) {// 섬에서 시작 아직 바다
					cost++;
				}
				prev = curr;
			}
		}

	}

	static void hr() {
		for (int i = 0; i < N; i++) {
			int prev = 0;
			int curr = 0;
			int v1 = 0;
			int v2 = 0;
			int cost = 0;

			// 옆으로 쭉 가면서
			for (int j = 0; j < M; j++) {
				curr = map[i][j];
				if (prev == 0 && curr != 0) {// 0->0이 아닌 좌표(바다->섬,최초 시작점-> 섬)
					if (v1 == 0)
						v1 = curr;
					else {
						v2 = curr;
						if (cost > 1) {
							// 간선 추가(v1,v2,cost)
							addEdge(v1 - 1, v2 - 1, cost);
						}
						v1 = v2;
						v2 = 0;
						cost = 0;
					}
				} else if (v1 != 0 && curr == 0) {// 섬에서 시작 아직 바다
					cost++;
				}
				prev = curr;
			}
		}

	}

	static void addEdge(int v1, int v2, int cost) {
		// 뒤져서 중복인 항목을 제거하는 것이 손해가 될 수도 있고 이득이 될 수도 있다.
		boolean same = false;
		for (Edge edge : pqueue) {// 같은 정점을 연결하는 간선이면 최소값으로 갱신
			if (edge.v1 == v1 && edge.v2 == v2) {
				same = true;
				edge.cost = Math.min(edge.cost, cost);
				break;
			}
		}
		if (!same)// 새로운 간선이면 추가
			pqueue.offer(new Edge(v1, v2, cost));

	}

	private static void bfs(int i, int j, int cnt) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { i, j });
		map[i][j] = cnt;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc) && map[nr][nc] == 1) {
					map[nr][nc] = cnt;
					queue.add(new int[] { nr, nc });
				}

			}
		}

	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M)
			return true;
		return false;
	}

	static void makeset() {
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
	}

	static int findset(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = findset(parent[x]);
	}

	static boolean union(int x, int y) {
		int px = findset(x);
		int py = findset(y);

		if (py == px)
			return false;// 사이클 존재
		if (px < py)
			parent[py] = px;
		else
			parent[px] = py;
		return true;
	}

}