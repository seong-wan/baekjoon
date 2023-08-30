import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static int[] memoi = new int[11];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		memoi[1] = 1;
		memoi[2] = 2;
		memoi[3] = 4;
		for (int i = 4; i <= 10; i++) {
			memoi[i] = memoi[i - 3] + memoi[i - 2] + memoi[i - 1];
		}
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(memoi[n] + "\n");
		}
		System.out.println(sb);
	}
}
