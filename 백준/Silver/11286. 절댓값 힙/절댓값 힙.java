import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {

			if (Math.abs(o1) == Math.abs(o2))
				return o1 - o2;
			else
				return Math.abs(o1) - Math.abs(o2);
		}//절댓값이 같다면 그냥 수로 비교 절댓값이 다르다면 절댓값으로 비교
	});

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd != 0) {
				pq.add(cmd);
			} else {
				if (!pq.isEmpty())
					sb.append(pq.poll()).append("\n");
				else
					sb.append(0).append("\n");//pq가 비어있다면 0출력
			}
		}

		System.out.print(sb);
	}

}
