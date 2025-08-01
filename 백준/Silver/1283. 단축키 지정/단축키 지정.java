import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean[] chk = new boolean[26];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int idx = 0;
			boolean pass = false;
			String input = br.readLine();

			st = new StringTokenizer(input);

			//단어의 첫글자 체크
			while (st.hasMoreTokens()) {
				String word = st.nextToken();

				if (!check(word.charAt(0))) {
					pass = true;
					chk[Character.toLowerCase(word.charAt(0)) - 'a'] = true;
					sb.append(input, 0, idx).append("[").append(word.charAt(0)).append("]")
						.append(input.substring(idx + 1)).append("\n");
					break;
				}

				idx += word.length() + 1;
			}

			if (pass)
				continue;

			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) != ' ' && !check(input.charAt(j))) {
					pass = true;

					chk[Character.toLowerCase(input.charAt(j)) - 'a'] = true;
					sb.append(input.replaceFirst(input.substring(j, j + 1), "[" + input.charAt(j) + "]"))
						.append("\n");

					break;
				}
			}

			if (pass)
				continue;

			sb.append(input).append("\n");
		}

		System.out.println(sb);
	}

	static boolean check(char temp) {
		if (temp >= 'a' && temp <= 'z') {
			return chk[temp - 'a'];
		}

		return chk[temp - 'A'];
	}
}