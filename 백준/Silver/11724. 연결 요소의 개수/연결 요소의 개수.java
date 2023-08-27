import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, count;
	static List<Integer>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adlist = new List[N + 1];
		visit = new boolean[N + 1];// 0은 더미
		for (int i = 0; i < N + 1; i++) {
			adlist[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adlist[from].add(to);
			adlist[to].add(from);
		} // 인접 리스트 입력
		find();
		System.out.println(count);
	}

	static void find() {
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {
				count++;
				dfs(i);
			}

		}
	}

	static void dfs(int n) {
		visit[n] = true;
		for (Integer next : adlist[n]) {
			if (!visit[next])
				dfs(next);
		}

	}

}
