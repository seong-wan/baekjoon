import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;// 동기의 수,친구 관계 리스트의 길이
	static List<Integer>[] adlist;// 인접 리스트
	static boolean[] visited;// 방문 체크
	static int cnt, depth;// 지나간 정점의 개수,1번을 기준으로 1부터 시작하는 그래프 깊이
	static Queue<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph_set();
		bfs();// 1번의 깊이를 1로 잡고 그래프의 연결된 정점들을 깊이 3까지 너비우선탐색
		System.out.println(cnt - 1);// 1번 제외
	}

	static void addEdge(int s, int d) {
		adlist[s].add(d);
		adlist[d].add(s);

	}// 양방향 간선 추가

	static void graph_set() throws IOException {
		adlist = new ArrayList[N + 1];// 학번이 1부터 시작하므로 배열 크기를 N+1로 설정
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		} // adlist 배열 안의 리스트 초기화;
		visited = new boolean[N + 1];// 학번 1부터 방문 체크를 하기 위해

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			addEdge(s, d);
		}
	}

	static void bfs() {
		queue.add(1);
		visited[1] = true;
		while (!queue.isEmpty()) {
			if (depth == 2) {
				queue.poll();
				cnt++;
				continue;
			} // 깊이가 2일 경우 queue에서 남은 깊이 3의 정점들 빼는 것만 진행

			int qsize = queue.size();
			for (int i = 0; i < qsize; i++) {
				int vertex = queue.poll();
				cnt++;
				for (int n : adlist[vertex]) {
					if (!visited[n]) {
						visited[n] = true;
						queue.add(n);
					}

				}

			}
			depth++;

		}

	}

}
