import java.util.Arrays;

public class Main {
	static int N;
	static int[] input = new int[100000];
	static int[] temp;
	static int carryCnt, avg;
	static long ans;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
			avg += input[i];
		}

		avg /= N;

		for (int i = 0; i < N; i++) {
			input[i] -= avg;
		}

		temp = Arrays.copyOf(input, N);

		long carrySum = 0;

		//시계 방향
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				temp[j] += carryCnt;
				carryCnt = 0;

				if (temp[j] > 0) {
					carryCnt = temp[j];
					temp[j] = 0;
				}

				carrySum += carryCnt;
			}
		}

		ans = carrySum;

		temp = Arrays.copyOf(input, N);
		carrySum = 0;
		//반시계방향
		for (int i = 0; i < 2; i++) {
			for (int j = N - 1; j >= 0; j--) {
				temp[j] += carryCnt;
				carryCnt = 0;

				if (temp[j] > 0) {
					carryCnt = temp[j];
					temp[j] = 0;
				}

				carrySum += carryCnt;
			}
		}

		ans = Math.min(ans, carrySum);

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
