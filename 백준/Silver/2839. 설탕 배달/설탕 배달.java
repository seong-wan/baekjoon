import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		
		while(true) {
			
			if(N%5 == 0) {
				cnt += N/5;
				break;
			}
			
			N -= 3;
			cnt++;
			if(N == 0) break;
			
			if(N<0) {
				cnt = -1;
				break;
			}
		}
		
		System.out.println(cnt);

	}

}