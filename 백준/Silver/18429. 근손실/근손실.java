import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] training;// 운동별 중량 증가량
	static int N, K;// 운동키트 개수, 일당 중량 감소량
	static int[] tgt;// 0~N-1까지의 순열로 모든 운동 순서 체크
	static boolean[] select;// 순열 - 선택한 번호 체크
	static int chk;// 중량 500이상이 유지되는 운동 순서 체크
	static int weight;// 중량

	public static void main(String[] args) throws IOException {
		input();
		perm_chk(0);
		System.out.println(chk);

	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		training = new int[N];
		tgt = new int[N];
		select = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			training[i] = Integer.parseInt(st.nextToken());
		}
	}// 입력값 받고 training배열에 운동키트별 중량증가량 입력

	static void perm_chk(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			weight = 500;
			for (int t : tgt) {
				weight = weight + training[t] - K;
				if (weight < 500) {
					chk--;
					break;

				}

			}
			chk++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (select[i]) {
				continue;
			}
			tgt[tgtIdx] = i;
			select[i] = true;
			perm_chk(tgtIdx + 1);
			select[i] = false;

		}
	}//인덱스 번호를 순열로 뽑아서 뽑힌 조합대로 진행했을 때 weight가 500밑이 넘어가는 경우가 나오면 제외하고 체크
}
