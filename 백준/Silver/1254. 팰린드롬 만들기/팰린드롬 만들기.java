import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {// 문자열 앞과 뒤를 비교하면서 두 문자가 같다면 포인터가 같이 이동
	// 두 문자가 일치하지 않으면 문자열 뒤에 앞의 문자를 추가했다고 치고 앞읨 문자 제거 후 다시 처음부터 비교
	// 두 포인터가 서로 교차하는 시점이 비교가 끝나는 시점
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		String input = br.readLine();
		int fidx = 0;
		int lidx = input.length() - 1;
		int ans = lidx + 1;

		while (fidx < lidx) {// 두 포인터가 같아질 때까지 이동
			if (input.charAt(fidx) == input.charAt(lidx)) {// 비교하는 두 문자가 같은 경우
				fidx++;
				lidx--;// 두 포인터가 같이 이동
			} else {// 비교하는 두 문자가 다른 경우
				input = input.substring(1);
				fidx = 0;
				lidx = input.length() - 1;// 포인터 초기화
				ans++;
			}
		}
		System.out.println(ans);
	}
}