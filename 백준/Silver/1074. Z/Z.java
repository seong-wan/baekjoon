import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, r, c, value;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		z(0, 0, (int) Math.pow(2, N), 0);
		System.out.println(value);
	}

	static void z(int sr, int sc, int size, int sv) {
		if (size == 2) {
			if (r == sr && c == sc)
				value = sv;
			else if (r == sr && c == sc + 1)
				value = sv + 1;
			else if (r == sr + 1 && c == sc)
				value = sv + 2;
			else if (r == sr + 1 && c == sc + 1)
				value = sv + 3;

		}
		int half = size / 2;
		if (sr <= r && r < sr + half && sc <= c && c < sc + half)
			z(sr, sc, half, sv);
		if (sr <= r && r < sr + half && sc + half <= c && c < sc + 2 * half)
			z(sr, sc + half, half, sv + half * half);
		if (sr + half <= r && r < sr + 2 * half && sc <= c && c < sc + half)
			z(sr + half, sc, half, sv + 2 * half * half);
		if (sr + half <= r && r < sr + 2 * half && sc + half <= c && c < sc + 2 * half)
			z(sr + half, sc + half, half, sv + 3 * half * half);

	}

}
