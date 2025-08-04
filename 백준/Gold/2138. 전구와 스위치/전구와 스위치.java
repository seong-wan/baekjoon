import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans = Integer.MAX_VALUE;
	static int[] origin;
	static int[] after;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		origin = new int[N];
		after = new int[N];

		String input = br.readLine();
		for (int i = 0; i < N; i++) {
			origin[i] = input.charAt(i) - '0';
		}

		input = br.readLine();
		for (int i = 0; i < N; i++) {
			after[i] = input.charAt(i) - '0';
		}

		int[] pushFirst = Arrays.copyOf(origin, N);
		pushFirst[0] = pushFirst[0] == 0 ? 1 : 0;
		pushFirst[1] = pushFirst[1] == 0 ? 1 : 0;

		int cnt = 0;

		//0번 스위치를 누르지 않은 경우
		for (int i = 0; i < N - 1; i++) {
			if (origin[i] != after[i]) {
				cnt++;
				origin[i + 1] = origin[i + 1] == 0 ? 1 : 0;

				if (i != N - 2)
					origin[i + 2] = origin[i + 2] == 0 ? 1 : 0;
			}
		}

		if (origin[N - 1] == after[N - 1])
			ans = Math.min(ans, cnt);

		cnt = 1;
		//0번 스위치를 누른 경우
		for (int i = 0; i < N - 1; i++) {
			if (pushFirst[i] != after[i]) {
				cnt++;
				pushFirst[i + 1] = pushFirst[i + 1] == 0 ? 1 : 0;

				if (i != N - 2)
					pushFirst[i + 2] = pushFirst[i + 2] == 0 ? 1 : 0;
			}
		}

		if (pushFirst[N - 1] == after[N - 1])
			ans = Math.min(ans, cnt);

		System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
	}
}