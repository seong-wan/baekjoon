import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, mat[][];

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		mat = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 인접행렬 입력

		solve();

		System.out.println(sb);

	}

	static void solve() {// from에서 중간에 거쳐갈 노드를 갈 수 있고 그 노드에서 to로 갈 수 있으면 from에서 to도 갈 수 있음
		for (int k = 0; k < N; k++) { // 중간에 거쳐갈 노드
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					if (mat[from][k] == 1 && mat[k][to] == 1) {
						mat[from][to] = 1;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(mat[i][j] + " ");
			}
			sb.append("\n");
		}

	}

}