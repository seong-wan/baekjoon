import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, start, end;
	static String[] hammingCodes;
	static List<Integer>[] adlist;
	static StringTokenizer st;
	static int[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		hammingCodes = new String[N + 1];
		adlist = new List[N + 1];
		visit = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			hammingCodes[i] = br.readLine();
			adlist[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			String first = hammingCodes[i];
			for (int j = i + 1; j <= N; j++) {
				String second = hammingCodes[j];

				int distance = 0;

				for (int k = 0; k < first.length(); k++) {
					if (first.charAt(k) != second.charAt(k))
						distance++;
				}

				if (distance == 1) {
					adlist[i].add(j);
					adlist[j].add(i);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		bfs();

		search();

		System.out.println(sb);
	}

	static void search() {
		Deque<Integer> stack = new ArrayDeque<>();

		if (visit[end] == 0) {
			sb.append(-1);
			return;
		}

		int cur = end;

		while (true) {
			if (cur == -1)
				break;

			stack.push(cur);
			cur = visit[cur];
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
	}

	static void bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visit[start] = -1;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (Integer next : adlist[cur]) {
				if (visit[next] != 0)
					continue;

				visit[next] = cur;

				if (next == end)
					return;

				queue.add(next);
			}
		}
	}
}