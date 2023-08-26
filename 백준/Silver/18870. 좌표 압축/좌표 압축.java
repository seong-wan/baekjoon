import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, x;
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set = new TreeSet<>();
	static Map<Integer, Integer> chk = new HashMap<>();// 계산했던 값 체크용
	static int[] input;
	static int cnt;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		for (int i = 0; i < N; i++) {
			x = Integer.parseInt(st.nextToken());
			input[i] = x;
			set.add(x);
		}
		cp();
		System.out.println(sb);
	}

	static void cp() {

		for (Integer num : set) {
			chk.put(num, cnt++);

		}
		for (int i = 0; i < N; i++) {
			sb.append(chk.get(input[i]) + " ");

		}
	}

}
