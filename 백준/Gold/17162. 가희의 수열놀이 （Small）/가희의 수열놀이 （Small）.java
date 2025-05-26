import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static int Q, mod;
	static Deque<Integer>[] modLocation;
	static List<Integer> arr = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		Q = in.nextInt();
		mod = in.nextInt();

		modLocation = new Deque[mod];
		for (int i = 0; i < mod; i++) {
			modLocation[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < Q; i++) {
			int cmd = in.nextInt();

			if (cmd == 1)
				add(in.nextInt());
			else if (cmd == 2)
				remove();
			else
				query();
		}

		System.out.println(sb);
	}

	static void add(int num) {
		int location = arr.size();
		int modValue = num % mod;

		arr.add(num);
		modLocation[modValue].push(location);
	}

	static void remove() {
		if (arr.isEmpty())
			return;

		int num = arr.get(arr.size() - 1);
		int modValue = num % mod;

		arr.remove(arr.size() - 1);
		modLocation[modValue].pop();
	}

	static void query() {
		int first = Integer.MAX_VALUE;

		for (int i = 0; i < mod; i++) {
			if (modLocation[i].isEmpty()) {
				sb.append(-1).append("\n");
				return;
			}

			first = Math.min(first, modLocation[i].peekFirst());
		}

		sb.append(arr.size() - first).append("\n");
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