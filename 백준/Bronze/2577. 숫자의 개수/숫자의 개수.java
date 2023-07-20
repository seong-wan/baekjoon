
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int[] Arr = new int[10];
		String s = String.valueOf(A * B * C);
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 10; j++) {
				if (s.charAt(i) == (char) ('0' + j)) {
					Arr[j]++;
				}

			}

		}
		for (int i = 0; i < 10; i++) {

			System.out.println(Arr[i]);
		}

	}
}