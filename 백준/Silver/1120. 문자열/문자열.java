import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();

		int min = B.length();

		for (int i = 0; i < B.length() - A.length() + 1; i++) {
			String str = B.substring(i, i + A.length());
			int cnt = 0;
			for (int j = 0; j < str.length(); j++) {
				if (A.charAt(j) != str.charAt(j))
					cnt++;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min);

	}

}