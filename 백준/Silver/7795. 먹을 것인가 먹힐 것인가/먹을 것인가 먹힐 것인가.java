import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, A[], B[];

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			A = new int[N];
			B = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			} // A 입력

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			} // B 입력

			Arrays.sort(B);// 이분 탐색을 위해 기준 값인 B정렬
			for (int i = 0; i < N; i++) {
				ans += bs(A[i]);
			}
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}

	static int bs(int n) {
		int min_idx = 0;
		int max_idx = M - 1;

		while (min_idx <= max_idx) {
			int half = (min_idx + max_idx) / 2;
			if (B[half] < n)
				min_idx = half + 1;
			else
				max_idx = half - 1;
		}
		return min_idx;
	}// 이분탐색 후 조건을 만족하는 인덱스 값 리턴(해당 값보다 B배열에서 작은 값들의 개수)

}
