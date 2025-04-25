import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int r1, c1, r2, c2, count, maxCount;
	static int length = 1, num = 1;
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static int maxDigit = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		map = new int[r2 - r1 + 1][c2 - c1 + 1];
		maxCount = (r2 - r1 + 1) * (c2 - c1 + 1);

		make();

		maxDigit = Math.max(maxDigit, Integer.toString(map[0][0]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[r2 - r1][0]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[0][c2 - c1]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[r2 - r1][c2 - c1]).length());
		String format = "%" + maxDigit + "d";

		for (int i = 0; i <= r2 - r1; i++) {
			for (int j = 0; j <= c2 - c1; j++) {
				sb.append(String.format(format, map[i][j])).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void make() {
		int curR = -r1;
		int curC = -c1;
		int dir = 0;

		if (0 <= curR && curR <= r2 - r1 && 0 <= curC && curC <= c2 - c1) {
			map[curR][curC] = num;
			count++;
		}

		num++;

		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < length; j++) {
					curR += dr[dir];
					curC += dc[dir];

					if (0 <= curR && curR <= r2 - r1 && 0 <= curC && curC <= c2 - c1) {
						map[curR][curC] = num;
						count++;
					}

					num++;

					if (count == maxCount)
						break;
				}
				if (count == maxCount)
					break;

				dir = (dir + 1) % 4;
			}
			if (count == maxCount)
				break;

			length++;
		}
	}
}