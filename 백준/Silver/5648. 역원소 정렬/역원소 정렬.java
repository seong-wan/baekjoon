import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static List<Long> input = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			long temp = Long.parseLong(in.nextStringR());
			input.add(temp);
		}

		input.sort(Comparator.naturalOrder());
		for (Long i : input) {
			sb.append(i).append("\n");
		}

		System.out.println(sb);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		String nextStringR() throws Exception {
			StringBuilder sb = new StringBuilder();
			byte c;
			while ((c = read()) < 32) {
				if (size < 0)
					return "endLine";
			}
			do
				sb.appendCodePoint(c);
			while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
			return sb.reverse().toString();
		}

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