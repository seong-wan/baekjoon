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
	static int N, Q, cnt;// 정점의 수, 질문의 수,볼 수 있는 동영상의 수
	static List<int[]>[] adlist;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		adlist = new List[N + 1];// 0은 더미

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<int[]>();
		} // 인접리스트 초기화

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, w });
			adlist[to].add(new int[] { from, w });
		}

		for (int i = 0; i < Q; i++) {
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			bfs(k, v);
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}

	static void bfs(int k, int v) {
		boolean[] visit = new boolean[N + 1];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { k, v });
		visit[v] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int ck = cur[0];
			int cv = cur[1];
			for (int[] to : adlist[cv]) {
				if (!visit[to[0]] && to[1] >= k) {//가중치가 k이상이고 방문하지 않은 곳으로만 이동
					queue.add(new int[] { k, to[0] });
					cnt++;
					visit[to[0]] = true;
				}
			}
		}

	}

}
