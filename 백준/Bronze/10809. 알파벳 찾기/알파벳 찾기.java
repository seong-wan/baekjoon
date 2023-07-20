
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		int[] Arr = new int[26];
		for (int i = 0; i < 26; i++) {
			Arr[i] = S.indexOf('a' + (char) i);

		}
		for (int i : Arr) {
			System.out.print(i + " ");

		}

	}
}
