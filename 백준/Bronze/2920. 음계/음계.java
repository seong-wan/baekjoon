import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] Arr = new int[8];
		int cnt = 0;

		for (int i = 0; i < 8; i++) {
			Arr[i] = sc.nextInt();

		}
		cnt = Arr[0];
		if (cnt == 1) {
			for (int i = 0; i < 8; i++) {
				if (Arr[i] == cnt) {
					if (cnt == 8) {
						System.out.println("ascending");

					}
					cnt++;
				} else {
					System.out.println("mixed");
					break;
				}

			}
		} else if (cnt == 8) {
			for (int i = 0; i < 8; i++) {
				if (Arr[i] == cnt) {
					if (cnt == 1) {
						System.out.println("descending");

					}
					cnt--;

				} else {
					System.out.println("mixed");
					break;
				}
			}

		} else
			System.out.println("mixed");

	}
}
