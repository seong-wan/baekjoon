import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, right;
	static int[] cookies;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		cookies = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cookies[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, cookies[i]);
		} // 과자들의 길이 정보를 입력 받으면서 최대를 구함

		find();
	}

	static void find() {
		int left = 1;
		while (left <= right) {
			int half = (left + right) / 2;
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (cookies[i] / half > 0)
					count += cookies[i] / half;
			}
			if (count < M) {
				right = half - 1;
			} else {
				left = half + 1;
			}

		}
		System.out.println(right);
	}

}