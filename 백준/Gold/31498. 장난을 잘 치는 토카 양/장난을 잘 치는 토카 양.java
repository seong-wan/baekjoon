import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long A, C;
	static long B, D, K;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		C = Long.parseLong(st.nextToken());
		D = Long.parseLong(st.nextToken());

		K = Long.parseLong(br.readLine());

		//토카가 집에 들어가는 최소 이동 횟수 구하기
		long cnt = 0;

		//등속도
		if (K == 0) {
			cnt = A / B + (A % B == 0 ? 0 : 1);
		} else {
			long max = (B - 1) / K + 1;

			//집에 도달할 수 없는 경우
			if ((B + B - (max - 1) * K) * max / 2 < A) {
				System.out.print(-1);
				return;
			}

			long left = 1;
			long right = max;

			while (left <= right) {
				long mid = (left + right) >> 1;
				long dis = (B + B - (mid - 1) * K) * mid / 2;

				if (dis >= A)
					right = mid - 1;
				else
					left = mid + 1;
			}

			cnt = left;
		}

		//토카가 집에 들어왔을 때 돌돌이가 집에 도착했는지 확인
		if (D * cnt >= A + C)
			System.out.print(-1);
		else
			System.out.print(cnt);
	}
}