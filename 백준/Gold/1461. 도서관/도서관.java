import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//절대값이 큰 지점을 다시 원점으로 돌아오지 않게 마지막에 가게 처리
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static StringTokenizer st;
	//절대값이 큰 수부터 나오게 정렬
	static PriorityQueue<Integer> positive = new PriorityQueue<>((e1, e2) -> e2 - e1);
	static PriorityQueue<Integer> negative = new PriorityQueue<>((e1, e2) -> e1 - e2);
	static int ans;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num < 0)
				negative.add(num);
			else
				positive.add(num);
		}

		int bigPositive = positive.isEmpty() ? 0 : positive.peek();
		int bigNegative = negative.isEmpty() ? 0 : negative.peek();

		if (bigPositive >= -bigNegative) {
			ans += bigPositive;
			for (int i = 0; i < M; i++) {
				if (positive.isEmpty())
					break;

				positive.poll();
			}
		} else {
			ans += -bigNegative;
			for (int i = 0; i < M; i++) {
				if (negative.isEmpty())
					break;

				negative.poll();
			}
		}

		while (!positive.isEmpty()) {
			ans += 2 * positive.peek();

			for (int i = 0; i < M; i++) {
				if (positive.isEmpty())
					break;

				positive.poll();
			}
		}
		while (!negative.isEmpty()) {
			ans += 2 * -negative.peek();

			for (int i = 0; i < M; i++) {
				if (negative.isEmpty())
					break;

				negative.poll();
			}
		}

		System.out.print(ans);
	}
}