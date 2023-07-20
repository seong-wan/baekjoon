
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sum = 0;
		String A = sc.next();
		for (int i = 0; i < N; i++) {
			sum += (A.charAt(i) - '0');

		}
		System.out.println(sum);
	}
}
