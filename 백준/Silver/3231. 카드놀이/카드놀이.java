import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N, ans;
	static Set<Integer> need = new HashSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		need.add(1);

		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());

			if (need.contains(temp)) {
				need.remove(temp);
				need.add(temp + 1);
			} else {
				ans++;
				need.add(temp + 1);
			}
		}

		System.out.print(ans);
	}
}