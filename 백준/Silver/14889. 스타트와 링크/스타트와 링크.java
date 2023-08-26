import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, ans = Integer.MAX_VALUE;// 사람의 수 , 능력치의 합,최소값
	static int[][] ability, synergy;// 능력치를 입력 받을 배열, 시너지를 따로 계산해놓을 배열
	static int[] tgt;// 조합

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		ability = new int[N + 1][N + 1];
		synergy = new int[N + 1][N + 1];// 0은 더미
		tgt = new int[N / 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());

			}
		} // 능력치의 합을 구하면서 입력 받음
		syn();// 두 사람 사이의 시너지를 미리 계산해서 배열에 넣음
		comb(0, 1);//1의 번호부터 N번호 중 N/2 명의 사람의 조합을 구함
		System.out.println(ans);
	}

	static void comb(int tgtidx, int num) {
		if (tgtidx == N / 2) {
			cal();
			return;
		}
		if (num == N + 1)
			return;
		tgt[tgtidx] = num;
		comb(tgtidx + 1, num + 1);
		comb(tgtidx, num + 1);
	}//한 쪽 팀의 조합을 구함

	static void cal() {
		int tgtsum = 0;
		int remainsum = 0;
		boolean[] visit = new boolean[N + 1];// 0은 더미
		for (Integer num : tgt) {
			visit[num] = true;//뽑은 사람들의 번호는 true로 나머지는 false로 해서 팀을 나눔
		}
		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (visit[i] == true && visit[j] == true)
					tgtsum += synergy[i][j];//뽑힌 사람들의 시너지의 합
				if (visit[i] == false && visit[j] == false)
					remainsum += synergy[i][j];//뽑히지 않은 사람들의 시너지의 합
			}
		}

		ans = Math.min(ans, Math.abs(tgtsum - remainsum));// 양팀 시너지 차이의 최소값을 구함

	}

	static void syn() {
		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				synergy[i][j] = ability[i][j] + ability[j][i];
			}
		}
	}// 두 사람 사이의 시너지를 미리 계산해서 배열에 넣음
}