import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] arr;
	static int[] src;
	static boolean[] select;
	static StringTokenizer st;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(br.readLine());
		src = new int[N];
		select = new boolean[N];
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			src[i] = i;

		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		subset(0);
		System.out.println(ans);
	}

	static void subset(int srcIdx) {
		
		if (srcIdx == src.length) {

			printSubset();
			return;
		}

	
		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);

	}

	static void printSubset() {
		int a = 1;
		int b = 0;

		int cnt = 0;
		for (int i = 0; i < select.length; i++) {
			if (select[i]) {
				a *= arr[i][0];
				b += arr[i][1];
				cnt++;
			}

		}
		if (cnt > 0) {
			int min = Math.abs(a - b);
			if (ans > min)
				ans = min;
		}

	}
}
