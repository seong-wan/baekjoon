import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, zero, one;
	static int[][] map;
	static int sum;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sum += map[i][j] = Integer.parseInt(st.nextToken());
			}

		} // map입력
		if (sum == 0)
			zero++;
		else if (sum == N * N)
			one++;
		else
			divide(0, 0, N);//1과 0으로 가득 차 있지 않다면 분할

		System.out.println(zero + "\n" + one);

	}

	static void divide(int sr, int sc, int size) {
		if (size == 1)
			return;
		int half = size / 2;

		sum = 0;
		for (int i = sr; i < sr + half; i++) {
			for (int j = sc; j < sc + half; j++) {
				sum += map[i][j];
			}
		}
		if (sum == half * half)
			one++;
		else if (sum == 0)
			zero++;
		else
			divide(sr, sc, half);
		sum = 0;
		for (int i = sr; i < sr + half; i++) {
			for (int j = sc + half; j < sc + 2 * half; j++) {
				sum += map[i][j];
			}
		}
		if (sum == half * half)
			one++;
		else if (sum == 0)
			zero++;
		else
			divide(sr, sc + half, half);
		sum = 0;
		for (int i = sr + half; i < sr + 2 * half; i++) {
			for (int j = sc; j < sc + half; j++) {
				sum += map[i][j];
			}
		}
		if (sum == half * half)
			one++;
		else if (sum == 0)
			zero++;
		else
			divide(sr + half, sc, half);

		sum = 0;
		for (int i = sr + half; i < sr + 2 * half; i++) {
			for (int j = sc + half; j < sc + 2 * half; j++) {
				sum += map[i][j];
			}
		}
		if (sum == half * half)
			one++;
		else if (sum == 0)
			zero++;
		else
			divide(sr + half, sc + half, half);
	}

}
