import java.util.Arrays;

public class Main {
	static int N;
	static int[] A = new int[4000], B = new int[4000], C = new int[4000], D = new int[4000];
	static int[] sumAList, sumBList;
	static long cnt;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		sumAList = new int[N * N];
		sumBList = new int[N * N];

		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
			B[i] = in.nextInt();
			C[i] = in.nextInt();
			D[i] = in.nextInt();
		}

		int idx = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sumA = A[i] + B[j];
				int sumB = C[i] + D[j];

				sumAList[idx] = sumA;
				sumBList[idx++] = sumB;
			}
		}

		Arrays.sort(sumAList);
		Arrays.sort(sumBList);

		int left = 0;
		int right = N * N - 1;

		while (left <= N * N - 1 && right >= 0) {
			int temp = sumAList[left] + sumBList[right];

			if (temp < 0)
				left++;
			else if (temp > 0)
				right--;
			else {
				long cntA = 1;
				long cntB = 1;

				while (left < N * N - 1 && sumAList[left] == sumAList[left + 1]) {
					cntA++;
					left++;
				}

				while (right > 0 && sumBList[right] == sumBList[right - 1]) {
					cntB++;
					right--;
				}

				left++;
				right--;

				cnt += cntA * cntB;
			}
		}

		System.out.println(cnt);
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