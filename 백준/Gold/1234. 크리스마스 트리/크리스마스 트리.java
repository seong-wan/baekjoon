import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, R, G, B;
	static long ans;
	static int[] factorial = new int[11];

	public static void main(String[] args) throws Exception {
		makeFactorial();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		//각각의 색으로 시작함
		if (R >= 1)
			search(R - 1, G, B, 2, 1);
		if (G >= 1)
			search(R, G - 1, B, 2, 1);
		if (B >= 1)
			search(R, G, B - 1, 2, 1);

		System.out.print(ans);
	}

	static void search(int r, int g, int b, int level, long value) {
		if (level > N) {
			ans += value;
			return;
		}

		for (int i = 0; i <= Math.min(r, level); i++) {
			for (int j = 0; j <= Math.min(g, level); j++) {
				for (int k = 0; k <= Math.min(b, level); k++) {
					if (i + j + k != level)
						continue;

					if (i == 0 && j == k || j == 0 && i == k || k == 0 && i == j || i == j && j == k
						|| j == k && j == 0 || i == k && i == 0 || i == j && i == 0) {
						long nextValue = value * factorial[level] / ((long)factorial[i] * factorial[j] * factorial[k]);
						search(r - i, g - j, b - k, level + 1, nextValue);
					}
				}
			}
		}
	}

	static void makeFactorial() {
		factorial[0] = 1;
		factorial[1] = 1;
		for (int i = 2; i <= 10; i++) {
			factorial[i] = i * factorial[i - 1];
		}
	}
}