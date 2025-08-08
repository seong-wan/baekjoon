import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, maxLength;
	static String[] words;
	static int[] ans = new int[] {0, 1};

	static class Node {
		Node[] next = new Node[26];
		int firstIdx;
	}

	static Node root = new Node();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		words = new String[N];

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		for (int i = 0; i < N; i++) {
			insert(i);
		}

		System.out.println(words[ans[0]]);
		System.out.print(words[ans[1]]);

	}

	static void insert(int idx) {
		Node temp = root;
		int length = 0;

		for (int i = 0; i < words[idx].length(); i++) {
			char cur = words[idx].charAt(i);

			//해당 접두어를 가진 단어가 있는 경우
			if (temp.next[cur - 'a'] != null) {
				length++;
				temp = temp.next[cur - 'a'];

				if (length == maxLength && ans[0] > temp.firstIdx) {
					ans[0] = temp.firstIdx;
					ans[1] = idx;
				}

				if (length > maxLength) {
					maxLength = length;
					ans[0] = temp.firstIdx;
					ans[1] = idx;
				}
			}
			//접두어를 가진 단어가 없는 경우
			else {
				temp.next[cur - 'a'] = new Node();
				temp.next[cur - 'a'].firstIdx = idx;
				temp = temp.next[cur - 'a'];
			}
		}
	}
}