import java.util.ArrayList;
import java.util.List;

//골드바흐의 추측
//2를 제외한 짝수는 무조건 소수 둘로 나누어 질 수 있음
//홀수는 짝수+홀수로 나누어짐
//짝수 소수는 2만 존재하므로 2,(S-2)로 나누고 (S-2)가 소수인지 아닌지를 판명하면 됨
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int T;
	static boolean[] isNotPrime = new boolean[2000001];
	static List<Integer> prime = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		init();

		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			long a = in.nextLong();
			long b = in.nextLong();
			long S = a + b;

			if (S <= 3) {
				sb.append("NO").append("\n");
				continue;
			}

			if (S % 2 == 0) {
				sb.append("YES").append("\n");
				continue;
			}

			S -= 2;

			if (check(S))
				sb.append("YES").append("\n");
			else
				sb.append("NO").append("\n");
		}
		System.out.print(sb);
	}

	static boolean check(long num) {
		for (Integer i : prime) {
			if ((long)i * i > num)
				break;

			if (num % i == 0)
				return false;
		}

		return true;
	}

	static void init() {
		for (int i = 2; i * i <= 2000000; i++) {
			if (!isNotPrime[i]) {
				for (int j = i * i; j <= 2000000; j += i) {
					isNotPrime[j] = true;
				}
			}
		}

		for (int i = 2; i <= 2000000; i++) {
			if (!isNotPrime[i])
				prime.add(i);
		}
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