import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//comb는 시간초과
//dp + 파스칼의 방정식 
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
			memoi = new int[M+1][M+1]; //0dummy, M*N (x)
			
			memoi[0][0] = 1;
			
			//파스칼의 삼각형
			for(int i=1; i<=M; i++) { //밑으로 (행)
				for(int j=0; j<=i; j++) { //옆으로 (열)
					if(j==0 || i==j) {
						memoi[i][j] = 1;
						continue;
					}
					
					memoi[i][j] = memoi[i-1][j-1] + memoi[i-1][j];
				}
			}
			
			System.out.println(memoi[M][N]);
		}
	}
	
	
}