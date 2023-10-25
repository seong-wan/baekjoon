import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Integer>[] count;
	static int T, K, min, max;
	static String W;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			count = new List[26];
			for (int i = 0; i < 26; i++) {
				count[i] = new ArrayList<>();
			}
			for (int i = 0; i < W.length(); i++) {
				int temp = W.charAt(i) - 'a';
				count[temp].add(i);// 리스트에 문자별 인덱스 값 입력
				if (count[temp].size() == K) {
					min = Math.min(min, i - count[temp].get(0) + 1);
					max = Math.max(max, i - count[temp].get(0) + 1);
					count[temp].remove(0);// 다음 비교를 위해 맨 처음 값은 제외
				} // 어떤 문자가 K개를 만족할 때의 가장 짧은 값과 가장 긴 값을 구함
					// 가장 짧은 문자열이 되기 위해서는 앞 뒤가 해당 문자로 같으면서 가장 짧아야 하고
					// 가장 긴 문자열의 경우에도 K개를 만족하는 해당 문자가 앞 뒤로 있는 경우이므로
					// K개가 되는 순간에 가장 긴 값을 찾으면 됨
			}
			if (min == Integer.MAX_VALUE)
				sb.append(-1 + "\n");
			else
				sb.append(min + " " + max + "\n");
		}
		System.out.println(sb);
	}
}