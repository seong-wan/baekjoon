import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0;
		int max = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (max < arr[i]) {
				cnt++;
				max = arr[i];
			}
		}
		System.out.println(cnt);
	}
}