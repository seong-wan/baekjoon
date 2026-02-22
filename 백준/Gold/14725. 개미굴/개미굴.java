import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static TreeMap<String, TreeMap> root = new TreeMap<>();
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int K = Integer.parseInt(st.nextToken());
			TreeMap<String, TreeMap> temp = root;

			for (int j = 0; j < K; j++) {
				String s = st.nextToken();

				if (!temp.containsKey(s))
					temp.put(s, new TreeMap<>());

				temp = temp.get(s);
			}
		}

		search(root, 0);

		System.out.print(sb);
	}

	static void search(TreeMap<String, TreeMap> map, int depth) {
		for (String key : map.keySet()) {
			for (int i = 0; i < depth; i++) {
				sb.append("--");
			}

			sb.append(key).append("\n");
			search(map.get(key), depth + 1);
		}
	}
}