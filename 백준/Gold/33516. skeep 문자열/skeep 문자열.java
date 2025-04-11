import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans, findIdx;
	static String s;
	static Deque<Character> stack = new ArrayDeque<>();
	static char[] findChar = {'s', 'k', 'e', 'e', 'p'};

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		s = br.readLine();

		for (int i = 0; i < N; i++) {
			if (s.charAt(i) == findChar[findIdx]) {
				//skeep 완성
				if (findIdx == 4) {
					alter();
					continue;
				}

				stack.push(s.charAt(i));
				findIdx++;
			} else if (s.charAt(i) == 's') {
				stack.push(s.charAt(i));
				findIdx = 1;
			} else {
				stack.clear();
				findIdx = 0;
			}
		}

		System.out.println(ans);
	}

	static void alter() {
		ans++;
		for (int i = 0; i < 4; i++) {
			stack.pop();
		}

		if (stack.isEmpty()) {
			findIdx = 0;
			return;
		}

		char last = stack.pop();

		if (last == 's')
			findIdx = 2;
		else if (last == 'k')
			findIdx = 3;
		else if (last == 'e') {
			char beforeLast = stack.pop();
			stack.push(beforeLast);

			//e가 2개인 경우, 바로 다시 skeep이 만들어지는 경우
			if (beforeLast == 'e') {
				stack.push(last);
				alter();
				return;
			} else
				findIdx = 4;
		}

		stack.push(last);
		stack.push(findChar[findIdx - 1]);
	}
}