import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] input;
	static int[][][] dp; //[이동방향][y][x] -> ex) [2][4][3] => 대각선으로 4,3 으로 이동한 경우의 수(합)
	//[0][4][3] => 가로로 4,3 좌표로 이동한 경우의 수 (합)
	//[1][4][3] => 세로로 4,3 좌표로 이동한 경우의 수 (합)
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N+1][N+1];
		dp = new int[3][N+1][N+1]; 
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1][2] = 1;
		
		//현재 위치에서 대각선으로, 가로로, 세로로 이동하는 값을 dp로 계산
		for(int i=1; i<=N; i++) {
			for(int j=2; j<=N; j++) {
				//대각선으로 이동하는 dp
				//대각선으로 이동시 벽지가 발리면 안되는 구간을 체크하기 
				if(i < N && j < N && input[i+1][j+1] == 0 && input[i+1][j] == 0 && input[i][j+1] == 0) {
					//현재 y,x에서 대각선 y+1, x+1로 가는 모든 경우의 수는 
					//y,x 로 올때 대각선으로, 가로로, 세로로 오는 모든 경우의 수를 합친다. 
					dp[2][i+1][j+1] = dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
				}
				
				//가로로 이동하는 dp
				//2개 이동만 (대각선, 가로)
				if(j < N && input[i][j+1]==0) {
					dp[0][i][j+1] = dp[0][i][j] + dp[2][i][j];
				}

				//세로로 이동하는 dp
				//세로 -> 세로, 대각선 -> 세로 만 가능하므로. 
				if(i < N && input[i+1][j] == 0) {
					dp[1][i+1][j] = dp[1][i][j] + dp[2][i][j];
				}
			}
		}
		
		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);

	}

}