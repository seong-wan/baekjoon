import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger bi = new BigInteger("0");
		BigInteger b2 = new BigInteger("0");
		for (int i = 0; i < 3; i++) {
			int N = sc.nextInt();
			bi = new BigInteger("0");
			for (int j = 0; j < N; j++) {
				bi = bi.add(sc.nextBigInteger());
			}

			switch (bi.compareTo(b2)) {
			case 1:
				System.out.println("+");
				break;
			case -1:
				System.out.println("-");
				break;
			case 0:
				System.out.println("0");
				break;
			}
		}
	}

}