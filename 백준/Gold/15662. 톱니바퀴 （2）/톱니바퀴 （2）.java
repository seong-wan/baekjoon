import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//톱니바퀴 - 8의 크기를 가진 배열 (위,오위,오,오아래,아래,왼아래,왼,왼위)
//시계방향 회전 - 왼위(7)을 저장 후 한 칸씩 뒤로 미루고 위에 왼위를 저장
//반시계방향 회전 - 위를 저장 후 한 칸씩 앞으로 옮기고 왼위에 위를 저장
//12방향인 S극의 개수 - [0]에 1이 저장된 톱니의 개수
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, K;
	static int[][] cogwheels;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		cogwheels = new int[T + 1][8];
		for (int i = 1; i <= T; i++) {
			char[] poles = br.readLine().toCharArray();

			for (int j = 0; j < 8; j++) {
				cogwheels[i][j] = poles[j] - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			rotate(num, dir);
		}

		int cnt = 0;
		for (int i = 1; i <= T; i++) {
			if (cogwheels[i][0] == 1)
				cnt++;
		}

		System.out.print(cnt);
	}

	static void rotate(int num, int dir) {
		List<Integer> mustLeftRotate = new ArrayList<>();
		List<Integer> mustRightRotate = new ArrayList<>();

		if (dir == 1)
			mustRightRotate.add(num);
		else
			mustLeftRotate.add(num);

		int temp = num;
		int tempDir = -dir;

		//왼쪽 부분 회전해야할 톱니바퀴들 체크
		while (temp > 1) {
			if (cogwheels[temp][6] != cogwheels[temp - 1][2]) {
				if (tempDir == 1)
					mustRightRotate.add(temp - 1);
				else
					mustLeftRotate.add(temp - 1);

				temp--;
				tempDir = -tempDir;
				continue;
			}

			break;
		}

		temp = num;
		tempDir = -dir;

		//오른쪽 부분 회전해야할 톱니바퀴들 체크
		while (temp < T) {
			if (cogwheels[temp][2] != cogwheels[temp + 1][6]) {
				if (tempDir == 1)
					mustRightRotate.add(temp + 1);
				else
					mustLeftRotate.add(temp + 1);

				temp++;
				tempDir = -tempDir;
				continue;
			}

			break;
		}

		for (Integer i : mustRightRotate) {
			toRight(i);
		}

		for (Integer i : mustLeftRotate) {
			toLeft(i);
		}
	}

	static void toRight(int num) {
		int temp = cogwheels[num][7];
		for (int i = 6; i >= 0; i--) {
			cogwheels[num][i + 1] = cogwheels[num][i];
		}

		cogwheels[num][0] = temp;
	}

	static void toLeft(int num) {
		int temp = cogwheels[num][0];
		for (int i = 1; i <= 7; i++) {
			cogwheels[num][i - 1] = cogwheels[num][i];
		}

		cogwheels[num][7] = temp;
	}
}