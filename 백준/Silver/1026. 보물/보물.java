import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// B의 높은 수에 A의 낮은 수를 차례대로 곱해주고 더하면 됨
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, A[], B[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);
		Arrays.sort(B);
		System.out.println(cal());

	}

	static int cal() {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += A[i] * B[N - 1 - i];
		}
		return ans;
	}// 계산

}
