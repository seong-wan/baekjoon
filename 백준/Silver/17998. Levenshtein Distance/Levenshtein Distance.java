import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String s, query;
	static Set<String> treeSet = new TreeSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		s = br.readLine();
		query = br.readLine();

		for (int i = 0; i < s.length(); i++) {
			char temp = s.charAt(i);

			//추가
			treeSet.add(temp + query);

			for (int j = 1; j < query.length(); j++) {
				treeSet.add(query.substring(0, j) + temp + query.substring(j));
			}

			treeSet.add(query + temp);

			//제거
			for (int j = 0; j < query.length(); j++) {
				if (query.charAt(j) == temp) {
					treeSet.add(query.substring(0, j) + query.substring(j + 1));
				}
			}

			//변환
			for (int j = 0; j < query.length(); j++) {
				treeSet.add(query.substring(0, j) + temp + query.substring(j + 1));
			}
		}

		for (String string : treeSet) {
			if (string.equals(query))
				continue;

			sb.append(string).append("\n");
		}

		System.out.println(sb);
	}
}