import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static Deque<Integer> queue;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int cnt;

	public static void main(String[] args) throws Exception, IOException {
		while (true) {
			String T = br.readLine();
			if (T == null || T.length() == 0)
				break;
			queue = new ArrayDeque<>();
			int t = Integer.parseInt(T);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			} // 큐에 배열 원래 값 저장
			find();
			System.out.print("#" + t + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(queue.poll() + " ");

			}
			System.out.println();
		}

	}

	static void find() {
		cnt = 1;
		while (true) {
			int n = queue.poll();
			n -= cnt;
			if (n <= 0) {

				queue.add(0);
				break;
			} else {
				cnt++;
				if (cnt == 6)
					cnt = 1;
				queue.add(n);
			}
		}
	}
}
