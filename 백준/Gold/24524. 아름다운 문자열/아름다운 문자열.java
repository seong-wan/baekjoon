import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String S, T;
	static Deque<Integer>[] idxs = new Deque[26];
	static int ans;

	public static void main(String[] args) throws Exception {
		S = br.readLine();
		T = br.readLine();

		for (int i = 0; i < 26; i++) {
			idxs[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < S.length(); i++) {
			idxs[S.charAt(i) - 'a'].add(i);
		}

		while (true) {
			boolean isMake = true;
			int before = -1;
			for (int i = 0; i < T.length(); i++) {
				if (idxs[T.charAt(i) - 'a'].isEmpty()) {
					isMake = false;
					break;
				}

				boolean isFound = false;
				while (!idxs[T.charAt(i) - 'a'].isEmpty()) {
					int tempIdx = idxs[T.charAt(i) - 'a'].poll();
					if (tempIdx > before) {
						isFound = true;
						before = tempIdx;
						break;
					}
				}

				if (!isFound) {
					isMake = false;
					break;
				}
			}

			if (isMake)
				ans++;
			else
				break;
		}

		System.out.print(ans);
	}
}