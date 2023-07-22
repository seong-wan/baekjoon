import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] Arr = new int[8];
		int chk = 0;

		for (int i = 0; i < 8; i++) {
			Arr[i] = sc.nextInt();

		}
		chk = Arr[0];
		if (chk == 1) {
			for (int i = 0; i < 8; i++) {
				if (Arr[i] == chk) {
					if (chk == 8) {
						System.out.println("ascending");

					}
					chk++;
				} else {
					System.out.println("mixed");
					break;
				}

			}
		} else if (chk == 8) {
			for (int i = 0; i < 8; i++) {
				if (Arr[i] == chk) {
					if (chk == 1) {
						System.out.println("descending");

					}
					chk--;

				} else {
					System.out.println("mixed");
					break;
				}
			}

		} else
			System.out.println("mixed");

	}
}
