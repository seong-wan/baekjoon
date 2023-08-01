
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] num;
	static int[] tgt;
	static boolean[] select;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		tgt = new int[M];
		select = new boolean[N];
		
		for(int i=1; i<=N; i++) {
			num[i-1] = i;
		}
		
		perm(0);
	}
	
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			for(int j=0; j<tgt.length; j++) {
				System.out.print(tgt[j] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<num.length; i++) {
			if(select[i]) continue;
			tgt[tgtIdx] = num[i];
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

}
