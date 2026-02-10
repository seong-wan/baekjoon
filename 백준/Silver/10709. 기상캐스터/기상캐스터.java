import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int H, W;
	static int[][] map;
	static Deque<int[]> queue = new ArrayDeque<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(map[i], -1);
		}

		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				if (s.charAt(j) == 'c')
					queue.add(new int[] {i, j});
			}
		}

		int stage = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				if (map[cr][cc] != -1)
					continue;

				map[cr][cc] = stage;

				if (cc + 1 == W)
					continue;

				queue.add(new int[] {cr, cc + 1});
			}
			stage++;
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}