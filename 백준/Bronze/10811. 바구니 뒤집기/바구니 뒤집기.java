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

		}                     //생성된 배열에 1~N까지 넣기
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();

			while (B > A) {
				cnt = Arr[A - 1];
				Arr[A - 1] = Arr[B - 1];
				Arr[B - 1] = cnt;
				A++;
				B--;

			}               //A,B입력 받고 A가 B보다 커질 때까지 계속해서 배열 순서 바꿈(역순)

		}
		for (int i : Arr) {
			System.out.printf("%d ", i); //출력

		}
	}
}
