import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		check = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());

			if (check[temp]) {
				System.out.print(temp);
				return;
			}

			check[temp] = true;
		}
	}
}