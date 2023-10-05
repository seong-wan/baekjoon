import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] pi;

	public static void main(String[] args) throws Exception {
		while (true) {
			String s = br.readLine();
			if (s.equals("."))
				break;
			makePi(s);

		}
		System.out.println(sb);

	}

	static void makePi(String p) {
		pi = new int[p.length()];
		char[] pArray = p.toCharArray();
		// j 접두사 쪽 인덱스, i 접미사 쪽 인덱스
		int j = 0;
		for (int i = 1; i < pArray.length; i++) {

			// i번째 값(뒤쪽) j번째 값(앞쪽) 값이 일치하거나,j==0이면 while문 벗어난다.
			while (j > 0 && pArray[i] != pArray[j])
				j = pi[j - 1];

			// 현재 시점은 j ==0 이거나 i번째 값과 j번째 값이 일치
			if (pArray[i] == pArray[j])
				pi[i] = ++j;

		}
		if (p.length() % (p.length() - pi[p.length() - 1]) != 0)
			sb.append(1).append("\n");
		else
			sb.append(p.length() / (p.length() - pi[p.length() - 1])).append("\n");
	}
}