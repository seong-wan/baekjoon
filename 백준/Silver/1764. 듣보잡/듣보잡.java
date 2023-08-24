import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static HashMap<String, Integer> hashmap = new HashMap<>();
	static PriorityQueue<String> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			hashmap.put(br.readLine(), 1);
		}
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if (hashmap.get(s) != null)
				pq.add(s);

		}
		int size = pq.size();
		sb.append(size + "\n");

		for (int j = 0; j < size; j++) {
			sb.append(pq.poll() + "\n");
		}
		System.out.println(sb);
	}

}
