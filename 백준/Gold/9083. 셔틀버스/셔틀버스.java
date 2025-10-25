import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static PriorityQueue<int[]> schedule = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);
	static PriorityQueue<Integer> A = new PriorityQueue<>((e1, e2) -> e1 - e2);
	static PriorityQueue<Integer> B = new PriorityQueue<>((e1, e2) -> e1 - e2);
	static Deque<Integer> temp = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			A.clear();
			B.clear();

			int passTime = Integer.parseInt(br.readLine());

			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), ":");
				int hour = Integer.parseInt(st.nextToken());
				int min = Integer.parseInt(st.nextToken());

				schedule.add(new int[] {hour * 60 + min, 0});
			}

			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), ":");
				int hour = Integer.parseInt(st.nextToken());
				int min = Integer.parseInt(st.nextToken());

				schedule.add(new int[] {hour * 60 + min, 1});
			}

			while (!schedule.isEmpty()) {
				int[] cur = schedule.poll();
				int time = cur[0];
				int loc = cur[1];

				if (loc == 0) {
					if (!A.isEmpty() && A.peek() <= time) {
						while (!A.isEmpty() && A.peek() <= time) {
							temp.push(A.poll());
						}

						temp.pop();

						while (!temp.isEmpty()) {
							A.add(temp.pop());
						}
					} else if (!B.isEmpty() && B.peek() + passTime <= time) {
						while (!B.isEmpty() && B.peek() + passTime <= time) {
							temp.push(B.poll());
						}

						temp.pop();

						while (!temp.isEmpty()) {
							B.add(temp.pop());
						}
					}

					B.add(time + passTime);
				} else {
					if (!B.isEmpty() && B.peek() <= time) {
						while (!B.isEmpty() && B.peek() <= time) {
							temp.push(B.poll());
						}

						temp.pop();

						while (!temp.isEmpty()) {
							B.add(temp.pop());
						}
					} else if (!A.isEmpty() && A.peek() + passTime <= time) {
						while (!A.isEmpty() && A.peek() + passTime <= time) {
							temp.push(A.poll());
						}

						temp.pop();

						while (!temp.isEmpty()) {
							A.add(temp.pop());
						}
					}

					A.add(time + passTime);
				}
			}

			sb.append(A.size() + B.size()).append("\n");
		}
		System.out.print(sb);
	}
}