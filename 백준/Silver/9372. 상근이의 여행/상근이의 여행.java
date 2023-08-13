import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M;
	static List<Integer>[] adlist;
	static boolean[] visited;
	static int cnt;// 지나간 정점의 개수

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph_set();
			dfs(1);// 시작점을 1로 잡고 dfs
			sb.append(cnt - 1 + "\n");// cnt-1이 타고 다닌 비행기의 수
		}
		System.out.println(sb);
	}

	static void addEdge(int s, int d) {
		adlist[s].add(d);
		adlist[d].add(s);
	}// 양방향 간선 추가

	static void graph_set() throws IOException {
		cnt = 0;
		adlist = new ArrayList[N + 1];// 국가 번호가 1부터 시작하므로 배열 크기를 N+1로 설정
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		} // adlist 배열 안의 리스트 초기화;
		visited = new boolean[N + 1];// 국가 번호 1부터 방문 체크를 하기 위해

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			addEdge(s, d);
		}
	}

	static void dfs(int vertex) {
		visited[vertex] = true;
		cnt++;

		for (int n : adlist[vertex]) {
			if (!visited[n])
				dfs(n);

		}

	}
}
