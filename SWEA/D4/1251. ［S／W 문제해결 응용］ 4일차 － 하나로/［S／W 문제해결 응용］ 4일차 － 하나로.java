import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static class Edge {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static double E;
	static int[] X, Y;
	static int parents[];
	static List<Edge> edgeList;
	static int V, Edge;

	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;// 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;

	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
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
			make();

			double result = 0;// MST 비용
			int count = 0;// 연결된 간선 개수
			for (Edge e : edgeList) {
				if (union(e.from, e.to)) {
					result += e.weight;
					if (++count == N - 1)
						break;
				}

			}
			sb.append("#" + t + " " + Math.round(result) + "\n");

		}
		System.out.println(sb);

	}

	static void make_edge() {
		edgeList = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				int from = i;
				int to = j;
				double w = E * (Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
				edgeList.add(new Edge(from, to, w));
			}
		}
		Collections.sort(edgeList, (e1, e2) -> e1.weight - e2.weight >= 0 ? 1 : -1);
		// 가중치 기준 오름차순으로 정렬
	}// 각 섬 간의 환경 부담금을 계산하여 간선 리스트에 넣음

}
