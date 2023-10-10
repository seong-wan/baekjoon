import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R,C,T;
	static int[][] room, temp; // temp는 각 초마다 미세먼지 확산 정보를 저장할 배열
	static boolean[][] dust; // 각 초마다 미세먼지 정보를 저장할 배열
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static int[] cleanerHead = new int[2];
	static int[] cleanerTail = new int[2];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		
		room = new int[R][C];
		int cleanerIdx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int now = Integer.parseInt(st.nextToken());
				if( now == -1) {
					if( cleanerIdx == 0) {
						cleanerHead[0] = i;
						cleanerHead[1] = j;
						cleanerIdx++;
					}else {
						cleanerTail[0] = i;
						cleanerTail[1] = j;
					}
					
				}
				room[i][j] = now; 
			}
		}
		for(int time = 0; time < T; time++ ) {
			play(); // 확산
			play2(); //공기 청정기 작동

		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if( room[i][j] > 0) sum+= room[i][j];
			}
		}
		System.out.println(sum);
		
	}
	
	static void play() {
		dust = new boolean[R][C];
		temp = new int[R][C];
		// 확산 먼저 하기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if( room[i][j] > 0) dust[i][j] = true;
			}
		}
		
		// true인것에 대해 temp에 확산 값을 넣자.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if( dust[i][j] = true) {
					// 확산 수행
					if( room[i][j] == -1) continue;// 현재 좌표가 -1이 아니면 수행
					
					
					int quantity = room[i][j] / 5;
					if( quantity == 0) continue;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						
						if(ny < 0 || nx < 0 || ny >= R || nx >= C ) continue; // 범위 벗어나면 continue
						if( room[ny][nx] == -1) continue; // 공기청정기 이면 continue
						
						temp[ny][nx] += quantity;
						cnt++;
					}
					// cnt해준만큼 원래값에서 빼자.
					room[i][j] -= quantity*cnt;
				}
			}
		}
		// room에 값 넣어주기.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				room[i][j] += temp[i][j];
			}
		}
		
	}
	
	static void play2() {
		
		// temp, dust를 사용하자.
		// temp 초기화
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[i][j] = 0;
				dust[i][j] = false;
			}
		}
		
		// 위에 먼저 반시계
		
		// 가로1
		for (int i = 1; i < C-1; i++) {
			
			temp[cleanerHead[0]][i+1] = room[cleanerHead[0]][i];
			dust[cleanerHead[0]][i] = true;
		}
		
		// 세로1
		for (int i = cleanerHead[0]; i > 0; i--) {
			temp[i-1][C-1] = room[i][C-1];
			dust[i][C-1] = true;
		}
		
		// 가로2
		for (int i = C-1; i > 0; i--) {
			temp[0][i-1] = room[0][i];
			dust[0][i] = true;
		}
		// 세로2 , 마지막 제외
		for (int i = 0; i < cleanerHead[0]-1; i++) {
			temp[i+1][0] = room[i][0];
			dust[i][0] = true;
		}
		// 마지막꺼 추가
		dust[cleanerHead[0]-1][0] = true;
		// 아래 시계
		// 가로1
		for (int i = 1; i < C-1; i++) {
			temp[cleanerTail[0]][i+1] = room[cleanerTail[0]][i];
			dust[cleanerTail[0]][i] = true;
		}
		// 세로1
		for (int i = cleanerTail[0]; i < R-1; i++) {
			temp[i+1][C-1] = room[i][C-1];
			dust[i][C-1] = true;
		}
		// 가로2
		for (int i = C-1; i > 0; i--) {
			temp[R-1][i-1] = room[R-1][i];
			dust[R-1][i] = true;
		}
		// 세로2 , 마지막 제외
		for (int i = R-1; i > cleanerTail[0]+1; i--) {
			temp[i-1][0] = room[i][0];
			dust[i][0] = true;
		}
		// 마지막꺼 추가
		dust[cleanerTail[0]+1][0] = true;
		// 방문한곳만 room 초기화
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if( dust[i][j] ) room[i][j] = temp[i][j];
			}
		}
	}

}