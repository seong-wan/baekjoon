import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, H, W, N, dir, sr, sc, nr, nc;// 테케,맵 높이,너비,명령 개수,전차의 방향,현재 위치,움직이고자 하는 위치
	static char[][] map;
	static int[] point = new int[2];// 전차의 위치(r,c)
	static int[] dr = { -1, 1, 0, 0 };// 위0,아래1,좌2,우3
	static int[] dc = { 0, 0, -1, 1 };// 위,아래,좌,우

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();

			} // 기본 맵 구성
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '^') {
						point[0] = i;
						point[1] = j;
						dir = 0;

					} // 전차가 위로 설정되어 있을 때
					if (map[i][j] == 'v') {
						point[0] = i;
						point[1] = j;
						dir = 1;

					} // 전차가 아래로 설정되어 있을 때
					if (map[i][j] == '<') {
						point[0] = i;
						point[1] = j;
						dir = 2;

					} // 전차가 좌로 설정되어 있을 때
					if (map[i][j] == '>') {
						point[0] = i;
						point[1] = j;
						dir = 3;

					} // 전차가 우로 설정되어 있을 때

				}

			} // 전차의 위치를 찾고 방향을 설정함
			sr = point[0];
			sc = point[1];

			N = Integer.parseInt(br.readLine());
			char[] cmd = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) {
				if (cmd[i] == 'U') {
					dir = 0;
					map[sr][sc] = '^';
					nr = sr + dr[0];
					nc = sc + dc[0];
					if (nr >= 0 && nr <= H - 1 && map[nr][nc] == '.') {
						map[nr][nc] = '^';
						map[sr][sc] = '.';
						sr = nr;
						sc = nc;
					}
				}//U 입력을 받았을 때
				if (cmd[i] == 'D') {
					dir = 1;
					map[sr][sc] = 'v';
					nr = sr + dr[1];
					nc = sc + dc[1];
					if (nr >= 0 && nr <= H - 1 && map[nr][nc] == '.') {
						map[nr][nc] = 'v';
						map[sr][sc] = '.';
						sr = nr;
						sc = nc;
					}
				}//D 입력을 받았을 때
				if (cmd[i] == 'L') {
					dir = 2;
					map[sr][sc] = '<';
					nr = sr + dr[2];
					nc = sc + dc[2];
					if (nc >= 0 && nc <= W - 1 && map[nr][nc] == '.') {
						map[nr][nc] = '<';
						map[sr][sc] = '.';
						sr = nr;
						sc = nc;
					}
				}//L 입력을 받았을 때
				if (cmd[i] == 'R') {
					dir = 3;
					map[sr][sc] = '>';
					nr = sr + dr[3];
					nc = sc + dc[3];
					if (nc >= 0 && nc <= W - 1 && map[nr][nc] == '.') {
						map[nr][nc] = '>';
						map[sr][sc] = '.';
						sr = nr;
						sc = nc;
					}
				}//R 입력을 받았을 때
				if (cmd[i] == 'S') {
					nr = sr + dr[dir];
					nc = sc + dc[dir];
					while (nr >= 0 && nr <= H - 1 && nc >= 0 && nc <= W - 1) {
						if (map[nr][nc] == '.' || map[nr][nc] == '-') {//물과 평지면 탄환이 범위 안에서 계속 진행
							nr += dr[dir];
							nc += dc[dir];

						}
						if (nr >= 0 && nr <= H - 1 && nc >= 0 && nc <= W - 1) {
							if (map[nr][nc] == '*') {//벽돌벽이면 파괴
								map[nr][nc] = '.';
								break;
							}
							if (map[nr][nc] == '#') {//강철벽이면 그냥 막힘
								break;
							}
						}

					}
				}//S 입력을 받았을 때

			}
			sb.append("#" + t + " ");
			for (int j = 0; j < H; j++) {
				for (int j2 = 0; j2 < W; j2++) {
					sb.append(map[j][j2]);

				}
				sb.append("\n");
			}
		}
		System.out.print(sb);//출력
	}

}