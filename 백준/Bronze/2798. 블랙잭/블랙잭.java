import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = 0;
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			arr.add(sc.nextInt());

		}
		for (int i = 0; i < arr.size() ; i++) {
			for (int j = i + 1; j < arr.size() ; j++) {
				for (int j2 = j + 1; j2 < arr.size(); j2++) {
					if (arr.get(i) + arr.get(j) + arr.get(j2) <= M) {
						if (arr.get(i) + arr.get(j) + arr.get(j2) > max) {
							max = arr.get(i) + arr.get(j) + arr.get(j2);
						}
					}

				}

			}

		}
		System.out.println(max);
	}

}
