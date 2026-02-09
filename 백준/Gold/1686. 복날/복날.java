import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int v, m, idx = 2, limit;
	static double[][] points = new double[1002][2];
	static StringTokenizer st;
	static List<Integer>[] adlist;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		limit = v * m * 60;

		//출발 지점
		st = new StringTokenizer(br.readLine());
		points[0][0] = Double.parseDouble(st.nextToken());
		points[0][1] = Double.parseDouble(st.nextToken());

		//도착지점
		st = new StringTokenizer(br.readLine());
		points[1][0] = Double.parseDouble(st.nextToken());
		points[1][1] = Double.parseDouble(st.nextToken());

		String input = "";
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			st = new StringTokenizer(input);
			points[idx][0] = Double.parseDouble(st.nextToken());
			points[idx++][1] = Double.parseDouble(st.nextToken());
		}

		adlist = new List[idx];
		for (int i = 0; i < idx; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < idx; i++) {
			for (int j = i + 1; j < idx; j++) {
				double distance = Math.sqrt(
					Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));

				if (distance > limit)
					continue;

				adlist[i].add(j);
				adlist[j].add(i);
			}
		}

		int ans = dijk();

		if (ans == -1)
			System.out.print("No.");
		else
			System.out.print("Yes, visiting " + ans + " other holes.");
	}

	static int dijk() {
		boolean[] visit = new boolean[idx];
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] {0, 0}); //노드 번호, 중간 개수

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cnt = cur[1];

			if (visit[node])
				continue;

			visit[node] = true;

			//시작 지점과 마지막 지점 카운트 제외
			if (node == 1)
				return cnt - 1;

			for (Integer next : adlist[node]) {
				if (visit[next])
					continue;

				pq.add(new int[] {next, cnt + 1});
			}
		}

		return -1;
	}
}