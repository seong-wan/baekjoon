import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static List<int[]>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adlist = new List[N + 1];
		visit = new boolean[N + 1];// 0은 더미
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		} // 인접 리스트 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, w });
		} // 인접 리스트 입력

		for (int i = 0; i < args.length; i++) {
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int desti = Integer.parseInt(st.nextToken());
		dijkstra(start, desti);
	}

	static void dijkstra(int s, int d) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] { s, 0 });
		visit[s] = true;
		while (!pq.isEmpty()) {
			int cur[] = pq.poll();
			int from = cur[0];
			int cc = cur[1];
			visit[from] = true;//확실하게 지나갔을 때 방문처리
			if (from == d) {
				System.out.println(cc);
				return;
			}
			for (int[] next : adlist[from]) {
				int to = next[0];
				int nc = next[1];
				if (!visit[to]) {
					pq.add(new int[] { to, cc + nc });
				}
			}
		}

	}

}