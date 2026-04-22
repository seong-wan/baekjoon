import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, cnt;
	static int[] aCnt;
	static List<Integer>[] child;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e1 - e2);

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		aCnt = new int[N + 1];
		child = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			child[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			int signIdx = s.indexOf('>');
			int comIdx = s.indexOf(',');

			//승자가 1명
			if (signIdx < comIdx) {
				int winner = Integer.parseInt(s.substring(0, signIdx));

				int loser1 = Integer.parseInt(s.substring(signIdx + 1, comIdx));
				int loser2 = Integer.parseInt(s.substring(comIdx + 1));

				child[winner].add(loser1);
				child[winner].add(loser2);
				aCnt[loser1]++;
				aCnt[loser2]++;
			}
			//승자가 2명
			else {
				int winner1 = Integer.parseInt(s.substring(0, comIdx));
				int winner2 = Integer.parseInt(s.substring(comIdx + 1, signIdx));

				int loser = Integer.parseInt(s.substring(signIdx + 1));

				child[winner1].add(loser);
				child[winner2].add(loser);
				aCnt[loser] += 2;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (aCnt[i] == 0) {
				pq.add(i);
			}
		}

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(" ");
			cnt++;

			for (Integer next : child[cur]) {
				aCnt[next]--;

				if (aCnt[next] == 0) {
					pq.add(next);
				}
			}
		}

		System.out.print(cnt == N ? sb : 0);
	}
}
