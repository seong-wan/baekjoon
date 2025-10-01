import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, ans;
	static boolean[] learned = new boolean[26];
	static Set<Character>[] characters;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K < 5) {
			System.out.print(0);
			return;
		}

		learned[0] = true;
		learned[2] = true;
		learned[8] = true;
		learned[13] = true;
		learned[19] = true;

		characters = new Set[N];

		for (int i = 0; i < N; i++) {
			characters[i] = new HashSet<>();

			String s = br.readLine();

			for (int j = 0; j < s.length(); j++) {
				characters[i].add(s.charAt(j));
			}
		}

		comb(1, 6);

		System.out.print(ans);
	}

	static void comb(int idx, int cnt) {
		if (cnt == K + 1) {
			check();
			return;
		}

		if (idx == 26)
			return;

		comb(idx + 1, cnt);

		if (idx != 2 && idx != 8 && idx != 13 && idx != 19) {
			learned[idx] = true;
			comb(idx + 1, cnt + 1);
			learned[idx] = false;
		}
	}

	static void check() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			boolean avail = true;

			for (Character c : characters[i]) {
				if (!learned[c - 'a']) {
					avail = false;
					break;
				}
			}

			if (avail)
				cnt++;
		}

		ans = Math.max(cnt, ans);
	}
}