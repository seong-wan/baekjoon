import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, R, cnt = 1;
	static List<Integer>[] adlist;
	static int[] visited;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		visited = new int[N + 1];// 0은 더미
		adlist = new List[N + 1];// 0은 더미

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<Integer>();

		} // adlist 배열 안의 리스트들 초기화
		add_Edge();
		bfs(R);
		for (int i = 1; i <= N; i++) {
			sb.append(visited[i] + "\n");// 방문 배열 안에 있는 방문 순서를 출력
			// 방문하지 않았을 때는 배열 안의 값 0이 그대로 출력
		}
		System.out.println(sb);

	}

	static void add_Edge() throws Exception {
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adlist[from].add(to);
			adlist[to].add(from);

		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(adlist[i]);// 정점 번호가 작은 순서대로 방문하므로 정렬
		}

	}// 간선 정보 입력 받고 그래프에 추가

	static void bfs(int n) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(n);
		visited[n] = cnt++;// 시작 정점의 방문 순서를 1로 설정
		while (!queue.isEmpty()) {
			int x = queue.poll();
//			System.out.println(x);
			for (int i = 0; i < adlist[x].size(); i++) {

				int nx = adlist[x].get(i);
				if (visited[nx] == 0) {
					queue.add(nx);
					visited[nx] = cnt++;// 방문 순서를 체크하면서 방문 확인

				}

			}
		}

	}
}
