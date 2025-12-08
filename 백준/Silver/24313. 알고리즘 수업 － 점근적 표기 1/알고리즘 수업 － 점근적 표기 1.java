import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int a1, a0, c, n0;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		a1 = Integer.parseInt(st.nextToken());
		a0 = Integer.parseInt(st.nextToken());

		c = Integer.parseInt(br.readLine());

		n0 = Integer.parseInt(br.readLine());

		if (a1 > c) {
			System.out.print(0);
			return;
		}

		if (a1 * n0 + a0 <= c * n0)
			System.out.print(1);
		else
			System.out.print(0);
	}
}