import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, line[], ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		line = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			line[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(line);
		for (int i = 0; i < N; i++) {
			ans += line[i] * (N - i);
		}
		System.out.println(ans);
	}

}
