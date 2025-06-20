import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans;
	static int[] tmp = new int[4];
	static StringTokenizer st;
	static int[] list;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		list = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		list[0] = 1;
		for (int i = 1; i <= N; i++) {
			list[i] = list[i - 1] * Integer.parseInt(st.nextToken());
		}

		perm(0, 1);
		System.out.println(ans);
	}

	static void perm(int idx, int num) {
		if (idx == 4) {
			calc();
			return;
		}

		if (num == N + 1)
			return;

		tmp[idx] = num;
		perm(idx + 1, num + 1);
		perm(idx, num + 1);
	}

	static void calc() {
		int temp = list[tmp[0]];
		int sum = temp;
		for (int i = 1; i <= 3; i++) {
			temp = list[tmp[i]] / list[tmp[i - 1]];
			sum += temp;
		}

		ans = Math.max(ans, sum);
	}
}