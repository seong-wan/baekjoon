
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		src = new int[N];
		tgt = new int[M];
		select = new boolean[N];

		for (int i = 0; i < N; i++) {
			src[i] = i + 1;

		} // src 배열 채움
		perm(0);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			for (int t : tgt) {
				System.out.print(t + " ");

			}
			System.out.println();
			return;
		} // 기저 조건

		for (int i = 0; i < src.length; i++) {

			if (select[i]) {
				continue;
			}
			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

}
