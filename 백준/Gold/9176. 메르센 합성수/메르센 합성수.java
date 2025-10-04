import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[] ans = new String[62];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int K = Integer.parseInt(br.readLine());

		ans[11] = "23 * 89 = 2047 = ( 2 ^ 11 ) - 1";
		ans[23] = "47 * 178481 = 8388607 = ( 2 ^ 23 ) - 1";
		ans[29] = "233 * 1103 * 2089 = 536870911 = ( 2 ^ 29 ) - 1";
		ans[37] = "223 * 616318177 = 137438953471 = ( 2 ^ 37 ) - 1";
		ans[41] = "13367 * 164511353 = 2199023255551 = ( 2 ^ 41 ) - 1";
		ans[43] = "431 * 9719 * 2099863 = 8796093022207 = ( 2 ^ 43 ) - 1";
		ans[47] = "2351 * 4513 * 13264529 = 140737488355327 = ( 2 ^ 47 ) - 1";
		ans[53] = "6361 * 69431 * 20394401 = 9007199254740991 = ( 2 ^ 53 ) - 1";
		ans[59] = "179951 * 3203431780337 = 576460752303423487 = ( 2 ^ 59 ) - 1";

		for (int i = 0; i <= K; i++) {
			if (ans[i] != null)
				sb.append(ans[i]).append("\n");
		}

		System.out.print(sb);
	}
}