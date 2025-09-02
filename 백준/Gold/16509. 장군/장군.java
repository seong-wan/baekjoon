import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] general = new int[2], king = new int[2];
	static boolean[][] visit = new boolean[10][9];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		general[0] = Integer.parseInt(st.nextToken());
		general[1] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		king[0] = Integer.parseInt(st.nextToken());
		king[1] = Integer.parseInt(st.nextToken());

		System.out.print(bfs());
	}

	static int bfs() {
		Deque<int[]> queue = new ArrayDeque<>();

		queue.add(general);
		visit[general[0]][general[1]] = true;
		int time = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					//범위 밖이거나 다른 기물이 있는 곳이면 패스
					if (!canGo(nr, nc) || nr == king[0] && nc == king[1])
						continue;

					//위로 향하는 경우
					if (dir == 0) {
						int stepR = nr - 1;
						int stepC = nc - 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr - 2;
							stepC = nc - 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}
						stepR = nr - 1;
						stepC = nc + 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr - 2;
							stepC = nc + 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}
					}

					//왼쪽으로 향하는 경우
					else if (dir == 1) {
						int stepR = nr - 1;
						int stepC = nc - 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr - 2;
							stepC = nc - 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}

						stepR = nr + 1;
						stepC = nc - 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr + 2;
							stepC = nc - 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}
					}

					//아래로 향하는 경우
					else if (dir == 2) {
						int stepR = nr + 1;
						int stepC = nc - 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr + 2;
							stepC = nc - 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}

						stepR = nr + 1;
						stepC = nc + 1;
						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr + 2;
							stepC = nc + 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}
					}

					//오른쪽으로 향하는 경우
					else {
						int stepR = nr - 1;
						int stepC = nc + 1;

						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr - 2;
							stepC = nc + 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}

						stepR = nr + 1;
						stepC = nc + 1;
						if (canGo(stepR, stepC) && !(stepR == king[0] && stepC == king[1])) {
							stepR = nr + 2;
							stepC = nc + 2;

							if (canGo(stepR, stepC) && !visit[stepR][stepC]) {
								if (stepR == king[0] && stepC == king[1])
									return time;

								visit[stepR][stepC] = true;
								queue.add(new int[] {stepR, stepC});
							}
						}
					}
				}
			}
			time++;
		}

		return -1;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 9;
	}
}