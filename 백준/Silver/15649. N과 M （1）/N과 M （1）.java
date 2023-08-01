import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//자연수로 처리하기위해 1~N 인덱스를 사용하면
//src 배열이 필요 x index를 바로 넣어줌
//8! 경우의 수이므로 출력이 매우 크다 <= print() 대신 StiringBuilder 사용!
public class Main {
	
	static int N,M;
//	static int[] num;
	static int[] tgt;
	static boolean[] select;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		
//		num = new int[N];
		tgt = new int[M];
		select = new boolean[N+1];
		
//		for(int i=1; i<=N; i++) {
//			num[i-1] = i;
//		}
		
		perm(0);
		System.out.println(sb);
	}
	
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			for(int j=0; j<tgt.length; j++) {
				sb.append(tgt[j]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(select[i]) continue;
			tgt[tgtIdx] = i;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

}
