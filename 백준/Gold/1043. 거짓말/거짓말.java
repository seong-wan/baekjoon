import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N, M, ans;
	static Deque<Integer> queue = new ArrayDeque<>();
	static Set<Integer>[] parties;
	static boolean[] isTruth;
	static boolean[] checkedPerson;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		ans = M;

		parties = new Set[M];
		isTruth = new boolean[M];
		checkedPerson = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			parties[i] = new HashSet<>();
		}

		int truethCnt = in.nextInt();
		for (int i = 0; i < truethCnt; i++) {
			int num = in.nextInt();
			checkedPerson[num] = true;
			queue.add(num);
		}

		for (int i = 0; i < M; i++) {
			int partySize = in.nextInt();

			for (int j = 0; j < partySize; j++) {
				parties[i].add(in.nextInt());
			}
		}

		while (!queue.isEmpty()) {
			int temp = queue.poll();

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					//이미 체크된 경우
					if (isTruth[i])
						continue;

					if (parties[i].contains(temp)) {
						isTruth[i] = true;
						ans--;

						for (Integer num : parties[i]) {
							if (checkedPerson[num])
								continue;

							checkedPerson[num] = true;
							queue.add(num);
						}
					}
				}
			}
		}

		System.out.print(ans);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}