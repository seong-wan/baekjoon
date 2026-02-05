import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String temp = br.readLine();

			int turnPoint = -1;
			int left = 0;
			int right = temp.length() - 1;

			while (left < right) {
				//짝이 맞는 경우
				if (temp.charAt(left) == temp.charAt(right)) {
					left++;
					right--;
				}
				//짝이 맞지 않는 경우 - 처음부터 해당 지점까지를 reverse하고 뒤에 붙여야함
				else {
					turnPoint = left;
					left++;
					right = temp.length() - 1;
				}
			}

			sb.append(temp);
			if (turnPoint != -1) {
				StringBuilder reversePart = new StringBuilder(temp.substring(0, turnPoint + 1)).reverse();
				sb.append(reversePart);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}