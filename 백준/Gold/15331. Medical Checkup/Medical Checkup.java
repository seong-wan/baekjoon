import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, T, maxH;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());

			if (T < 0) {
				sb.append(1).append("\n");
				continue;
			}

			T -= h;
			maxH = Math.max(maxH, h);

			if (T < 0)
				sb.append(1).append("\n");
			else
				sb.append(T / maxH + 2).append("\n");
		}

		System.out.print(sb);
	}
}