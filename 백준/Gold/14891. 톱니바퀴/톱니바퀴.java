import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int T,K;
	static int[][] board;
	static boolean[] check; // 극이 다르면 true
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[5][8];

		for (int i = 1; i <= 4; i++) {
			char[] a = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				board[i][j] = Character.getNumericValue(a[j]);
			}
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			visit = new boolean[5]; // 0 dummy;
			check = new boolean[4]; // 0 dummy
			// 일단 맞닿아있는 4개 다 보자.
			for (int j = 1; j < 4; j++) {
				if( board[j][2] != board[j+1][6]) check[j] = true; 
			}
			
			rotation(num, dir);

		}
		
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
			if( board[i][0] == 1) {
				sum += Math.pow(2, i-1);
			}
		}
		
		System.out.println(sum);
		
	}
	
	static void rotation(int num, int dir) {
		
		if( visit[num] ) return;
		
		// 회전하기
		if( dir == 1) { // 시계방향
			int[] temp = new int[8];
			for (int i = 1; i < 8; i++) {
				temp[i] = board[num][i-1];
			}
			temp[0] = board[num][7];
			
			for (int i = 0; i < 8; i++) {
				board[num][i] = temp[i];
			}
			
		}else { // 반시계방향
			int[] temp = new int[8];
			for (int i = 1; i < 8 ; i++) {
				temp[i-1] = board[num][i];
			}
			temp[7] = board[num][0];
			
			for (int i = 0; i < 8; i++) {
				board[num][i] = temp[i];
			}
		}
		visit[num] = true;
		
		// 왼쪽 오른쪽 확인해서 다음 재귀 호출하기
		if( num == 2) { // 2
			
			// 왼쪽 확인
			if( check[1] ) { // true이면 
				
				rotation(num-1, -dir);
			}
			// 오른쪽 확인
			if( check[2]) {
				
				rotation(num+1, -dir);
			}
			
		}else if( num == 3) {
			// 왼쪽 확인
			if( check[2] ) { // true이면 
				rotation(num-1, -dir);
				
			}
			// 오른쪽 확인
			if( check[3]) {
				rotation(num+1, -dir);
				
			}
		}else {
			if( num == 1) {
				if( check[1] ) {
					rotation(num+1, -dir);
				}
			}else {
				if( check[3]) {
					rotation(num - 1, -dir);
				}
			}
		}
		
	}

}