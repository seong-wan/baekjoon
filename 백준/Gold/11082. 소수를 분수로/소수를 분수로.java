import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static long up, down, cUp, cDown;
	static boolean isDecimal, isCircular;

	public static void main(String[] args) throws Exception {
		input = br.readLine();
		up = input.charAt(0) - '0';
		down = 1;

		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(i) == ')')
				continue;

			if (input.charAt(i) == '.') {
				isDecimal = true;
				continue;
			}

			if (input.charAt(i) == '(') {
				isCircular = true;
				continue;
			}

			if (isCircular) {
				cUp = cUp * 10 + (input.charAt(i) - '0');
				cDown = cDown * 10 + 9;

				continue;
			}

			up = up * 10 + (input.charAt(i) - '0');

			if (isDecimal)
				down *= 10;
		}

		if (isCircular) {
			up = up * cDown + cUp;
			down *= cDown;
		}

		long div = gcd(up, down);
		
		System.out.print(up / div + "/" + down / div);
	}

	static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}