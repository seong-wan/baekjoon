import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);
	// 내림차순 정렬
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd != 0)
				pq.add(cmd);// 0이 아닌 수가 입력된 경우
			else {
				if (pq.isEmpty())
					sb.append(0 + "\n");
				else
					sb.append(pq.poll() + "\n");
			} // 0을 입력 받았을 때
		}
		System.out.println(sb);
	}

}
