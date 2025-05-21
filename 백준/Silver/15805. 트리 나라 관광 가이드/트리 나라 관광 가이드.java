import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, count = 1, cur, root;
	static int[] parent = new int[200001];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Arrays.fill(parent, -1);

		root = Integer.parseInt(st.nextToken());
		cur = root;
		for (int i = 0; i < N - 1; i++) {
			int temp = Integer.parseInt(st.nextToken());

			if (temp != root && parent[temp] == -1) {
				count++;
				parent[temp] = cur;
			}

			cur = temp;
		}

		sb.append(count).append("\n");
		for (int i = 0; i < count; i++) {
			sb.append(parent[i]).append(" ");
		}

		System.out.println(sb);
	}
}