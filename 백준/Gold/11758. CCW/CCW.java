import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] x = new int[4], y = new int[4];

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			x[i + 1] = Integer.parseInt(st.nextToken());
			y[i + 1] = Integer.parseInt(st.nextToken());
		}

		if (ccw() > 0)
			System.out.println(1);
		else if (ccw() == 0)
			System.out.println(0);
		else
			System.out.println(-1);
	}

	static int ccw() {

		return x[1] * y[2] + x[2] * y[3] + x[3] * y[1] - (x[2] * y[1] + x[3] * y[2] + x[1] * y[3]);
	}

}