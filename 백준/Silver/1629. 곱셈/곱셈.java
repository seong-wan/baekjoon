import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long A, B, C;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		System.out.println(power(A, B));

	}

	static int power(long num, long p) {
		long result = 1L;

		while (p > 0) {
			if (p % 2 == 1)
				result = result * num % C;
			p /= 2;
			num = num * num % C;
		}
		return (int) result;
	}

}