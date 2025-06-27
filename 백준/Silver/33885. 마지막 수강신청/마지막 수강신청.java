import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static StringTokenizer st;
	static List<int[]>[] sub;
	static int[] scores;
	static boolean[][] chk;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		scores = new int[N];
		sub = new List[N];
		for (int i = 0; i < N; i++) {
			sub[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			scores[i] = c;

			for (int j = 0; j < s; j++) {
				String day = st.nextToken();
				int hour = Integer.parseInt(st.nextToken());
				int dayNum = 0;

				if (day.equals("TUE"))
					dayNum = 1;
				else if (day.equals("WED"))
					dayNum = 2;
				else if (day.equals("THU"))
					dayNum = 3;
				else if (day.equals("FRI"))
					dayNum = 4;

				sub[i].add(new int[] {dayNum, hour});
			}
		}

		for (int i = 1; i <= Math.pow(2, N) - 1; i++) {
			if (check(i)) {
				System.out.println("YES");
				return;
			}
		}

		System.out.print("NO");
	}

	static boolean check(int mask) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) == 1 << i) {
				sum += scores[i];
			}
		}

		if (sum < M)
			return false;

		chk = new boolean[5][24];

		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) == 1 << i) {
				for (int[] time : sub[i]) {
					if (chk[time[0]][time[1]])
						return false;

					chk[time[0]][time[1]] = true;
				}
			}
		}

		return true;
	}
}