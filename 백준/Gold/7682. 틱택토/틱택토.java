import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[] input;
	static int X, O;

	public static void main(String[] args) throws Exception {
		while (true) {
			String s = br.readLine();
			X = 0;// 초기화
			O = 0;
			if (s.equals("end")) {// end가 나오면 종료
				System.out.println(sb);
				return;
			}
			input = s.toCharArray();
			for (int i = 0; i < 9; i++) {
				if (input[i] == 'X')
					X++;
				else if (input[i] == 'O')
					O++;
			} // X와 0의 개수를 구함
			if (X < 3 || O > X) {// 어떻게 해도 끝이 날 수가 없는 상황
				sb.append("invalid" + "\n");
				continue;
			}
			if (X == O)// 0가 X와 개수가 같다면 O가 이기고 X가 이기지 못하면서 게임이 끝나야 함
				if (!checkX() && checkO()) {
					sb.append("valid" + "\n");
					continue;
				} else {
					sb.append("invalid" + "\n");
					continue;
				}

			else if (X == O + 1)// X가 이기고 끝나는 상황
				if (X + O == 9) {// 꽉 차있는 경우 O가 이기지만 않으면 유효
					if (checkO()) {
						sb.append("invalid" + "\n");
						continue;
					} else {
						sb.append("valid" + "\n");
						continue;
					}
				} else {// X가 이기고 O가 이기지 못하는 상황이어야 유효
					if (checkX() && !checkO()) {
						sb.append("valid" + "\n");
						continue;
					} else {
						sb.append("invalid" + "\n");
						continue;
					}
				}

			else// 개수가 불가능한 상황
				sb.append("invalid" + "\n");
		}
	}

	static boolean checkX() {
		char temp = input[0];// 첫 번째 칸을 기준으로 확인
		if (temp == 'X') {
			if (input[1] == temp && input[2] == temp)
				return true;
			if (input[3] == temp && input[6] == temp)
				return true;
			if (input[4] == temp && input[8] == temp)
				return true;
		}

		temp = input[1];// 2번째 칸을 기준으로 확인
		if (temp == 'X') {
			if (input[4] == temp && input[7] == temp)
				return true;
		}

		temp = input[2];// 3번째 칸을 기준으로 확인
		if (temp == 'X') {
			if (input[5] == temp && input[8] == temp)
				return true;
			if (input[4] == temp && input[6] == temp)
				return true;
		}

		temp = input[3];// 4 번째 칸
		if (temp == 'X') {
			if (input[4] == temp && input[5] == temp)
				return true;
		}

		temp = input[6];
		if (temp == 'X') {
			if (input[7] == temp && input[8] == temp)
				return true;
		}

		return false;
	}

	static boolean checkO() {
		char temp = input[0];// 첫 번째 칸을 기준으로 확인
		if (temp == 'O') {
			if (input[1] == temp && input[2] == temp)
				return true;
			if (input[3] == temp && input[6] == temp)
				return true;
			if (input[4] == temp && input[8] == temp)
				return true;
		}

		temp = input[1];// 2번째 칸을 기준으로 확인
		if (temp == 'O') {
			if (input[4] == temp && input[7] == temp)
				return true;
		}

		temp = input[2];// 3번째 칸을 기준으로 확인
		if (temp == 'O') {
			if (input[5] == temp && input[8] == temp)
				return true;
			if (input[4] == temp && input[6] == temp)
				return true;
		}

		temp = input[3];// 4 번째 칸
		if (temp == 'O') {
			if (input[4] == temp && input[5] == temp)
				return true;
		}

		temp = input[6];
		if (temp == 'O') {
			if (input[7] == temp && input[8] == temp)
				return true;
		}

		return false;
	}
}