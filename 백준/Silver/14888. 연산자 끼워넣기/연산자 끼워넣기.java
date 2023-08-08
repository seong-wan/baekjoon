import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;// 숫자의 개수
	static int[] nums;// 숫자들을 담을 배열
	static int[] chk = new int[4]; // 연산자의 개수를 담을 배열
	static int[] tgt;// 연산자의 중복순열을 구하기 위한 배열
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;// 최소,최대
	static int result;// 연산 결과 값을 담을 변수

	public static void main(String[] args) throws NumberFormatException, IOException {
		set();
		perm_chk(0);
		System.out.println(max);
		System.out.println(min);
	}

	static void set() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		tgt = new int[N - 1];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		} // 배열에 숫자들 추가

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			chk[i] = Integer.parseInt(st.nextToken());

		} // 연산자 개수 배열에 추가
	}

	static void perm_chk(int tgtidx) {

		if (tgtidx == N - 1) {
			result = operator(tgt[0], nums[0], nums[1]);
			for (int i = 1; i < N - 1; i++) {
				result = operator(tgt[i], result, nums[i + 1]);
			}
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		} // N-1개의 순열을 구하는 기저 조건 만족하면 결과값들의 min과 max를 구하고 리턴
		for (int i = 0; i < 4; i++) {
			if (chk[i] == 0) {
				continue;
			}
			tgt[tgtidx] = i;
			chk[i]--;
			perm_chk(tgtidx + 1);
			chk[i]++;
		} // 연산자의 개수를 담고 있는 chk배열로 뽑는 수 체크

	}

	static int operator(int i, int x, int y) {
		switch (i) {
		case 0:
			return x + y;

		case 1:
			return x - y;

		case 2:
			return x * y;

		case 3:
			return x / y;

		}
		return 0;
	}// 연산자별 연산(0:+,1:-,2:*,3:/)
}
