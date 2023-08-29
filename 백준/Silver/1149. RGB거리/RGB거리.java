import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, memoi[][], R, G, B;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		memoi = new int[N + 1][3];// 0은 더미,R,G,B
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			memoi[i][0] = Math.min(memoi[i - 1][1], memoi[i - 1][2]) + R;
			memoi[i][1] = Math.min(memoi[i - 1][0], memoi[i - 1][2]) + G;
			memoi[i][2] = Math.min(memoi[i - 1][0], memoi[i - 1][1]) + B;

		}
		Arrays.sort(memoi[N]);
		System.out.println(memoi[N][0]);
	}

}