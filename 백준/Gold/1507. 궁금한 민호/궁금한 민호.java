import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, ans;
	static int[][] mat;
	static int[][] orin;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		orin = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				orin[i][j] = mat[i][j];
			}
		} // 인접 행렬 입력

		r_floyd();

	}

	static void r_floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j && i != k && k != j) {
						if (mat[i][j] == mat[i][k] + mat[k][j])
							orin[i][j] = 0;
						if (mat[i][j] > mat[i][k] + mat[k][j]) {
							System.out.println(-1);
							return;
						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += orin[i][j];
			}
		}
		System.out.println(ans / 2);

	}

}