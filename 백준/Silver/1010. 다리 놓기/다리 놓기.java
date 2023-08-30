import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//시간초과를 해결하기 위해
//comb + memoi

public class Main {
	static int T,N,M;
	static int[][] memoi;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			memoi = new int[M+1][N+1]; //파스칼의 삼각형이 아니므로 M*M이 아니여도 됩니다.

			System.out.println(comb(M,N)); //0,0 X
		}
	}
	
	static int comb(int srcIdx, int tgtIdx) {
		if( srcIdx == tgtIdx || tgtIdx == 0 ) {
			return memoi[srcIdx][tgtIdx] = 1;
		}
		
		//이미 계산된 것이 있으면 그걸 이용한다.
		if( memoi[srcIdx][tgtIdx] > 0 ) return memoi[srcIdx][tgtIdx];
		
		//아직 계산된 값이 없다면 선택, 비선택 두가지 경우의 수를 모두 구한 뒤에 합친다. 
		return memoi[srcIdx][tgtIdx] = comb(srcIdx-1, tgtIdx-1) + comb(srcIdx-1, tgtIdx);
	}
}