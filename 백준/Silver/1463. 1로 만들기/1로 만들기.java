import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int[] memoi;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		memoi = new int[n+1];
		
		if(n == 1) System.out.println(0);
		else if(n == 2) System.out.println(1);
		else if(n == 3) System.out.println(1);
		else {
			Arrays.fill(memoi, Integer.MAX_VALUE);
			memoi[1] = 0;
			memoi[2] = 1;
			memoi[3] = 1;
			
			for(int i=4; i<=n; i++) {
				if(i%3 == 0) memoi[i] = Math.min(memoi[i/3] +1,memoi[i]);
				if(i%2 == 0) memoi[i] = Math.min(memoi[i/2] +1,memoi[i]);
				memoi[i] = Math.min(memoi[i], memoi[i-1]+1);
			}
		
			System.out.println(memoi[n]);
		}

	}

}