import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tgt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new int[M];
		
		comb(1,0);
		System.out.println(sb);
		
	}
	
	static void comb(int nIdx, int tgtIdx ) {
		//기저조건
		if(tgtIdx == M) {
			for (int i : tgt) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		if(nIdx == N+1) return;
		
		tgt[tgtIdx] = nIdx; //현재 tgtIdx 에 현재 nIdx 넣기
		comb(nIdx+1, tgtIdx+1); //다음 tgtIdx에 다음 nIdx넣기
		comb(nIdx+1, tgtIdx); //현재 tgtIdx에 다음 nIdx넣기

	}

}
