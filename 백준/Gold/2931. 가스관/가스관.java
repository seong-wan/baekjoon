import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C;
	static int[] dy = {-1, 1, 0, 0}; //상하좌우
	static int[] dx = {0, 0, -1, 1};
	static char[] block = {'|', '-', '+', '1', '2', '3', '4'};
	static char[][] map;
	static int[] problem;
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char ans;
	static int my,mx,zy,zx,mcnt,zcnt;
	public static void main(String[] args) throws Exception{
	
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			
			for(int i=0; i<R; i++) {
				String str = br.readLine();
				for(int j=0; j<C; j++) {

					map[i][j] = str.charAt(j);
					if(map[i][j] == 'M') {
						my = i;
						mx = j;
					}
					
					if(map[i][j] == 'Z') {
						zy = i;
						zx = j;
					}
				}
			}
			
			problem = new int[6]; //0은 y좌표, 1은 x좌표, 2,3,4,5 -> 상하좌우 중 채워야할 방향 1
			simulation();
			
			for(int i=0; i<4; i++) {
				int ny = my + dy[i];
				int nx = mx + dx[i];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(map[ny][nx] != '.') mcnt++;
			}
			
			for(int i=0; i<4; i++) {
				int ny = zy + dy[i];
				int nx = zx + dx[i];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
				if(map[ny][nx] != '.') zcnt++;
			}
			
			int y = problem[0];
			int x = problem[1];
			
			for(int d=0; d<4; d++) { //문제되는 부분의 4방향을 돌면서 문제확인하기 
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny >= 0 && nx >= 0 && ny < R && nx < C && map[ny][nx] != '.') {
					char a = map[ny][nx];
					if(d == 0 && (a == block[0] ||a == block[2] ||a == block[3] ||a == block[6])) { //윗방향 문제담기
						problem[2] = 1;
					}
					
					if(d == 1 && (a == block[0] ||a == block[2] ||a == block[4] ||a == block[5])) { //아랫방향 문제담기
						problem[3] = 1;
					}
					
					if(d == 2 && (a == block[1] ||a == block[2] ||a == block[3] ||a == block[4])) { //왼쪽 문제 담기
						problem[4] = 1;
					}
					
					if(d == 3 && (a == block[1] ||a == block[2] ||a == block[5] ||a == block[6])) { //오른쪽 문제담기
						problem[5] = 1;
					}
					
					if((a == 'M' && mcnt == 0) || (a == 'Z' && zcnt == 0)) { //도착지점이 문제라면 해당 도착지점 문제 담기
						problem[d+2] = 1;
					}
				}
			}
			
			if(problem[2] == 1 && problem[3] == 1 && problem[4] == 1 && problem[5] == 1) { //4방향이 다 문제라면 
				ans = block[2]; //block 배열의 두번째
			}else if(problem[2] == 1 && problem[3] == 1) { //상하문제
				ans = block[0];
			}else if(problem[4] == 1 && problem[5] == 1) { //좌우문제
				ans = block[1];
			}else if(problem[3] == 1 && problem[5] == 1) { //하우문제
				ans = block[3];
			}else if(problem[2] == 1 && problem[5] == 1) { //상우문제
				ans = block[4];
			}else if(problem[2] == 1 && problem[4] == 1) { //상좌문제
				ans = block[5];
			}else if(problem[3] == 1 && problem[4] == 1) { //하좌 문제
				ans = block[6];
			}
			
			sb.append(problem[0]+1).append(" ").append(problem[1]+1).append(" ").append(ans).append("\n"); //출력하기
			
		
		
		System.out.println(sb);

	}
	
	static void simulation() { //문제있는 빈칸 찾기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == '.') continue; //빈칸이라면 넘어가기 
				
				if(map[i][j] == block[0]) { //'|'의 경우 상하를 체크하여 빈공간인 경우 문제에 담고 return
					for(int d=0; d<2; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						
						if(map[ny][nx] == '.') {
							problem[0] = ny;
							problem[1] = nx;
							if(d == 0) problem[3] = 1;
							else problem[2] = 1;
							return;
						}
					}
				}else if(map[i][j] == block[1]) { //-인 경우 좌우를 체크하여 빈공간인 경우 문제에 담고 return;
					for(int d=2; d<4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						
						if(map[ny][nx] == '.') {
							problem[0] = ny;
							problem[1] = nx;
							if(d == 2) problem[5] = 1;
							else problem[4] = 1;
							return;
						}
					}
				}else if(map[i][j] == block[2]) { //+인 경우 상하좌우를 체크하여 빈공간인 경우 문제에 담고 return;
					for(int d=0; d<4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if(map[ny][nx] == '.') {
							problem[0] = ny;
							problem[1] = nx;
							if(d == 0) problem[3] = 1;
							else if(d == 1) problem[2] = 1;
							else if(d == 2) problem[5] = 1;
							else if(d == 3) problem[4] = 1;
							return;
						}
					}
				}
				else if(map[i][j] == block[3]) { //block 배열의 3번째경우 , 하우를 체크하여 빈공간인 경우 문제에 담고 return;
					int ny = i+1;
					int nx = j;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[2] = 1;
						return;
					}
					
					ny = i;
					nx = j+1;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[4] = 1;
						return;
					}
				}else if(map[i][j] == block[4]) { //block 배열의 4번째경우 , 상우를 체크하여 빈공간인 경우 문제에 담고 return;
					int ny = i-1;
					int nx = j;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[3] = 1;
						return;
					}
					
					ny = i;
					nx = j+1;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[4] = 1;
						return;
					}
				}else if(map[i][j] == block[5]) { //block 배열의 5번째경우 , 상좌를 체크하여 빈공간인 경우 문제에 담고 return;
					int ny = i-1;
					int nx = j;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[3] = 1;
						return;
					}
					
					ny = i;
					nx = j-1;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[5] = 1;
						return;
					}
				}else if(map[i][j] == block[6]) { //block 배열의 6번째경우 , 좌, 하를 체크하여 빈공간인 경우 문제에 담고 return;
					int ny = i+1;
					int nx = j;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[2] = 1;
						return;
					}
					
					ny = i;
					nx = j-1;
					
					if(map[ny][nx] == '.') {
						problem[0] = ny;
						problem[1] = nx;
						problem[5] = 1;
						return;
					}
				}
			
			}
		}
	}

}