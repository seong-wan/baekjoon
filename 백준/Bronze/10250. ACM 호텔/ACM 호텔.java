import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int H = sc.nextInt();
			int W = sc.nextInt();
			int N = sc.nextInt();

			if (N % H == 0) {

				System.out.println(H * 100 + N / H);
			} else
				System.out.println(N % H * 100 + N / H + 1);
		} // N%H = 층 수 N/H+1 = 각 층의 호수
	}

}
