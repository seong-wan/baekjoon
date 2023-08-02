
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new int[M];
		
		comb(1,0);
		
	}
	
	static void comb(int nIdx, int tgtIdx ) {
		//기저조건
		if(tgtIdx == M) {
			for (int i : tgt) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		if(nIdx == N+1) return;
		
			tgt[tgtIdx] = nIdx;
			comb(nIdx+1, tgtIdx+1);
			comb(nIdx+1, tgtIdx);

	}

}
