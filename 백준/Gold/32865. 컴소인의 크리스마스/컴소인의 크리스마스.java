import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, sum;
	static StringBuilder sb = new StringBuilder();
	static Deque<Integer> solved = new ArrayDeque<>();
	static Deque<problem> problems = new ArrayDeque<>();

	static class problem {
		int num;
		int cnt;

		public problem(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			int cnt = Integer.parseInt(br.readLine());
			sum += cnt;

			if (sum >= N) {
				System.out.println(-1);
				return;
			}

			if (cnt == 0)
				solved.add(i);
			else
				problems.add(new problem(i, cnt));
		}

		if (sum + N != 2 * N - 1) {
			System.out.println(-1);
			return;
		}

		for (int i = 0; i < 2 * N - 1; i++) {
			//맞았습니다
			if (i % 2 == 0) {
				sb.append(solved.poll());
			}
			//틀렸습니다.
			else {
				problem temp = problems.poll();
				temp.cnt--;

				sb.append(temp.num);

				if (temp.cnt == 0)
					solved.add(temp.num);
				else
					problems.add(temp);
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}
}