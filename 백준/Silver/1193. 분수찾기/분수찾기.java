import java.io.BufferedReader;
import java.io.InputStreamReader;

//분수의 분모 +1 -> 분자+1 뒤-1 *1 -> 분자+1 ->분자-1 뒤+1 *2 ->... 반복
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int X, cnt = 0;
	static int[] up;
	static int[] down;

	public static void main(String[] args) throws Exception {
		X = Integer.parseInt(br.readLine());
		up = new int[X + 1];// X까지 분자
		down = new int[X + 1];// X까지 분모
		boolean chk = false;
		up[1] = 1;
		down[1] = 1;
		int i = 1;
		while (true) {
			if (X == i)
				break;
			cnt++;
			if (!chk) {
				i++;
				up[i] = up[i - 1];
				down[i] = down[i - 1] + 1;
				if (X == i)
					break;
				for (int j = 0; j < cnt; j++) {
					i++;
					up[i] = up[i - 1] + 1;
					down[i] = down[i - 1] - 1;
					if (X == i)
						break;
				}
			} else {
				i++;
				up[i] = up[i - 1] + 1;
				down[i] = down[i - 1];
				if (X == i)
					break;
				for (int j = 0; j < cnt; j++) {
					i++;
					up[i] = up[i - 1] - 1;
					down[i] = down[i - 1] + 1;
					if (X == i)
						break;
				}
			}
			chk = !chk;
		}
		System.out.println(up[X] + "/" + down[X]);
	}
}