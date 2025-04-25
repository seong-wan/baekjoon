import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int r1, c1, r2, c2, count, maxCount, maxR, maxC;
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

		maxR = r2 - r1;
		maxC = c2 - c1;

		map = new int[maxR + 1][maxC + 1];
		maxCount = (maxR + 1) * (maxC + 1);

		make();

		maxDigit = Math.max(maxDigit, Integer.toString(map[0][0]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[maxR][0]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[0][maxC]).length());
		maxDigit = Math.max(maxDigit, Integer.toString(map[maxR][maxC]).length());
		String format = "%" + maxDigit + "d";

		for (int i = 0; i <= maxR; i++) {
			for (int j = 0; j <= maxC; j++) {
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

		if (0 <= curR && curR <= maxR && 0 <= curC && curC <= maxC) {
			map[curR][curC] = num;
			count++;
		}

		num++;

		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < length; j++) {
					curR += dr[dir];
					curC += dc[dir];

					if (0 <= curR && curR <= maxR && 0 <= curC && curC <= maxC) {
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