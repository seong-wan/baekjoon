import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		System.out.println(gcd(a, b));
		System.out.println(lcm(a, b));
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	static int lcm(int a, int b) {
		int i = 2, j = 2; // 곱해주는 수
		int Ma = a;
		int Mb = b;
		while (Ma != Mb) {
			if (Ma > Mb) {
				Mb = b * j;
				j++;
			} else {
				Ma = a * i;
				i++;
			}
		}
		return Ma;
	}
}
