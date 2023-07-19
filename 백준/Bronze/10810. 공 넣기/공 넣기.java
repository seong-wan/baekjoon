import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] Arr = new int[N];    //N,M입력, 바구니 배열로 제작
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();    //i,j,k M만큼 입력받기
			for (int j = A - 1; j <= B - 1; j++) {
				Arr[j] = C;           //i에서j만큼 k공 바구니 배열에 입력

			}
		}
		for (int i = 0; i < N; i++) {
			System.out.print(Arr[i] + " "); //출력

		}
	}
}
