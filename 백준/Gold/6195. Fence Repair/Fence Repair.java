import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static long ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e1 - e2);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		while (pq.size() != 1) {
			int sum = pq.poll() + pq.poll();

			ans += sum;
			pq.add(sum);
		}

		System.out.print(ans);
	}
}