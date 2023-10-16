import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Character> queue = new ArrayDeque<>();
	static int acount, acnt, ans;

	public static void main(String[] args) throws Exception {
		char[] ch = br.readLine().toCharArray();
		int size = ch.length;

		for (int i = 0; i < size; i++) {
			if (ch[i] == 'a')
				acount++;// 연속되어야 하는 a의 개수
		}
		if (acount == 0) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < acount; i++) {
			queue.add(ch[i]);
			if (ch[i] == 'a')
				acnt++;
		} // queue에 연속되어야 하는 a의 개수만큼 담기
		ans = acount - acnt;// queue에 담긴 b의 개수(교환되어야 하는 수)

		for (int i = acount; i < size; i++) {
			if (queue.poll() == 'a')
				acnt--;
			queue.add(ch[i]);
			if (ch[i] == 'a')
				acnt++;
			ans = Math.min(ans, acount - acnt);
		}

		for (int i = 0; i < acount - 1; i++) {
			if (queue.poll() == 'a')
				acnt--;
			queue.add(ch[i]);
			if (ch[i] == 'a')
				acnt++;
			ans = Math.min(ans, acount - acnt);
		}
		System.out.println(ans);
	}

}