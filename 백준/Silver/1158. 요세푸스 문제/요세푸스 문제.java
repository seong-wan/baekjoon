import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static Deque<Integer> circle = new ArrayDeque<Integer>();// 입력 값 저장 덱
	static Deque<Integer> answer = new ArrayDeque<Integer>();// 답 저장 덱

	public static void main(String[] args) throws IOException {
		input();
		kill();

		System.out.println(answer.toString().replace('[', '<').replace(']', '>'));
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N + 1; i++) {
			circle.add(i);

		} // circle에 1~N까지의 수를 채워줌
	}

	static void kill() {
		while (!circle.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				circle.add(circle.poll());
			}
			answer.add(circle.poll());
		}
	}// 순환하면서 K번째 수마다 제거

}
