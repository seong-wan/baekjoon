import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int parents[];
	static Edge[] edgeList;
	static int V, E;

	static void make() {
		parents = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {// 0은 더미
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		Arrays.sort(edgeList, (e1, e2) -> e1.weight - e2.weight);// 간선 가중치 기준 오름차순 정렬

		make();

		int result = 0;// MST 비용
		int count = 0;// 연결된 간선 개수
		
        for (Edge e : edgeList) {
			
            if (union(e.from, e.to)) {
                if (count++ == V - 2)
					break;
				result += e.weight;
				
			}

		}
		System.out.println(result);
	}

}
