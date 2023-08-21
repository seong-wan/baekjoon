import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, level = 2;// 맵의 크기, 아기 상어의 사이즈
	static int[][] map;
	static int[][] visited;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };// 상,좌,우,하
	static int[] fish = new int[7];// 0은 dummy 1,2,3,4,5,6
	static int exp, time;
	static int shark_r, shark_c;// 상어의 초기 위치
	// 상어의 사이즈별 먹을 수 있는 물고기의 수 - 한 단계 낮은 물고기의 전체 수
	static List<int[]> eat_fish = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 9) {
					for (int j2 = 6; j2 >= map[i][j]; j2--) {
						fish[j2] += 1;

					}
				} // 맵을 입력하면서 물고기가 존재한다면 fish 배열에 체크
				if (map[i][j] == 9) {
					shark_r = i;
					shark_c = j;
				}
			}
		}
		shark_move(shark_r, shark_c);// 상어의 위치부터 시작
		System.out.println(time);
	}

	static void shark_move(int r, int c) {
		eat_fish.clear();// 초기화
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { r, c });
		visited[r][c] = 1;// 깊이(시간) 체크용 0부터 시작해야하므로 깊이는 visited 배열의 값의 -1
		while (!queue.isEmpty()) {
			if (fish[level - 1] == 0)
				return;
			int size = queue.size();

			for (int k = 0; k < size; k++) {
				int[] v = queue.poll();
				int sr = v[0];
				int sc = v[1];

				for (int i = 0; i < 4; i++) {
					int nr = sr + dr[i];
					int nc = sc + dc[i];
					if (nr >= 0 && nr <= N - 1 && nc >= 0 && nc <= N - 1 && visited[nr][nc] == 0
							&& map[nr][nc] <= level) {
						visited[nr][nc] = visited[sr][sc] + 1;// 깊이 마다 +1씩 체크로 시간 체크
						queue.add(new int[] { nr, nc });
						if (map[nr][nc] < level && map[nr][nc] != 0) {
							eat_fish.add(new int[] { nr, nc });

						}

					}
				}

			}
			if (!eat_fish.isEmpty()) {
				Collections.sort(eat_fish, (n1, n2) -> n1[0] == n2[0] ? n1[1] - n2[1] : n1[0] - n2[0]);
				eat(eat_fish.get(0)[0], eat_fish.get(0)[1]);
				shark_move(eat_fish.get(0)[0], eat_fish.get(0)[1]);
			} // 물고기가 같은 거리에 있을 때 위,좌 우선으로 먹기 위해 같은 거리에 있는 물고기들의 정보를 받아서 r,c기준으로 정렬
		}
	}

	static void eat(int r, int c) {

		map[r][c] = 0;// 물고기 먹은 자리 0으로 표시
		time += visited[r][c] - 1;// 물고기를 먹었을 때까지 걸린 시간을 더해줌
		exp += 1;
		map[shark_r][shark_c] = 0;
		map[r][c] = 9;
		shark_r = r;
		shark_c = c;// 먹은 위치가 현재 상어의 위치
		for (int i = 1; i <= 6; i++) {
			fish[i] -= 1;
		} // 사이즈별 먹을 수 있는 물고기 양 -1
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);

		} // 방문 배열 초기화
		if (exp == level) {// 물고기를 사이즈만큼 먹었을 경우
			if (level == 7)
				return;// 사이즈가 7이상인 경우 전체 물고기를 먹을 수 있으므로 더 사이즈업x
			level += 1;// 사이즈 +1
			exp = 0;// 먹은 물고기 0으로 초기화

		}
	}

//	static void print() {
//		System.out.println("시간 : " + time);
//		System.out.println("먹은 물고기 :" + exp);
//		System.out.println("사이즈 : " + level);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//
//		}
//		System.out.println();
//	}디버깅용

}