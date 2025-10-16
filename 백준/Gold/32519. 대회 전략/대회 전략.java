import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, T, ans;
	static int[][] time = new int[3][10001];
	static int[][] scores = new int[3][10001];
	static List<int[]>[] problem = new List[3];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		T = in.nextInt();

		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= N; j++) {
				scores[i][j] = scores[i][j - 1] + in.nextInt();
			}

			problem[i] = new ArrayList<>();
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= N; j++) {
				time[i][j] = time[i][j - 1] + in.nextInt();
			}
		}

		for (int i = 0; i < 3; i++) {
			int maxScore = -1;

			for (int j = 0; j <= N; j++) {
				if (scores[i][j] > maxScore && time[i][j] <= T) {
					maxScore = scores[i][j];
					problem[i].add(new int[] {time[i][j], scores[i][j]});
				}
			}
		}

		//문제 3의 특정 서브태스크까지 풀었다고 가정하고 두 문제에서 가능한 최대값을 투포인터를 통해 구함
		for (int[] three : problem[2]) {
			int restTime = T - three[0];
			int tempBest = 0;

			int left = 0;
			int right = problem[1].size() - 1;

			while (left < problem[0].size() && right >= 0) {
				int totalTime = problem[0].get(left)[0] + problem[1].get(right)[0];

				if (totalTime <= restTime) {
					tempBest = Math.max(tempBest, problem[0].get(left)[1] + problem[1].get(right)[1]);
					left++;
				} else {
					right--;
				}
			}

			ans = Math.max(ans, tempBest + three[1]);
		}

		System.out.print(ans);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}