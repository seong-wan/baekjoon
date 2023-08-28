import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, count, ans, min = Integer.MAX_VALUE;
	static Set<Integer>[] adlist;
	static int[] visit;// 방문체크용 유저마다 계산 시 초기화

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adlist = new Set[N + 1];// 0은 더미
		for (int i = 1; i <= N; i++) {
			adlist[i] = new HashSet<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adlist[from].add(to);
			adlist[to].add(from);
		} // 인접 리스트
		cal();
		System.out.println(ans);

	}

	static void cal() {
		for (int i = 1; i <= N; i++) {
			visit = new int[N + 1];// 0은 더미 유저마다 초기화
			count = 0;
			bfs(i);
			if (min > count) {// 베이컨의 수가 적으면 그 때의 유저로 갱신
				min = count;
				ans = i;
			} else if (min == count) {// 베이컨 수가 같으면 적은 번호의 유저로 갱신
				ans = Math.min(ans, i);
			}
		}
	}

	static void bfs(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(num);
		visit[num] = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (Integer next : adlist[cur]) {
				if (visit[next] == 0) {
					queue.add(next);
					visit[next] = visit[cur] + 1;
					count += visit[cur];// 본인부터 1로 시작하므로 -1이 되어 있는 visit[cur]값을 더함
				}
			}
		}
	}

}
