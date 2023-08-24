import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Edge {
		int v;
		double weight;

		public Edge(int v, double weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static double E, result;
	static int[] X, Y;
	static List<List<Edge>> adlist;
	static boolean[] visit;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(((e1, e2) -> e1.weight - e2.weight >= 0 ? 1 : -1));

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = 0;
			pq.clear();
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
			visit = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());

			} // X좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());

			} // Y좌표 입력
			E = Double.parseDouble(br.readLine());

			make_edge();

			prim();
			sb.append("#" + t + " " + Math.round(result) + "\n");

		}
		System.out.println(sb);

	}

	static void make_edge() {
		adlist = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adlist.add(new ArrayList<>());

		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				int from = i;
				int to = j;
				double w = E * (Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				adlist.get(from).add(new Edge(to, w));
				adlist.get(to).add(new Edge(from, w));
			}
		}
	}// 각 섬 간의 환경 부담금을 계산하여 간선 리스트에 넣음

	static void prim() {
		int cnt = 1; // 선택된 정점의 수
		visit[0] = true;// 시작 정점 visit(선택)
		pq.addAll(adlist.get(0));
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();// 현재 pq에 담긴 간선들 중 비용이 최소
			if (!visit[edge.v]) {
				visit[edge.v] = true;
				result += edge.weight;
				cnt++;
				if (cnt == N)
					break;
				pq.addAll(adlist.get(edge.v));
			}
		}
	}

}
