import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] list = input.split(" ");
		
		int res = 0;
		for (int i = 0; i < 2; i++) {
			res += Integer.valueOf(list[i]); 
		}
		
		System.out.println(res);
		
		
	}

}