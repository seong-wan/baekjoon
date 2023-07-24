import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		int sum = 1;
		int r = 1;
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			if (i == sum && i != N) {
				cnt += 6;
				sum += cnt;
				r++;
			}

		}
		System.out.println(r);

	}
}