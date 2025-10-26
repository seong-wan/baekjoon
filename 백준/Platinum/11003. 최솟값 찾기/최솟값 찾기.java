import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, L;
	static int[] input = new int[5000000];
	static Deque<Integer> deque = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());

			//범위 밖의 인덱스 제거
			if (!deque.isEmpty() && deque.peek() == i - L)
				deque.poll();

			//범위 안의 최솟값이 아닌 인덱스 제거
			while (!deque.isEmpty() && input[deque.peekLast()] > input[i]) {
				deque.pollLast();
			}

			deque.add(i);

			sb.append(input[deque.peek()]).append(" ");
		}

		System.out.print(sb);
	}
}