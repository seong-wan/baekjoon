import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static boolean[] prime = new boolean[9001];// 9000수까지 소수 확인을 하기 위해
	static int[] tgt;// 조합을 구하기 위한 배열
	static int[] H;// 소 무게들을 담을 배열
	static int ans;// 조합으로 나온 인덱스 값을 이용해 얻은 무게의 합을 담을 배열
	static Queue<Integer> pq = new PriorityQueue<Integer>();// 나온 소수값 무게들을 정렬

	public static void main(String[] args) throws IOException {
		input();
		comb_chk(0, 0);
		if (pq.isEmpty())
			System.out.println(-1);
		else {
			int size = pq.size();
			for (int j = 0; j < size; j++) {
				sb.append(pq.poll() + " ");
			} // pq에서 오름차순으로 나와서 sb에 담김
			System.out.println(sb);
		}

	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tgt = new int[M];
		H = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());

		}
	}// 입력과 소들의 무게를 배열에 담음

	static boolean primechk(int n) {
		prime[0] = prime[1] = true;// 1은 소수가 아니므로 true
		for (int i = 2; i * i <= 9000; i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= 9000; j += i) {
					prime[j] = true;// 소수가 아니면 true

				}
			}

		}
		return prime[n];

	}// 소수면 false를 반환하는 함수

	static void comb_chk(int tgtIdx, int num) {
		ans = 0;
		if (tgtIdx == tgt.length) {
			for (int i : tgt) {
				ans += H[i];
			}
			if (!primechk(ans) && !pq.contains(ans))
				pq.add(ans);

			return;
		}
		if (num == N)
			return;
		tgt[tgtIdx] = num;
		comb_chk(tgtIdx + 1, num + 1);
		comb_chk(tgtIdx, num + 1);

	}// 0~N-1까지 수의 조합을 구하고 구해진 수를 인덱스로 이용하여
		// 가능한 M마리 소들의 무게 합을 모두 구한 뒤 소수이고 pq에 없다면 pq에 담음

}
