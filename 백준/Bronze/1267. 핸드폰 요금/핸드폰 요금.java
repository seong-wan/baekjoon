import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] fee;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		fee = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			fee[i] = Integer.parseInt(st.nextToken());

		}
		cal();
	}

	static void cal() {
		int young = 0;
		int min = 0;
		for (int i = 0; i < N; i++) {
			young += (fee[i] / 30 + 1) * 10;
			min += (fee[i] / 60 + 1) * 15;
		}
		if (young > min)
			System.out.println("M " + min);
		if (young < min)
			System.out.println("Y " + young);
		if (young == min)
			System.out.println("Y M " + min);

	}

}
