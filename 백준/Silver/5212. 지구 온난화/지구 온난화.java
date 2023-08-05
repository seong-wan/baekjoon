
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static char[][] map, result_map;// 기존 맵을 바꾸면 연쇄 작용으로 땅이어야 할 곳도 바다로 바뀜
	static String in_m;// 맵 입력을 위해
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };// 상,우,하,좌(시계방향)탐색
	static boolean chk;// 바다로 바뀌지 않았을 때를 체크 하기 위함
	static int start_y, end_y, start_x, end_x;// 출력할 맵 범위 체크

	public static void main(String[] args) throws IOException {
		start();
		after();
		print();

	}

	static void start() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 2][C + 2]; // 지도 밖 바다 구현 위해 크기 +2씩
		result_map = new char[R + 2][C + 2];
		for (int i = 0; i < R + 2; i++) {
			Arrays.fill(result_map[i], '.');// 결과 맵 전체를 바다로 초기화
		}
		Arrays.fill(map[0], '.');
		Arrays.fill(map[R + 1], '.');// 지도 밖을 바다로 채우기 위해
		for (int i = 1; i < R + 1; i++) {
			in_m = br.readLine();
			Arrays.fill(map[i], '.');// 지도 밖을 바다로 채우기 위해
			for (int j = 1; j < C + 1; j++) {
				map[i][j] = in_m.charAt(j - 1);// 1번째 칸에 String의 0번째부터 들어감

			}
		}

	}// 기존 지도 + 지도 밖 바다 구성(index 1~R,1~C까지가 기존 지도)

	static void after() {
		start_y = R;
		end_y = 1;
		start_x = C;
		end_x = 1;

		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				int near = 0;// 인접 바다 수 체크
				chk = false;// 바다로 바뀌지 않으면 false 바뀌면 true
				if (map[i][j] == 'X') {
					for (int dir = 0; dir < 4; dir++) {// 4방향 인접 바다 수 탐색
						if (map[i + dy[dir]][j + dx[dir]] == '.') {
							near++;
							if (near == 3) {
								chk = true;
								break;
							} // 인접 바다 수가 3이 되면 탐색을 종료
						}
					}
					if (chk == false) {
						result_map[i][j] = 'X';
						if (i < start_y)
							start_y = i;
						if (i > end_y)
							end_y = i;
						if (j < start_x)
							start_x = j;
						if (j > end_x)
							end_x = j;
					} // 땅이 바다로 바뀌지 않고 그대로일 때 출력을 위한 맵 범위 체크
				}

			}

		}
	}

	static void print() {
		for (int i = start_y; i <= end_y; i++) {
			for (int j = start_x; j <= end_x; j++) {
				sb.append(result_map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}// 변화된 맵 출력

}