import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int B, ans;
	static char[] nums;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = st.nextToken().toCharArray();
		B = Integer.parseInt(st.nextToken());
		visit = new boolean[nums.length];

		for (int i = 0; i < nums.length; i++) {
			int temp = nums[i] - '0';
			int digit = (int)Math.pow(10, nums.length - 1);

			if (temp == 0 || (temp != 1 && digit == 1_000_000_000))
				continue;

			visit[i] = true;
			dfs(temp * digit, nums.length - 1);
			visit[i] = false;
		}

		System.out.println(ans == 0 ? -1 : ans);
	}

	static void dfs(int num, int digit) {
		if (num > B)
			return;

		if (digit == 0) {
			ans = Math.max(ans, num);
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visit[i])
				continue;

			int temp = nums[i] - '0';

			visit[i] = true;
			dfs((int)(num + temp * Math.pow(10, digit - 1)), digit - 1);
			visit[i] = false;
		}
	}
}