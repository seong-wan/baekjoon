import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// B의 높은 수에 A의 낮은 수를 차례대로 곱해주고 더하면 됨
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static PriorityQueue<Integer> pqA = new PriorityQueue<>(), pqB = new PriorityQueue<>((n1, n2) -> n2 - n1);
    
	// 정렬을 바로 하기 위해 PQ사용 A는 오름차,B는 내림차
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pqA.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pqB.add(Integer.parseInt(st.nextToken()));
		}

		System.out.println(cal());

	}

	static int cal() {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += pqA.poll() * pqB.poll();
		}
		return ans;
	}//계산 

}
