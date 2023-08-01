import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] src;
	static int[] tgt;
	static boolean[] select;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tgt = new int[M];
		select = new boolean[N];

		perm(0);
		System.out.println(sb);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			for (int t : tgt) {
				sb.append(t).append(" ");
			}
			sb.append("\n");
			return;
		} // 기저 조건

		for (int i = 0; i < N; i++) {

			if (select[i]) {
				continue;
			}
			tgt[tgtIdx] = i + 1;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

}
