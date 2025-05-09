import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static class Node {
		Node[] next = new Node[26];
		boolean end;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Set<String> set = new TreeSet<>();
	static Set<String> ans = new TreeSet<>();
	static StringBuilder sb = new StringBuilder();
	static Node root = new Node();

	public static void main(String[] args) throws Exception {
		String str = "";

		while ((str = br.readLine()) != null && !(str.isEmpty())) {
			set.add(str);
		}

		for (String s : set) {
			Node cur = root;

			for (int i = 0; i < s.length(); i++) {
				if (cur.next[s.charAt(i) - 'a'] == null) {
					cur.next[s.charAt(i) - 'a'] = new Node();
				}

				cur = cur.next[s.charAt(i) - 'a'];

				//한 단어의 끝인 경우
				if (cur.end) {
					String before = s.substring(0, i + 1);
					String after = s.substring(i + 1);

					if (set.contains(after))
						ans.add(s);
				}
			}

			cur.end = true;
		}

		for (String s : ans) {
			sb.append(s).append("\n");
		}

		System.out.println(sb);
	}
}