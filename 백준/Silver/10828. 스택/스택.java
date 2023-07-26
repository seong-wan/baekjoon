import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws NoSuchElementException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> stack = new ArrayDeque<Integer>(); // stack ArrayDeque로 정의
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			String[] str = s.split(" ");
			if (str[0].equals("push")) {

				stack.push(Integer.parseInt(str[1]));// push가 문자열에 포함 시 다음에 나올 정수 push

			} else if (s.equals("top")) {
				System.out.println(stack.size() == 0 ? -1 : stack.getFirst());
			} else if (s.equals("size")) {
				System.out.println(stack.size());
			} else if (s.equals("empty")) {
				System.out.println(stack.isEmpty() ? 1 : 0);
			} else if (s.equals("pop")) {
				System.out.println(stack.size() == 0 ? -1 : stack.pop());
			}

		}

	}

}