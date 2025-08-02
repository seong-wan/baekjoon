public class Main {
	static int N, K, stage, breakCount;
	static Belt[] conveyor;

	static class Belt {
		int durability;
		boolean robot;

	}

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		K = in.nextInt();

		conveyor = new Belt[N * 2];
		for (int i = 0; i < N * 2; i++) {
			conveyor[i] = new Belt();
			conveyor[i].durability = in.nextInt();
		}

		while (breakCount < K) {
			stage++;
			rotate();
			moveRobots();
			upRobot();
		}

		System.out.print(stage);
	}

	static void rotate() {
		Belt temp = conveyor[2 * N - 1];

		for (int i = 2 * N - 2; i >= 0; i--) {
			conveyor[i + 1] = conveyor[i];
		}

		conveyor[0] = temp;
		conveyor[N - 1].robot = false;
	}

	static void moveRobots() {
		for (int i = N - 2; i >= 0; i--) {
			if (conveyor[i].robot && conveyor[i + 1].durability > 0 && !conveyor[i + 1].robot) {
				conveyor[i].robot = false;
				conveyor[i + 1].robot = true;
				conveyor[i + 1].durability--;

				if (conveyor[i + 1].durability == 0) {
					breakCount++;
				}
			}
		}

		conveyor[N - 1].robot = false;
	}

	static void upRobot() {
		if (conveyor[0].durability > 0) {
			conveyor[0].robot = true;
			conveyor[0].durability--;

			if (conveyor[0].durability == 0) {
				breakCount++;
			}
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