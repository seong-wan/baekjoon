import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Point loc;
	static boolean[][] map = new boolean[101][101];
	static int n, minSize = Integer.MAX_VALUE;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static List<Point> candidates = new ArrayList<>();
	static Point[] minPoints = new Point[2];
	static Set<Integer>[][] connected = new Set[101][101];

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				connected[i][j] = new HashSet<>();
			}
		}

		st = new StringTokenizer(br.readLine());
		loc = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		n = Integer.parseInt(br.readLine());

		map[loc.x][loc.y] = true;
		int lastDir = -1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			String direction = st.nextToken();
			int dir = 0;
			switch (direction) {
				case "U": {
					break;
				}
				case "D": {
					dir = 1;
					break;
				}
				case "R": {
					dir = 2;
					break;
				}
				case "L": {
					dir = 3;
				}
			}

			int dis = Integer.parseInt(st.nextToken());

			//코너가 되는 상황
			if (lastDir != -1 && dir != lastDir) {
				candidates.add(new Point(loc.x, loc.y));
			}

			lastDir = dir;
			connected[loc.x][loc.y].add(i);

			for (int j = 0; j < dis; j++) {
				loc.x += dx[dir];
				loc.y += dy[dir];

				//기존에 선분이 있는 상황(수직*수평)
				if (map[loc.x][loc.y])
					candidates.add(new Point(loc.x, loc.y));

				map[loc.x][loc.y] = true;
				connected[loc.x][loc.y].add(i);
			}
		}

		candidates.sort((e1, e2) -> e1.y == e2.y ? e1.x - e2.x : e1.y - e2.y);

		int listSize = candidates.size();
		for (int i = 0; i < listSize; i++) {
			for (int j = i + 1; j < listSize; j++) {
				for (int k = j + 1; k < listSize; k++) {
					for (int l = k + 1; l < listSize; l++) {
						Point p1 = candidates.get(i);
						Point p2 = candidates.get(j);
						Point p3 = candidates.get(k);
						Point p4 = candidates.get(l);

						//사각형 조건 만족하는지 확인
						if (p1.y == p2.y && p3.y == p4.y && p1.x == p3.x && p2.x == p4.x) {
							if (!check(p1, p2, p3) || !check(p4, p2, p3))
								continue;

							int squareSize = (p2.x - p1.x) * (p3.y - p1.y);
							if (squareSize < minSize) {
								minSize = squareSize;
								minPoints[0] = p1;
								minPoints[1] = p4;
							}
						}
					}
				}
			}
		}

		if (minSize == Integer.MAX_VALUE) {
			System.out.print(0);
			return;
		}

		System.out.println(minPoints[0].x + " " + minPoints[0].y);
		System.out.print(minPoints[1].x + " " + minPoints[1].y);
	}

	static boolean check(Point p1, Point p2, Point p3) {
		boolean connectA = false;
		boolean connectB = false;

		for (Integer i : connected[p1.x][p1.y]) {
			if (connected[p2.x][p2.y].contains(i))
				connectA = true;

			if (connected[p3.x][p3.y].contains(i))
				connectB = true;
		}

		return connectA && connectB;
	}
}