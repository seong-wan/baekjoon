import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double A = sc.nextInt();
		int B = sc.nextInt();
		int V = sc.nextInt();

		System.out.println((int) Math.ceil((V - A) / (A - B)) + 1);

	}

}
