import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	static Deque<Character> stack = new ArrayDeque<>();
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;

	public static void main(String[] args) throws Exception, IOException {
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			System.out.print("#" + t + " " + valid_check() + "\n");
		}
	}

	static int valid_check() {
		char c;
		stack.clear();
		if (N % 2 == 1)// N이 홀수로 짝이 맞을 수가 없음
			return 0;
		else if (N % 2 == 0) {
			for (int i = 0; i < N; i++) {
				if (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}' || str.charAt(i) == '>') {
					// 닫는 괄호가 나올 때 스택 상황 확인
					if (stack.isEmpty()) {// 열린 괄호가 나오지 않은 상태로 닫는 괄호가 나오면 유효 X
						return 0;
					} else {// 열린 괄호는 나와있는 상황
						c = stack.pop();
						switch (str.charAt(i)) {
						case ')':
							if (c != '(')
								return 0;
							break;
						case ']':
							if (c != '[')
								return 0;
							break;
						case '}':
							if (c != '{')
								return 0;
							break;
						case '>':
							if (c != '<')
								return 0;
							break;
						}
					}
				} else
					stack.push(str.charAt(i));

			}

		}
		return 1;
	}
}
