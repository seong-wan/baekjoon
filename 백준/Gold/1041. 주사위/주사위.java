import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//(N!=1)일 경우 붙어있는 면 3개가 최소여야 하는 경우 - 4
//면 2개 - 8N-12
//면 1개 - 5N^2-16N+12
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long Ndouble, ans;
	static int[] dice = new int[6];
	static int[][] dicePair = {{0, 5}, {1, 4}, {2, 3}}; //서로 같이 있을 수 없는 인덱스 쌍
	static int[] min = {151, 151, 151}; //면 1,2,3개가 보이는 경우의 최소값

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			min[0] = Math.min(min[0], dice[i]);
		}

		//N이 1인 경우 최대값을 가진 면을 제외한 합
		if (N == 1) {
			int max = 0;
			int sum = 0;
			for (int i = 0; i < 6; i++) {
				sum += dice[i];
				max = Math.max(max, dice[i]);
			}

			System.out.print(sum - max);
			return;
		}

		Ndouble = (long)N * N;
		calc();
		ans += min[2] * 4; //면 3개가 보이는 경우
		ans += min[1] * (8 * N - 12); //면 2개가 보이는 경우
		ans += min[0] * (5 * Ndouble - 16 * N + 12); //면 1개가 보이는 경우

		System.out.print(ans);
	}

	static void calc() {
		//붙어있는 면 2개의 합이 최소인 경우
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				int aIdx = dicePair[i][j];
				for (int k = i + 1; k < 3; k++) {
					for (int l = 0; l < 2; l++) {
						int bIdx = dicePair[k][l];
						min[1] = Math.min(min[1], dice[aIdx] + dice[bIdx]);
					}
				}
			}
		}

		//붙어있는 면 3개의 합이 최소인 경우
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					int aIdx = dicePair[0][i];
					int bIdx = dicePair[1][j];
					int cIdx = dicePair[2][k];
					min[2] = Math.min(min[2], dice[aIdx] + dice[bIdx] + dice[cIdx]);
				}
			}
		}
	}
}