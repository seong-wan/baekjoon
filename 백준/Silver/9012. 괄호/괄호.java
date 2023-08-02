
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String st;
	static boolean chk;

	public static void main(String[] args) throws Exception, IOException {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			Deque<Integer> stack = new ArrayDeque<>();
			chk = false;
			st = br.readLine();
			char[] ct = st.toCharArray();
			int count = ct.length;
			for (int j = 0; j < count; j++) {
				if (ct[j] == '(') {
					stack.push(1);

				} else {
					if (stack.isEmpty()) {
						System.out.println("NO");
						chk = true;
						break;
					}

					else
						stack.pop();
				}

			}
			if (!chk) {
				if (stack.isEmpty())
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}

	}

}
