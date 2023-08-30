import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, N;// 가지고 있는 랜선, 필요한 랜선
	static int[] lan;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lan = new int[K];
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());

		} // 랜선 정보 저장
		Arrays.sort(lan);
		find();
	}

	static void find() {
		long left = 1;
		long right = lan[K - 1];
		while (left <= right) {
			long half = (left + right) / 2;
			int count = 0;
			for (int i = 0; i < K; i++) {
				count += lan[i] / half;
			}
			if (count < N) {
				right = half - 1;
			} else {
				left = half + 1;
			}

		}
		System.out.println(right);
	}

}
