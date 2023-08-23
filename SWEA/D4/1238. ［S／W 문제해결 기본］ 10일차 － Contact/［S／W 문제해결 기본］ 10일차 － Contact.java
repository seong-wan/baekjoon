import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, S;// 테스트 케이스 수,간선 수 ,시작점
	static Set<Integer>[] adlist = new Set[101];// 0은 더미
	static int[] visited;
	static int max_depth, ans;// 최대 깊이,최대 깊이 중 가장 큰 번호
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			visited = new int[101];// 방문 체크를 하면서 깊이를 체크 0은 더미
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= 100; i++) {
				adlist[i] = new HashSet<Integer>();

			} // 인접리스트(중복된 요소 제거 위해 set으로 구현)

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adlist[from].add(to);
			} // 인접 정점 정보 입력
			bfs(S);
			find();
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);

	}

	static void bfs(int n) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(n);
		visited[n] = 1;// 첫 시작의 깊이를 1로 설정
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (Integer i : adlist[cur]) {
				if (visited[i] == 0) {
					max_depth = visited[i] = visited[cur] + 1;//방문체크를 하면서 최대 깊이를 체크
					queue.add(i);
				}
			}
		}
	}

	static void find() {
		for (int i = 100; i >= 1; i--) {
			if (visited[i] == max_depth) {
				ans = i;
				break;
			}
		}
	}//최대 깊이에 도착한 정점 중 최대 번호를 찾음

}
