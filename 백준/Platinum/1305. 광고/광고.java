import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int L;
	static String S;
	static int[] pi;

	public static void main(String[] args) throws Exception {
		L = Integer.parseInt(br.readLine());
		S = br.readLine();
		makePi(S);
	}

	static void makePi(String p) {
		pi = new int[L];
		char[] pArray = p.toCharArray();
		// j 접두사 쪽 인덱스, i 접미사 쪽 인덱스
		int j = 0;
		for (int i = 1; i < L; i++) {

			// i번째 값(뒤쪽) j번째 값(앞쪽) 값이 일치하거나,j==0이면 while문 벗어난다.
			while (j > 0 && pArray[i] != pArray[j])
				j = pi[j - 1];

			// 현재 시점은 j ==0 이거나 i번째 값과 j번째 값이 일치
			if (pArray[i] == pArray[j])
				pi[i] = ++j;

		}
		System.out.println(L - pi[L - 1]);
	}
}