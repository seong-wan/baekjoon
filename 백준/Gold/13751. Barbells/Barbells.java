import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int b, p;
	static int[] barbells, plates;
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set = new HashSet<>();
	static Set<Integer> result = new TreeSet<>((e1, e2) -> e1 - e2);

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		barbells = new int[b];
		plates = new int[p];

		for (int i = 0; i < b; i++) {
			barbells[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < p; i++) {
			plates[i] = Integer.parseInt(br.readLine());
		}

		//왼쪽,오른쪽,사용하지 않음의 3가지 경우로 dfs
		dfs(0, 0, 0);

		for (int i = 0; i < b; i++) {
			for (Integer weight : set) {
				result.add(weight + barbells[i]);
			}
		}

		for (Integer i : result) {
			sb.append(i).append("\n");
		}

		System.out.print(sb);
	}

	static void dfs(int idx, int left, int right) {
		if (idx == p) {
			if (left == right) {
				set.add(2 * left);
			}

			return;
		}

		dfs(idx + 1, left + plates[idx], right);
		dfs(idx + 1, left, right + plates[idx]);
		dfs(idx + 1, left, right);
	}
}