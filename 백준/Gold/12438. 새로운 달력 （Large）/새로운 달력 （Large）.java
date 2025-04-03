import java.util.ArrayList;
import java.util.List;

public class Main {
	static int T, dayPerMonth, dayPerWeek;
	static long totalMonth;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();

		for (int t = 1; t <= T; t++) {
			long ans = 0;
			long remainDay = 0;
			long term = 1;

			List<Long> weekCount = new ArrayList<>();
			weekCount.add(0L);

			totalMonth = in.nextLong();
			dayPerMonth = in.nextInt();
			dayPerWeek = in.nextInt();

			remainDay = dayPerMonth % dayPerWeek;
			long weekPerMonth = dayPerMonth / dayPerWeek + (remainDay > 0 ? 1 : 0);

			weekCount.add(weekPerMonth);

			while (remainDay > 0) {
				long temp = dayPerMonth + remainDay;
				term += 1;
				remainDay = temp % dayPerWeek;
				long newWeekPerMonth = temp / dayPerWeek + (remainDay > 0 ? 1 : 0);
				weekPerMonth += newWeekPerMonth;
				weekCount.add(weekPerMonth);
			}

			ans = (totalMonth / term) * weekPerMonth + weekCount.get((int)(totalMonth % term));

			sb.append("Case #").append(t).append(": ").append(ans).append("\n");
		}
		System.out.println(sb);
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

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32)
				;
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