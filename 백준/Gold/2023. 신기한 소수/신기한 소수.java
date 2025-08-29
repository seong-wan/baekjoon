import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		//소수부터 시작해서 1자리씪 붙여가며 소수 판정
		search(2, 1);
		search(3, 1);
		search(5, 1);
		search(7, 1);

		System.out.print(sb);
	}

	static void search(int num, int digits) {
		if (digits == N) {
			sb.append(num).append("\n");
			return;
		}

		for (int i = 0; i <= 9; i++) {
			int nextNum = num * 10 + i;

			if (primeCheck(nextNum))
				search(nextNum, digits + 1);
		}
	}

	static boolean primeCheck(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}
}