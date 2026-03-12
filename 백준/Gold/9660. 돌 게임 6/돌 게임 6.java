import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static long N;
	static boolean[] pattern = {true, false, true, true, true, true, false};
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		N = Long.parseLong(br.readLine());
		int idx = (int)((N - 1) % 7);

		System.out.print(pattern[idx] ? "SK" : "CY");
	}
}