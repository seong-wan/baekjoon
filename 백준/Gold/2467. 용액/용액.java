import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, turn, ans = Integer.MAX_VALUE;
	static int[] liquid;
	static int[] result = new int[2];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		turn = N - 1;// 처음 양수를 만날 때의 값을 기록할 변수
		liquid = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		} // 용액 값 입력

		for (int i = 0; i < N; i++) {
			if (liquid[i] > 0) {
				turn = i;
				break;
			}
		} // 처음 0이상인 산성값이 나올 때

		if (turn >= 2) {
			ans = Math.abs(liquid[turn - 2] + liquid[turn - 1]);
			result[0] = liquid[turn - 2];
			result[1] = liquid[turn - 1];
		} // 음수의 개수가 2 이상일 때 제일 큰 음수 2개를 합친 값
		if (N - turn >= 2) {
			if (liquid[turn] + liquid[turn + 1] < ans)
				;
			ans = liquid[turn] + liquid[turn + 1];
			result[0] = liquid[turn];
			result[1] = liquid[turn + 1];
		} // 양수의 개수가 2 이상일 때 제일 작은 양수 2개를 합친 값

		for (int i = 0; i < turn; i++) {
			int num = Math.abs(liquid[i] + liquid[turn]);
			int tresult[] = new int[] { liquid[i], liquid[turn] };
			for (int j = turn + 1; j < N; j++) {
				int temp = Math.abs(liquid[i] + liquid[j]);
				if (temp < num) {
					num = temp;
					tresult[0] = liquid[i];
					tresult[1] = liquid[j];
				} else
					break;// 음수와 양수의 비교에서 앞의 수보다 큰 값이 나오면 더 이상 비교x
			}
			if (num < ans) {
				ans = num;
				result[0] = tresult[0];
				result[1] = tresult[1];
			}
		}

		System.out.println(result[0] + " " + result[1]);
	}

}