import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[][] item;
	static int[][] memoi;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		item = new int[N+1][2];
		memoi = new int[N+1][K+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				item[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				if(item[i][0] <= j) {
					int sel_o = memoi[i-1][j-item[i][0]] + item[i][1];
					int sel_x = memoi[i-1][j];
					
					memoi[i][j] = Math.max(sel_o, sel_x);
				}else {
					memoi[i][j] = memoi[i-1][j];
				}
			}
		}
		
		System.out.println(memoi[N][K]);
	}

}