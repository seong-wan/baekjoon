import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] Arr = new int[N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Arr[i] = i + 1;

		}

		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			cnt = Arr[A - 1];
			Arr[A - 1] = Arr[B - 1];
			Arr[B - 1] = cnt;
		}

		for (int i = 0; i < N; i++) {
			System.out.print(Arr[i] + " ");

		}
	}
}
