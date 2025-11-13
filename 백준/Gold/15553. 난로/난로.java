import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e1 - e2);

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int prev = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			pq.add(temp - prev - 1);
			prev = temp;
		}

		ans = N;
		for (int i = 0; i < N - K; i++) {
			ans += pq.poll();
		}

		System.out.print(ans);
	}
}