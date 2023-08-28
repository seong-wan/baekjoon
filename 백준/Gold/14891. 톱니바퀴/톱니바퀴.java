import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class magnet1 {
	Deque<Integer> mag = new ArrayDeque<>();

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, K;
	static magnet1[] mags = new magnet1[4];// 4개의 자석

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 4; i++) {
			mags[i] = new magnet1();
		}

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				mags[i].mag.add(s.charAt(j) - '0');
			}

		} // 자석에 자성 정보 담기

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			// 인덱스를 0부터 시작하게 했으므로 맞춰주기 위해-1
			int dir = Integer.parseInt(st.nextToken());
			simul(num, dir);
		} // 명령받고 시뮬레이션

		chk_score();
	}

	static void rotate(int mag_num, int dir) {
		if (dir == 1)
			mags[mag_num].mag.addFirst(mags[mag_num].mag.pollLast());// 시계 방향 회전
		if (dir == -1)
			mags[mag_num].mag.add(mags[mag_num].mag.poll());// 반시계 방향 회전

	}

	static int check_r(int num) {
		int cnt = 0;
		for (Integer i : mags[num].mag) {
			if (cnt == 2) {
				return i;
			}
			cnt++;

		}
		return -1;
	}

	static int check_l(int num) {
		int cnt = 0;
		for (Integer i : mags[num].mag) {
			if (cnt == 6) {
				return i;
			}
			cnt++;

		}
		return -1;
	}

	static void chk_score() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			score += mags[i].mag.poll() * Math.pow(2, i);

		}
		System.out.println(score);
	}

	static void simul(int mag_num, int dir) {
		boolean[] chk = new boolean[3];// (1==2,2==3,3==4)
		if (check_r(0) != check_l(1))
			chk[0] = true;
		if (check_r(1) != check_l(2))
			chk[1] = true;
		if (check_r(2) != check_l(3))
			chk[2] = true;// 자석을 돌릴 시점에 양 쪽에 자성이 같은 지 확인
		if (mag_num == 0) {
			rotate(0, dir);
			if (chk[0]) {
				rotate(1, r_dir(dir));
				if (chk[1]) {
					rotate(2, dir);
					if (chk[2])
						rotate(3, r_dir(dir));
				}

			}
		} // 1번 자석일 경우
		else if (mag_num == 1) {
			rotate(1, dir);
			if (chk[0])
				rotate(0, r_dir(dir));
			if (chk[1]) {
				rotate(2, r_dir(dir));
				if (chk[2])
					rotate(3, dir);
			}
		} // 2번 자석일 경우
		else if (mag_num == 2) {
			rotate(2, dir);
			if (chk[2])
				rotate(3, r_dir(dir));
			if (chk[1]) {
				rotate(1, r_dir(dir));
				if (chk[0])
					rotate(0, dir);
			}
		} // 3번 자석일 경우
		else if (mag_num == 3) {
			rotate(3, dir);
			if (chk[2]) {
				rotate(2, r_dir(dir));
				if (chk[1]) {
					rotate(1, dir);
					if (chk[0])
						rotate(0, r_dir(dir));
				}

			}
		} // 4번 자석일 경우

	}

	static int r_dir(int dir) {
		if (dir == 1)
			return -1;
		else if (dir == -1)
			return 1;
		return 0;

	}
}
