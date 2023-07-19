import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 0;
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			int N = sc.nextInt();
			if (N > max) {
				max = N;
				cnt = i;
			}
		}

		System.out.printf("%d\n%d", max, cnt + 1);
	}
}
