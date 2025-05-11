import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] comb = {1, 18, 0, 0, 3060, 0, 18564, 0, 43758, 48620, 43758, 0, 18564, 0, 3060, 816, 153, 0, 1};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static double A, B, reverseA, reverseB, sum;

	public static void main(String[] args) throws Exception {
		A = Double.parseDouble(br.readLine()) / 100;
		reverseA = 1 - A;
		B = Double.parseDouble(br.readLine()) / 100;
		reverseB = 1 - B;

		for (int i = 0; i <= 18; i++) {
			if (comb[i] == 0)
				continue;

			for (int j = 0; j <= 18; j++) {
				if (comb[j] == 0)
					continue;

				double perA = comb[i] * Math.pow(A, i) * Math.pow(reverseA, 18 - i);
				double perB = comb[j] * Math.pow(B, j) * Math.pow(reverseB, 18 - j);

				sum += perA * perB;
			}
		}
		System.out.println(1 - sum);
	}
}