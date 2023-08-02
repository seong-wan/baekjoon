import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] src;
	static int[] tgt;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		src = new int[N];
		tgt = new int[M];
		for (int i = 0; i < N; i++) {
			src[i] = i + 1;
		}
		comb(0, 0);
		System.out.print(sb);
	}

	static void comb(int tgtIdx, int srcIdx) {
		if (tgtIdx == tgt.length) {
			for (int i : tgt) {

				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		if (srcIdx == src.length)
			return;
		tgt[tgtIdx] = src[srcIdx];
		comb(tgtIdx + 1, srcIdx + 1);
		comb(tgtIdx, srcIdx + 1);

	}
}
