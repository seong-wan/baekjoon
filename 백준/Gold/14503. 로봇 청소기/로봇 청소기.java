import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, cnt;// 맵의 크기,청소한 칸 수
	static int[] robot = new int[3]; // 0-r,1-c,2-dir
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };// 북-서-남-동 반시계 방향
	static int[][] map;
	static boolean stop;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			robot[i] = Integer.parseInt(st.nextToken());
		}
		if (robot[2] == 1)
			robot[2] = 3;
		else if (robot[2] == 3)
			robot[2] = 1;// 문제에서는 시계방향으로 방향을 주므로 조정

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

		} // map입력
		simul();
		System.out.println(cnt);
	}

	static void clean() {
		int r = robot[0];
		int c = robot[1];
		map[r][c] = 2;// 청소한 칸은 후진을 위해 2로 표시
		cnt++;

	}// 로봇이 있는 현재 칸 청소

	static void detect() {
		boolean chk = false;
		int r = robot[0];
		int c = robot[1];
		int d = robot[2];
		for (int i = 1; i < 5; i++) {//청소할 칸이 있는 경우 무조건 반시계 돌고 시작
			int nr = r + dr[(d + i) % 4];
			int nc = c + dc[(d + i) % 4];// 현재 방향 기준으로 반시계 방향으로 탐색
			if (map[nr][nc] == 0) {
				chk = true;
				robot[0] = nr;
				robot[1] = nc;
				robot[2] = (d + i) % 4;
				clean();
				return;
			} // 청소하지 않은 빈 칸이 탐색된 경우
		}
		if (!chk) {// 탐색되지 않은 경우
			int pr = r - dr[d];
			int pc = c - dc[d];
			if (map[pr][pc] == 2) {
				robot[0] = pr;
				robot[1] = pc;
			} // 후진할 수 있다면 방향 유지한 채로 후진
			else {
				stop = true;
			} // 후진할 수 없다면 시뮬레이션 종료
		}
	}

	static void simul() {
		clean();// 처음 로봇이 있는 칸 청소
		while (!stop) {
			detect();
//			print();
		}

	}

//	static void print() {
//		System.out.println(cnt);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//	}
}
