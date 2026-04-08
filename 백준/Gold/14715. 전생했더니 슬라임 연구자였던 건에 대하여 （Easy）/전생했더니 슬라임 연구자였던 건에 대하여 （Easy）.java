import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, cnt;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		calc();

		System.out.print((int)Math.ceil(Math.log(cnt) / Math.log(2)));
	}

	static void calc() {
		for (int i = 2; i <= Math.sqrt(1000000); i++) {
			while (N % i == 0) {
				cnt++;
				N /= i;
			}

			if (N == 1)
				break;
		}

		if (N != 1)
			cnt++;
	}
}
