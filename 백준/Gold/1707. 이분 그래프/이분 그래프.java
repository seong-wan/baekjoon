import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int V, E, K;
	static List<Integer>[] adlist;
	static boolean[][] visit;
	static boolean chk;

	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		for (int t = 0; t < K; t++) {
			chk = false;// 초기화
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adlist = new List[V + 1];
			visit = new boolean[V + 1][2];

			for (int i = 1; i <= V; i++) {
				adlist[i] = new ArrayList<>();

			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adlist[from].add(to);
				adlist[to].add(from);
			}
			for (int i = 1; i <= V; i++) {
				if (visit[i][0] || visit[i][1])
					continue;// 방문을 어떤 집합으로든 했다면 bfs를 다시 돌 필요가 없음
				bfs(i);// 연결되어 있지 않은 정점에서 다시 bfs로 이분그래프 확인
				if (chk) {
					sb.append("NO" + "\n");
					break;
				}
			}
			if (!chk)
				sb.append("YES" + "\n");
		}
		System.out.println(sb);
	}

	static void bfs(int start) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { start, 0 });
		visit[start][0] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i : adlist[cur[0]]) {
				if (visit[i][cur[1] % 2]) {// 같은 집합에서 방문한 적 있다면
					chk = true;
					return;
				}
				if (!visit[i][(cur[1] + 1) % 2]) {
					visit[i][(cur[1] + 1) % 2] = true;
					queue.add(new int[] { i, cur[1] + 1 });
				}
			}
		}
	}
}