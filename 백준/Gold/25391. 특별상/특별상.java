import java.util.Arrays;

public class Main {
	static long sum;
	static int N, M, K;
	static int[][] students;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();

		students = new int[N][2];
		for (int i = 0; i < N; i++) {
			students[i][0] = in.nextInt();
			students[i][1] = in.nextInt();
		}

		Arrays.sort(students, (e1, e2) -> e2[1] - e1[1]);
		for (int i = 0; i < N; i++) {
			students[i][1] = i;

			//일반상을 타는 학생
			if (i < K)
				sum += students[i][0];
		}

		int specialPrizeCnt = 0;
		int idx = 0;

		Arrays.sort(students, (e1, e2) -> e2[0] - e1[0]);
		while (specialPrizeCnt != M) {
			//이미 일반상을 탈 수 있는 학생
			if (students[idx][1] < K) {
				idx++;
				continue;
			}

			specialPrizeCnt++;
			sum += students[idx++][0];
		}

		System.out.print(sum);
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