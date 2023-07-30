import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int j = N; j > 0; j--) {

			for (int i = 0; i < j; i++) {
				System.out.print("*");

			}
			System.out.println();
		}
	}

}
