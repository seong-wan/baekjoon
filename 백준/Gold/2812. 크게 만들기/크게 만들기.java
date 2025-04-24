import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, count;
	static Deque<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String input = br.readLine();

		queue.add(input.charAt(0) - '0');
		for (int i = 1; i < N; i++) {
			int temp = input.charAt(i) - '0';

			while (!queue.isEmpty()) {
				int front = queue.pollLast();

				if (front >= temp) {
					queue.add(front);
					break;
				}

				count++;
				if (count == K)
					break;
			}

			if (count == K) {
				while (!queue.isEmpty()) {
					sb.append(queue.poll());
				}

				sb.append(input, i, N);
				break;
			}

			queue.add(temp);
		}

		if (count != K) {
			for (int i = 0; i < N - K; i++) {
				sb.append(queue.poll());
			}
		}

		System.out.println(sb);
	}
}
