import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] nums = new int[10];

	public static void main(String[] args) throws Exception {
		String s = br.readLine();
		N = Integer.parseInt(s);

		int temp = N;
		for (int i = s.length() - 1; i >= 0; i--) {
			int digit = pow(i);
			int mid = temp / digit;
			int left = N / (digit * 10);
			int right = N % digit;
			temp = right;

			for (int j = 0; j <= 9; j++) {
				if (j < mid)
					nums[j] += (left + 1) * digit;
				else
					nums[j] += left * digit;

				if (j == 0)
					nums[j] -= digit;

				if (j == mid)
					nums[j] += right + 1;
			}
		}

		for (int num : nums) {
			System.out.print(num + " ");
		}
	}

	static int pow(int num) {
		int result = 1;

		for (int i = 0; i < num; i++) {
			result *= 10;
		}

		return result;
	}
}