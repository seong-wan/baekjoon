import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int r, c, key;

		Node(int r, int c, int key) {// 민식이의 위치, 키 정보 저장
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}

	static Queue<Node> queue = new ArrayDeque<>();// bfs탐색을 위한 큐
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static char[][] map;
	static boolean[][][] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][64];// 열쇠 상태에 대한 정보 64가지

		for (int r = 0; r < N; ++r) {
			char[] s = br.readLine().toCharArray();
			for (int c = 0; c < M; ++c) {
				map[r][c] = s[c];

				if (map[r][c] == '0') {
					queue.add(new Node(r, c, 0));
					visited[r][c][0] = true;
					map[r][c] = '.';// 민식이의 초기 위치 파악 후 이동을 위해 .으로 처리
				}
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		int ans = 0;// 이동 횟수

		while (!queue.isEmpty()) {
			int size = queue.size();
			ans++;

			for (int i = 0; i < size; ++i) {
				Node cur = queue.poll();
				int cr = cur.r;
				int cc = cur.c;
				int key = cur.key;

				for (int dir = 0; dir < 4; ++dir) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					int nk = key;

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (visited[nr][nc][key] || map[nr][nc] == '#')// 방문 했거나 벽을 만난 경우
						continue;

					if (map[nr][nc] == '1')// 도착한 경우
						return ans;

					else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {// 비트마스킹으로 열쇠를 주웠을 때 key의 값 처리
						int ck = 1 << (map[nr][nc] - 'a');
						if ((key & ck) != ck)
							nk |= ck;
					} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {//열쇠 정보별 방문 가능 여부 확인
						int cd = 1 << (map[nr][nc] - 'A');
						if ((key & cd) != cd)
							continue;
					}

					queue.add(new Node(nr, nc, nk));
					visited[nr][nc][key] = true;
				}
			}
		}
		return -1;
	}
}