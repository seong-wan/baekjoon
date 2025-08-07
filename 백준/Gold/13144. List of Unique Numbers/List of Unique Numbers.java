import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] input;
	static long ans;
	static boolean[] chk = new boolean[100001];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		input = new int[N];
		ans = N;

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 1;
		chk[input[0]] = true;

		while (right < N) {
			//아직 같은 숫자가 없는 상황
			if (!chk[input[right]]) {
				chk[input[right]] = true;
				ans += right - left;
				right++;

				continue;
			}

			//이미 숫자가 있을 때 앞에서부터 하나씩 빼는 처리
			chk[input[left]] = false;
			left++;

			//앞에 있는 숫자가 다 사라졌다면 right 부터 다시 시작
			if (left == right) {
				chk[input[left]] = true;
				right++;
			}
		}

		System.out.print(ans);
	}
}