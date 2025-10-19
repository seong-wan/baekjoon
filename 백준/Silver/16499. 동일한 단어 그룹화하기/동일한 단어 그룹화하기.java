import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Set<String> set = new HashSet<>();
	static int N;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			stringToKey(s);
		}

		System.out.print(set.size());
	}

	static void stringToKey(String s) {
		int[] nums = new int[26];
		String key = "";

		for (int i = 0; i < s.length(); i++) {
			int temp = s.charAt(i) - 'a';

			nums[temp]++;
		}

		for (int i = 0; i < 26; i++) {
			key += Integer.toString(nums[i]);
		}

		set.add(key);
	}
}