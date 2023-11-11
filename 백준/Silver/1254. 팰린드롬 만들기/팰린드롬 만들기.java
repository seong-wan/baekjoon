import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {// 문자열 앞과 뒤를 비교하면서 두 문자가 같다면 포인터가 같이 이동
	// 두 문자가 일치하지 않으면 문자열 뒤에 앞의 문자를 추가 후 다시 처음부터 비교
	// 두 포인터가 서로 교차하는 시점이 비교가 끝나는 시점
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		String input = br.readLine();
		String plus = "";// 추가할 문자열
		int fidx = 0;
		int pidx = 0;
		int lidx = input.length() - 1;
		String temp = input;
		while (fidx < lidx) {// 두 포인터가 같아질 때까지 이동
			if (temp.charAt(fidx) == temp.charAt(lidx)) {// 비교하는 두 문자가 같은 경우
				fidx++;
				lidx--;// 두 포인터가 같이 이동
			} else {// 비교하는 두 문자가 다른 경우
				plus = input.charAt(pidx++) + plus;// 뒤에 추가할 문자열
				temp = input + plus;
				fidx = 0;
				lidx = temp.length() - 1;// 포인터 초기화
			}
		}
		System.out.println(input.length() + plus.length());
	}
}