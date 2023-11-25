import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class score {
		String name;
		int Korean;
		int english;
		int math;

		public score(String name, int korean, int english, int math) {
			super();
			this.name = name;
			Korean = korean;
			this.english = english;
			this.math = math;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<score> list = new ArrayList<>();
	static int N;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int Korean = Integer.parseInt(st.nextToken());
			int english = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			list.add(new score(name, Korean, english, math));
		}
		Collections.sort(list,
				(e1, e2) -> e2.Korean == e1.Korean
						? (e1.english == e2.english
								? (e1.math == e2.math ? (e1.name.compareTo(e2.name)) : e2.math - e1.math)
								: e1.english - e2.english)
						: e2.Korean - e1.Korean);

		for (score score : list) {
			sb.append(score.name + "\n");
		}
		System.out.println(sb);
	}

}