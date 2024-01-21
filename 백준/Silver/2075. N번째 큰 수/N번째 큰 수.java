import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//밑의 N개의 수를 pq에 넣고 pq에서 하나씩 빼면서
//빠진 수의 바로 위 숫자를 넣는 식으로 N번째 큰 수를 구함
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e2 - e1);
	static int N, ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 0; i < N; i++) {
			ans = pq.poll();
		}
		System.out.println(ans);
	}
}