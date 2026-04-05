import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String s;
	static String[] pikachu = {"pi", "ka", "chu"};

	public static void main(String[] args) throws Exception {
		s = br.readLine();

		int start = 0;

		while (start < s.length()) {
			boolean isPikachu = false;

			for (String p : pikachu) {
				if (s.startsWith(p, start)) {
					start += p.length();
					isPikachu = true;
					break;
				}
			}

			if (!isPikachu) {
				System.out.print("NO");
				return;
			}
		}

		System.out.print("YES");
	}
}