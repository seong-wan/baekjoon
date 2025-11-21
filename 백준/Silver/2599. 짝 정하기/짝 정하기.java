import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] school = new int[3][2];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				school[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int A_Man = school[0][0];
		int B_Man = school[1][0];
		int C_Man = school[2][0];

		int A_Woman = school[0][1];
		int B_Woman = school[1][1];
		int C_Woman = school[2][1];

		for (int i = 0; i <= A_Man; i++) {
			int a_b = i;                // A남 -> B여
			int a_c = A_Man - i;        // A남 -> C여
			int c_b = B_Woman - a_b;
			int c_a = C_Man - c_b;
			int b_c = C_Woman - a_c;
			int b_a = B_Man - b_c;
			if (a_b >= 0 && a_c >= 0 && b_a >= 0 && b_c >= 0 && c_a >= 0 && c_b >= 0) {
				if (b_a + c_a == A_Woman) {
					// 가능한 경우를 찾았으므로 출력 후 종료
					sb.append(1).append("\n");
					sb.append(a_b).append(" ").append(a_c).append("\n");
					sb.append(b_a).append(" ").append(b_c).append("\n");
					sb.append(c_a).append(" ").append(c_b).append("\n");
					System.out.print(sb);
					return;
				}
			}
		}

		System.out.println(0);
	}
}