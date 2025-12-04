import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int c, count;
	static boolean[] isNotPrime = new boolean[10000000];
	static StringBuilder sb = new StringBuilder();
	static int[] input;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		init();
		c = Integer.parseInt(br.readLine());

		for (int i = 0; i < c; i++) {
			String s = br.readLine();
			count = 0;
			input = new int[s.length()];
			for (int j = 0; j < s.length(); j++) {
				input[j] = s.charAt(j) - '0';
			}
			int max = (int)Math.pow(2, s.length()) - 1;

			select(max);

			for (Integer num : set) {
				if (!isNotPrime[num]) {
					count++;
				}
			}

			set.clear();

			sb.append(count).append("\n");
		}

		System.out.print(sb);
	}

	static void select(int max) {
		for (int i = 1; i <= max; i++) {
			perm(i, 0, 0, 0);
		}
	}

	static void perm(int mask, int checkMask, int value, int cnt) {
		if (cnt == Integer.bitCount(mask)) {
			set.add(value);
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if ((mask & (1 << i)) != 0 && (checkMask & (1 << i)) == 0) {
				perm(mask, checkMask | (1 << i), value * 10 + input[i], cnt + 1);
			}
		}
	}

	static void init() {
		isNotPrime[0] = isNotPrime[1] = true;
		for (int i = 2; i * i < isNotPrime.length; i++) {
			if (isNotPrime[i])
				continue;

			for (int j = i * i; j < isNotPrime.length; j += i) {
				isNotPrime[j] = true;
			}
		}
	}
}