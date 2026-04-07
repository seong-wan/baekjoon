import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[] cnt = new int[5001];
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		init();

		String input = "";
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			st = new StringTokenizer(input);

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			sb.append(cnt[M] - cnt[N - 1]).append("\n");
		}

		System.out.print(sb);
	}

	static void init() {
		for (int i = 1; i <= 5000; i++) {
			cnt[i] = cnt[i - 1];

			int a = i / 1000;
			if (a == 0)
				a = 10; //맨 앞이 0인 경우

			int b = (i % 1000) / 100;
			if (a == 10 && b == 0)
				b = 11; //맨 앞이 0이고 두 번째 자리도 0인 경우

			int c = (i % 100) / 10;
			if (a == 10 && b == 11 && c == 0)
				c = 12; //맨 앞이 0이고 두 번째 자리도 0이고 세 번째 자리도 0인 경우

			int d = (i % 10);

			if (a != b && a != c && a != d && b != c && b != d && c != d)
				cnt[i]++;
		}
	}
}
