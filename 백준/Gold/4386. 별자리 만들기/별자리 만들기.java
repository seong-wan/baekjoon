import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
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
	static int n;
	static int parents[];
	static List<Edge> edgeList;
	static double[][] star;

	static void make() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
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
		n = Integer.parseInt(br.readLine());
		make_edge();
		make();

		double result = 0;// MST 비용
		int count = 0;// 연결된 간선 개수
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {
				result += e.weight;
				if (++count == n - 1)
					break;
			}

		}
		System.out.println(result);

	}

	static void make_edge() throws Exception {
		edgeList = new ArrayList<>();
		star = new double[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			star[i][0] = x;
			star[i][1] = y;
		} // 별자리 좌표 입력 받기

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				double dt = Math.sqrt(Math.pow(star[i][0] - star[j][0], 2) + Math.pow(star[i][1] - star[j][1], 2));
				int from = i;
				int to = j;
				edgeList.add(new Edge(from, to, dt));// 별자리마다의 거리를 계산하고 간선리스트에 넣음
			}
		}

		Collections.sort(edgeList, (e1, e2) -> e1.weight - e2.weight >= 0 ? 1 : -1);// 별자리 거리 기준 오름차순 정렬

	}
}
