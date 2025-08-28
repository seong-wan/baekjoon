//N을 비트로 표현했을 때
//1의 개수와 K 가 같거나 작을 때 - 답 0
//1의 개수가 K보다 클 때 - 상위 비트 1을 K-1개 만큼 0으로 바꾸고 해당 값보다 크지만 최소인 2의 제곱수를 구한 뒤 해당 값을 뺌

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int bitCount = 0;
		int ceilIdx = -1;

		for (int i = 23; i >= 0; i--) {
			if ((N & (1 << i)) != 0) {
				if (bitCount == K - 1 && ceilIdx == -1) {
					ceilIdx = i + 1;
				}

				bitCount++;

				if (bitCount < K) {
					N = N - (1 << i);
				}
			}
		}

		if (bitCount <= K) {
			System.out.print(0);
			return;
		}

		System.out.print((1 << ceilIdx) - N);
	}
}