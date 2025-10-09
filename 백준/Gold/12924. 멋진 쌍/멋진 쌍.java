import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String A, B;
	static int ans, digits;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = st.nextToken();
		B = st.nextToken();

		digits = A.length();

		for (int i = Integer.parseInt(A); i <= Integer.parseInt(B); i++) {
			String s = String.valueOf(i);
			Set<Integer> set = new HashSet<>();

			for (int j = 1; j < digits; j++) {
				String rotated = s.substring(j) + s.substring(0, j);

				//회전 후에 앞 자리가 0인 경우
				if (rotated.charAt(0) == '0')
					continue;

				int y = Integer.parseInt(rotated);

				if (y > i && y <= Integer.parseInt(B) && set.add(y))
					ans++;
			}
		}

		System.out.print(ans);
	}
}