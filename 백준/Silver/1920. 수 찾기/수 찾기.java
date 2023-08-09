import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] A;

	public static void main(String[] args) throws IOException {
		setting();
		for (int i = 0; i < M; i++) {

			binary_check();
		}
		System.out.println(sb);
	}

	static void setting() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		} // 배열에 수 입력
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Arrays.sort(A);
	}

	static void binary_check() {

		sb.append(Arrays.binarySearch(A, Integer.parseInt(st.nextToken())) >= 0 ? 1 : 0);
		sb.append("\n");
	}
}
