import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, H, type, ans;
	static int[] input = new int[1000000];
	static int[][] pattern = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		type = Math.min(N, H);

		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			char temp = s.charAt(i);

			if (temp == 'R') {
				input[i] = 1;
			} else if (temp == 'W') {
				input[i] = 2;
			}
		}

		//3가지 용맥으로 불가능한 경우
		if (type >= 4) {
			System.out.print(-1);
			return;
		}

		//변환할 필요가 없는 경우
		if (type == 1) {
			System.out.print(0);
			return;
		}

		if (type == 2) {
			for (int i = 1; i < N; i++) {
				if (input[i] == input[i - 1]) {
					input[i] = 4;
					ans++;
				}
			}

			System.out.print(ans);
			return;
		}

		if (type == 3) {
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < 6; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if (input[j] != pattern[i][j % 3])
						temp++;
				}

				ans = Math.min(ans, temp);
			}

			System.out.print(ans);
		}
	}
}