import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String S;
	static String T;
	static boolean isReversed;

	public static void main(String[] args) throws Exception {
		S = br.readLine();
		T = br.readLine();

		while (T.length() != S.length()) {
			int idx = 0;
			if (!isReversed)
				idx = T.length() - 1;

			char temp = T.charAt(idx);

			if (isReversed)
				T = T.substring(1);
			else
				T = T.substring(0, T.length() - 1);

			if (temp == 'B')
				isReversed = !isReversed;
		}

		if (isReversed)
			T = new StringBuilder(T).reverse().toString();

		System.out.print(S.equals(T) ? 1 : 0);
	}
}