import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, R, day, cnt;// 땅의 크기,L명 이상 R명 이하
	static int[][] map, temp;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static List<Integer> union = new ArrayList<>();// 연합별 인구 수

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력

		while (true) {
			day++;
			temp = new int[N][N];//day별로 연합마다 cnt를 바꿔가며 체크하기 위함
			union.clear();//day별 연합마다의 인구수 저장 리스트 초기화
			if (!find())
				break;
			commit();
		}
		System.out.println(day - 1);

	}

	static boolean find() {
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == 0) {
					cnt++;
					bfs(i, j);

				}
			}
		}
		if (cnt == N * N)//연합이 이루어지지 않는 경우
			return false;
		return true;

	}

	static void bfs(int i, int j) {
		int sum = 0;
		int count = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { i, j });
		temp[i][j] = cnt;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			sum += map[cr][cc];
			count++;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc) && Math.abs(map[cr][cc] - map[nr][nc]) >= L
						&& Math.abs(map[cr][cc] - map[nr][nc]) <= R) {
					queue.add(new int[] { nr, nc });
					temp[nr][nc] = cnt;// 방문처리를 하면서 연합별 체크
				}
			}
		}
		union.add(sum / count);// cnt-1번째 인덱스에 cnt로 방문처리된 연합의 인구 수 저장
	}

	static void commit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = union.get(temp[i][j] - 1);// 저장된 인구 수 값으로 변경
			}
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N && temp[nr][nc] == 0)
			return true;
		return false;
	}

}