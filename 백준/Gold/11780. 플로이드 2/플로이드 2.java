import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n, m, INF = 1000000;
	static int[][] mat;
	static List<Integer>[][] route;// 경로 체크용 리스트

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		mat = new int[n + 1][n + 1];
		route = new List[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(mat[i], INF);
		} // INF로 행렬 초기화

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				route[i][j] = new ArrayList<>();
			}
		} // 리스트 초기화

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (mat[from][to] > cost)
				mat[from][to] = cost;
		} // 인접행렬 입력

		floyd();
		print();
		System.out.println(sb);
	}

	static void floyd() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == k || j == k || i == j)
						continue;
					if (mat[i][k] + mat[k][j] < mat[i][j]) {// 정점끼리의 최소비용이 바뀌는 경우
						route[i][j].clear();
						route[i][j].addAll(route[i][k]);
						route[i][j].add(k);
						route[i][j].addAll(route[k][j]);
						mat[i][j] = mat[i][k] + mat[k][j];
						// 경로를 저장하며 모든 정점끼리의 최소 비용 파악
					}
				}
			}
		}
	}

	static void print() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (mat[i][j] == INF)
					sb.append(0 + " ");
				else
					sb.append(mat[i][j] + " ");
			}
			sb.append("\n");
		} // 최소비용으로 이루어진 행렬 출력

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (mat[i][j] == INF)
					sb.append(0 + "\n");
				else {
					sb.append(2 + route[i][j].size() + " ");
					sb.append(i + " ");
					for (int num : route[i][j]) {
						sb.append(num + " ");
					}
					sb.append(j + " ");
					sb.append("\n");
				}
			}
		} // 경로 출력
	}
}