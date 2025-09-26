import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] num;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		num = new int[N];

		for (int i = 1; i <= 3; i++) {
			if (search(0, i))
				break;
		}

		for (int i : num) {
			System.out.print(i);
		}
	}

	static boolean search(int idx, int n) {
		if (idx == N)
			return true;

		int maxTerm = (idx + 1) / 2;

		num[idx] = n;

		for (int term = 1; term <= maxTerm; term++) {
			boolean isSame = true;
			for (int j = idx; j > idx - term; j--) {
				if (num[j - term] != num[j]) {
					isSame = false;
					break;
				}
			}

			if (isSame) {
				return false;
			}
		}

		for (int i = 1; i <= 3; i++) {
			if (search(idx + 1, i)) {
				return true;
			}
		}

		return false;
	}
}