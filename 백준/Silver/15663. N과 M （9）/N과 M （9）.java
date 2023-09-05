import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, src[], tgt[];
	static Set<String> set = new LinkedHashSet<>();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		src = new int[N];
		tgt = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			src[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(src);
		perm(0, 0);
		set.forEach(System.out::println);
	}

	static void perm(int tgtidx, int mask) {
		if (tgtidx == M) {
			StringBuilder sb = new StringBuilder();
			for (Integer i : tgt) {
				sb.append(i + " ");
			}
			set.add(sb.toString());

			return;

		}
		for (int i = 0; i < N; i++) {
			if ((mask & 1 << i) == 0) {
				tgt[tgtidx] = src[i];
				perm(tgtidx + 1, mask | 1 << i);
			}
		}
	}

}
