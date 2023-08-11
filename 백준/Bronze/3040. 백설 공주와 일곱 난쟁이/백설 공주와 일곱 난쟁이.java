import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] src = new int[9];
	static int[] tgt = new int[7];
	static StringBuilder sb = new StringBuilder();
	static int sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			src[i] = Integer.parseInt(br.readLine());
		}
		
		sum = 0;
		comb(0,0);
		
		System.out.println(sb);
	}	
	
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == 7) {
			if(sum == 100) {
				for (int i : tgt) {
					sb.append(i).append("\n");
				}
				return;
			}else {
				return;
			}
		}
		
		if(srcIdx == 9) return;
		
		tgt[tgtIdx] = src[srcIdx];
		sum += tgt[tgtIdx];
		comb(srcIdx + 1, tgtIdx + 1);
		sum -= tgt[tgtIdx];
		comb(srcIdx + 1, tgtIdx);
	}
}