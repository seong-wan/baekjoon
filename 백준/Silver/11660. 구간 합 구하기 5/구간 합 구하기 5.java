import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j - 1] + map[i - 1][j] - (map[i - 1][j - 1]);

			}

		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int Sy = Integer.parseInt(st.nextToken());
			int Sx = Integer.parseInt(st.nextToken());
			int Ey = Integer.parseInt(st.nextToken());
			int Ex = Integer.parseInt(st.nextToken());
			sum(Sy, Sx, Ey, Ex);
		}

		System.out.println(sb);
	}

	static void sum(int a, int b, int c, int d) {
		int ans = map[c][d] - map[c][b - 1] - map[a - 1][d] + map[a - 1][b - 1];
		sb.append(ans).append("\n");
	}

}
