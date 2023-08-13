import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int A, B, cnt;// 바꿀 수,목표 수,바꾸는 데 필요한 연산
	static boolean chk;// 목표값으로 바뀌는지 체크

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		dfs(A, 1);// A를 cnt 1부터 시작하면서 연산을 해봄
		if (!chk)
			System.out.println(-1);// 변경 불가능하면 -1출력
	}

	static void dfs(long num, int cnt) {
		if (num > B)
			return;
		if (num == B) {
			chk = true;
			System.out.println(cnt);
			return;
		}
		dfs(num * 2, cnt + 1);
		dfs(num * 10 + 1, cnt + 1);
	}

}
