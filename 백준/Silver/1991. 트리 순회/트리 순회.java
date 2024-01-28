import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//A를 루트로 A-0~Z-25 로 인덱싱을 한 후
//전위는 기준-왼-오, 중위는 왼-기준-오, 후위는 왼-오-기준 순으로 dfs를 돌면 됨
public class Main {
	static class Node {
		char left;
		char mid;
		char right;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static Node[] tree = new Node[26];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < 26; i++) {
			tree[i] = new Node();
		}

		// 트리 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char mid = st.nextToken().charAt(0);
			int idx = mid - 'A';
			tree[idx].mid = mid;
			tree[idx].left = st.nextToken().charAt(0);
			tree[idx].right = st.nextToken().charAt(0);
		}

		pre(0);
		sb.append("\n");
		in(0);
		sb.append("\n");
		post(0);
		System.out.println(sb);
	}

	private static void pre(int i) {
		if (i == '.' - 'A')
			return;
		sb.append(tree[i].mid);
		pre(tree[i].left - 'A');
		pre(tree[i].right - 'A');

	}

	private static void in(int i) {
		if (i == '.' - 'A')
			return;
		in(tree[i].left - 'A');
		sb.append(tree[i].mid);
		in(tree[i].right - 'A');
	}

	private static void post(int i) {
		if (i == '.' - 'A')
			return;
		post(tree[i].left - 'A');
		post(tree[i].right - 'A');
		sb.append(tree[i].mid);
	}
}