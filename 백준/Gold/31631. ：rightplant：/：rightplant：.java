import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		int right = N - 1;
		int left = 0;

		while (N > 0) {
			nums[right--] = N--;
			if (N == 0)
				break;

			nums[left++] = N--;
			if (N == 0)
				break;

			nums[left++] = N--;
			if (N == 0)
				break;

			nums[right--] = N--;
		}

		for (int num : nums) {
			sb.append(num).append(" ");
		}

		System.out.print(sb);
	}
}