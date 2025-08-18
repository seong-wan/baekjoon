import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1~9까지 돌면서 가로,세로,3*3 확인 후 가능한 자리라면 숫자 할당 후 다음 depth 진행
//가다가 막히면 그 전 depth에서 다음 되는 숫자를 할당 후 진행
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board = new int[9][9];
	static boolean chk;
	static boolean[][] horizontal = new boolean[9][10]; //가로 체크
	static boolean[][] vertical = new boolean[9][10]; //세로 체크
	static boolean[][] square = new boolean[9][10]; //3*3 체크

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if (board[i][j] != 0) {
					horizontal[i][board[i][j]] = true; //가로 체크
					vertical[j][board[i][j]] = true; //세로 체크
					square[calcSquare(i, j)][board[i][j]] = true; //3*3 체크
				}
			}
		}

		dfs(0);
		print();
	}

	static void dfs(int seq) {
		//스도쿠 완성
		if (seq == 81) {
			chk = true;
			return;
		}

		int r = seq / 9; //행
		int c = seq % 9; //열

		//빈 자리가 아닌 경우
		if (board[r][c] != 0) {
			dfs(seq + 1);
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (!horizontal[r][i] && !vertical[c][i] && !square[calcSquare(r, c)][i]) {
				horizontal[r][i] = true; //가로 체크
				vertical[c][i] = true; //세로 체크
				square[calcSquare(r, c)][i] = true; //3*3 체크
				board[r][c] = i;
				dfs(seq + 1);

				if (chk)
					return;

				board[r][c] = 0; //다시 빈 자리로 되돌림
				horizontal[r][i] = false; //가로 체크 해제
				vertical[c][i] = false; //세로 체크 해제
				square[calcSquare(r, c)][i] = false; //3*3 체크 해제
			}
		}
	}

	static int calcSquare(int r, int c) {
		return (r / 3) * 3 + (c / 3);
	}

	static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}