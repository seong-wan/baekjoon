import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class doc {
	int i;// 문서의 인덱스 값
	int p;// 문서의 중요도

	public doc(int i, int p) {
		super();
		this.i = i;
		this.p = p;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, M, cnt;// 테스트케이스 수, 문서의 개수와 찾고 싶은 문서의 인덱스,문서를 꺼낸 횟수
	static Queue<doc> queue = new ArrayDeque<>();// 문서를 담을 큐
	static Queue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
	// 문서의 중요도를 내림차순으로 내보내는 pq

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			cnt = 0;
			queue.clear();
			pq.clear();// 테케별 초기화
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int p = Integer.parseInt(st.nextToken());
				queue.add(new doc(i, p));// queue에 문서를 담음
				pq.add(p);// pq에 중요도를 담음

			} // 문서 정보 입력
			while (true) {
				int curp = pq.peek();// 제일 높은 우선순위를 봄
				doc curd = queue.poll();// 큐에 있는 문서를 꺼내서 확인해봄

				if (curd.p == curp) {// 꺼낸 문서의 중요도가 내림차순으로 정렬된 pq의 중요도와 같다면
					pq.poll();// 중요도가 같은 문서를 꺼넀으니 pq에서도 빼주고 꺼낸 문서는 넣지 않음
					cnt++;// 문서를 꺼냈으니 증가시켜줌
					if (curd.i == M) {// 꺼낸 문서의 인덱스가 찾고 있는 문서의 인덱스와 같다면
						System.out.println(cnt);
						break;
					}

				} else {
					queue.add(curd);// 우선순위가 다른 문서를 꺼내봤다면 다시 넣음
				}

			}
		}
	}

}
