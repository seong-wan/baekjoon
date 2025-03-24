public class Main {
	static class shark {
		//위치
		int r;
		int c;
		//속력
		int s;
		//이동 방향
		int d;
		//크기
		int z;

		public shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static int R, C, M, ans, fishManLoc;
	static shark[] sharks;

	static int[][] map;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		R = in.nextInt();
		C = in.nextInt();
		M = in.nextInt();

		sharks = new shark[M + 1];
		for (int i = 1; i <= M; i++) {
			sharks[i] = new shark(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		}

		simul();

		System.out.println(ans);
	}

	static void simul() {
		while (true) {
			//상어들의 현 위치를 맵에 표시
			marking();

			//낚시왕의 포획 시간
			fishing();

			//낚시 끝
			if (fishManLoc == C)
				return;

			//상어들의 이동 시간
			for (int i = 1; i <= M; i++) {
				//이미 사라져버린 불쌍한 상어들..
				if (sharks[i] == null)
					continue;

				int dir = sharks[i].d;

				//위로 향하는 상어의 경우 - r만 움직임
				if (dir == 1) {
					//가장 아래에서 부터의 거리
					int distance = R - sharks[i].r;
					int movePoint = distance + sharks[i].s;

					int reverseCount = movePoint / (R - 1);
					int afterDistance = movePoint % (R - 1);

					//방향이 안 바뀐 경우
					if (reverseCount % 2 == 0) {
						sharks[i].r = R - afterDistance;
					}
					//방향이 바뀐 경우
					else {
						sharks[i].d = 2;
						sharks[i].r = 1 + afterDistance;
					}
				}

				//아래로 향하는 상어의 경우 - r만 움직임
				else if (dir == 2) {
					//가장 아래에서 부터의 거리
					int distance = sharks[i].r - 1;
					int movePoint = distance + sharks[i].s;

					int reverseCount = movePoint / (R - 1);
					int afterDistance = movePoint % (R - 1);

					//방향이 안 바뀐 경우
					if (reverseCount % 2 == 0) {
						sharks[i].r = 1 + afterDistance;
					}
					//방향이 바뀐 경우
					else {
						sharks[i].d = 1;
						sharks[i].r = R - afterDistance;
					}
				}

				//오른쪽으로 향하는 상어의 경우 - c만 움직임
				else if (dir == 3) {
					//가장 아래에서 부터의 거리
					int distance = sharks[i].c - 1;
					int movePoint = distance + sharks[i].s;

					int reverseCount = movePoint / (C - 1);
					int afterDistance = movePoint % (C - 1);

					//방향이 안 바뀐 경우
					if (reverseCount % 2 == 0) {
						sharks[i].c = 1 + afterDistance;
					}
					//방향이 바뀐 경우
					else {
						sharks[i].d = 4;
						sharks[i].c = C - afterDistance;
					}
				}

				//왼쪽으로 향하는 상어의 경우 - c만 움직임
				else if (dir == 4) {
					//가장 아래에서 부터의 거리
					int distance = C - sharks[i].c;
					int movePoint = distance + sharks[i].s;

					int reverseCount = movePoint / (C - 1);
					int afterDistance = movePoint % (C - 1);

					//방향이 안 바뀐 경우
					if (reverseCount % 2 == 0) {
						sharks[i].c = C - afterDistance;
					}
					//방향이 바뀐 경우
					else {
						sharks[i].d = 3;
						sharks[i].c = 1 + afterDistance;
					}
				}
			}
		}
	}

	static void marking() {
		map = new int[R + 1][C + 1];

		for (int i = 1; i <= M; i++) {
			if (sharks[i] == null)
				continue;

			//이미 다른 상어가 있는 경우
			if (map[sharks[i].r][sharks[i].c] != 0) {
				int originSharkSize = sharks[map[sharks[i].r][sharks[i].c]].z;
				int newSharkSize = sharks[i].z;

				if (originSharkSize > newSharkSize) {
					sharks[i] = null;
					continue;
				} else {
					sharks[map[sharks[i].r][sharks[i].c]] = null;
				}
			}

			map[sharks[i].r][sharks[i].c] = i;
		}
	}

	static void fishing() {
		fishManLoc++;
		for (int i = 1; i <= R; i++) {
			//땅에 가장 가까운 위치에 있는 상어 포획
			if (map[i][fishManLoc] != 0) {
				int sharkNum = map[i][fishManLoc];
				ans += sharks[sharkNum].z;
				sharks[sharkNum] = null;
				break;
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