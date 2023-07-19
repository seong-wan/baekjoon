import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Arr = new int[N];
		int m;
		int M;
		for (int i = 0; i < N; i++) {
			Arr[i] = sc.nextInt();

		}
		m = Arr[0];
		M = Arr[0];

		for (int j = 0; j < N; j++) {

			if (Arr[j] < m) {
				m = Arr[j];
			} else if (Arr[j] > M) {
				M = Arr[j];
			}

		}
		System.out.printf("%d %d", m, M);

	}
}
