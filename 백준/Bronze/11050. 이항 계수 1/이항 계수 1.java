import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int up = 1;
		int down = 1;
		for (int i = 0; i < K; i++) {
			down *= K - i;

		}
		for (int i = 0; i < K; i++) {
			up *= N - i;

		}
		System.out.println(up / down);
	}

}
