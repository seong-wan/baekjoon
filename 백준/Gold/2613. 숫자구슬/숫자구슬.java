public class Main {
	static int N, M, left, right;
	static int[] beads;
	static int[] ansGroupCount;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		beads = new int[N];
		ansGroupCount = new int[M];

		for (int i = 0; i < N; i++) {
			beads[i] = in.nextInt();
			left = Math.max(beads[i], left);
		}

		right = 30000;

		while (left <= right) {
			int mid = (left + right) >> 1;
			int[] groupCount = new int[M];

			int before = 0;
			int idx = 0;
			int temp = mid;

			for (int i = 0; i < N; i++) {
				//남은 구슬의 개수와 그룹의 개수가 같은 경우
				if (M - idx - 1 == N - i) {
					groupCount[idx++] = i - before;
					for (int j = idx; j < M; j++) {
						groupCount[j] = 1;
						before = N - 1;
					}

					idx = M - 1;
					break;
				}

				if (temp < beads[i]) {
					groupCount[idx++] = i - before;
					before = i;

					//그룹이 원하는 개수를 넘어가는 경우
					if (idx + 1 > M)
						break;

					temp = mid - beads[i];
				} else
					temp -= beads[i];
			}

			//나와야 할 그룹 개수보다 많을 때
			if (idx + 1 > M)
				left = mid + 1;
			else {
				right = mid - 1;
				if (idx + 1 == M) {
					groupCount[idx] = N - before;
					copy(groupCount);
				}
			}
		}

		sb.append(left).append("\n");
		for (int i : ansGroupCount) {
			sb.append(i).append(" ");
		}

		System.out.println(sb);
	}

	static void copy(int[] origin) {
		for (int i = 0; i < M; i++) {
			ansGroupCount[i] = origin[i];
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