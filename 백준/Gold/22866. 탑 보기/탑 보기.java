import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N + 1];
		int[] count = new int[N + 1];
		int[] canSeeNum = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Deque<int[]> stack = new ArrayDeque<>();
		stack.push(new int[] {nums[1], 1});
		for (int i = 2; i <= N; i++) {
			int cur = nums[i];

			while (!stack.isEmpty()) {
				int frontH = stack.peek()[0];

				if (frontH <= cur)
					stack.pop();
				else
					break;
			}

			if (!stack.isEmpty()) {
				int frontIdx = stack.peek()[1];

				if (canSeeNum[i] == 0) {
					canSeeNum[i] = frontIdx;
				}

				count[i] += stack.size();
			}

			stack.push(new int[] {cur, i});
		}

		stack.clear();
		stack.push(new int[] {nums[N], N});
		for (int i = N - 1; i >= 1; i--) {
			int cur = nums[i];

			while (!stack.isEmpty()) {
				int frontH = stack.peek()[0];

				if (frontH <= cur)
					stack.pop();
				else
					break;
			}

			if (!stack.isEmpty()) {
				int frontIdx = stack.peek()[1];

				if (canSeeNum[i] == 0 || i - canSeeNum[i] > frontIdx - i) {
					canSeeNum[i] = frontIdx;
				}

				count[i] += stack.size();
			}

			stack.push(new int[] {cur, i});
		}

		for (int i = 1; i <= N; i++) {
			sb.append(count[i]);

			if (canSeeNum[i] != 0)
				sb.append(" ").append(canSeeNum[i]);

			sb.append("\n");
		}

		System.out.print(sb);
	}
}