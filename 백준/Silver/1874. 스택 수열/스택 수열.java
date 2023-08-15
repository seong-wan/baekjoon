import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Deque<Integer> stack = new ArrayDeque<>();// 실제 스택 출력값과 비교하기 위해
	static int n, cnt, p_num;// 숫자의 수와 1개의 push연산마다 올라가는 변수,현재 입력값 전의 입력값

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num > p_num) {
				chk_p(num);

			} else {
				if (p_num - num == 1) {
					stack.pop();
					sb.append("-\n");
				} else {
					if (num == stack.pop())
						sb.append("-\n");
					else {
						sb.setLength(0);
						sb.append("NO");
						break;
					}

				}

			}
			p_num = num;
		}
		System.out.println(sb);
	}

	static void chk_p(int n) {
		while (cnt != n) {
			cnt++;
			sb.append("+\n");
			stack.push(cnt);
		}
		stack.pop();
		sb.append("-\n");
	}

}
