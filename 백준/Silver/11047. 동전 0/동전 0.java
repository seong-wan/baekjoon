import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K, cnt;// 가지고 있는 동전의 종류, 원하는 가치의 합,필요한 최소 동전의 개수
	static int[] coin;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());

		}
		find();
		System.out.println(cnt);

	}

	static void find() {
		for (int i = N - 1; i >= 0; i--) {// 오름차순이므로 뒤에서부터 탐색
			cnt += K / coin[i];
			K %= coin[i];
			if (K == 0)
				return;
		}

	}

}
