import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, x, y;
	static Deque<Integer> enco = new LinkedList<>();
	static Deque<Integer> temp = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		for (int t = 1; t < 11; t++) {
			enco.clear();
			start();
			encoding();
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(enco.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void start() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			enco.add(Integer.parseInt(st.nextToken()));
		}
		M = Integer.parseInt(br.readLine());

	}

	static void encoding() throws IOException {
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int j = 0; j < a; j++) {
				temp.add(enco.poll());

			}
			for (int j = 0; j < b; j++) {
				temp.add(Integer.parseInt(st.nextToken()));

			}
			temp.addAll(enco);
			enco.clear();
			enco.addAll(temp);
			temp.clear();

		}
	}
}
