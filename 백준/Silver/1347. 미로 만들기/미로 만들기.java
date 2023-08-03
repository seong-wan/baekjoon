import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] map = new char[99][99]; // 최대 값으로 배열 선언
	static int y = 49, x = 49, start_y = 49, end_y = 49, start_x = 49, end_x = 49; // map 중간에서 출발(앞으로 쭉 가도 맵 밖으로 안 감)
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, -1, 0, 1 }; // 시계 방향(R입력 방향)
	static int dir; // 방향 인덱스

	public static void main(String[] args) throws IOException {
		search();// 입력에 따라 가는 길에 .찍기
		print();// 미로 맵 범위 빈 칸에 #찍고 출력
	}

	static void search() throws IOException {
		map[y][x] = '.'; // 첫 칸에 .
		int N = Integer.parseInt(br.readLine());
		String command = br.readLine();
		char cmd[] = command.toCharArray();
		for (int i = 0; i < N; i++) {
			switch (cmd[i]) {
			case 'F':
				y += dy[dir];
				x += dx[dir];
				map[y][x] = '.';
				if (y < start_y)
					start_y = y;
				if (y > end_y)
					end_y = y;
				if (x < start_x)
					start_x = x;
				if (x > end_x)
					end_x = x;
				break; // F인 경우 가야하는 방향으로 이동 후 .찍고 맵 범위 확인을 위해 시작점과 끝 확인
			case 'R':
				dir++;
				if (dir == 4)
					dir = 0;
				break; // R인 경우 방향 인덱스 +1 범위 넘어갈 시 3->0으로 이동
			case 'L':
				dir--;
				if (dir == -1)
					dir = 3;
				break;// L인 경우 방향 인덱스 -1 범위 넘어갈 시 0->3으로 이동

			}

		}
	}

	static void print() {
		for (int i = start_y; i <= end_y; i++) {
			if (i != start_y)
				System.out.println();//출력 시 첫 줄에 빈 줄 나오는 거 방지
			for (int j = start_x; j <= end_x; j++) {
				if (map[i][j] != '.')
					map[i][j] = '#';
				System.out.print(map[i][j]);
			}

		}

	}// 맵 범위 빈 칸에 #찍고 출력
}
