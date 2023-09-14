import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, sum;
	static List<Integer> mn = new ArrayList<>();// 음수 저장 리스트
	static List<Integer> pn = new ArrayList<>();// 양수 저장 리스트

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n > 0)
				pn.add(n);
			else
				mn.add(n);
		}

		Collections.sort(pn, Collections.reverseOrder());// 양수 내림차순 정렬
		Collections.sort(mn);// 음수 오름차순 정렬

		for (int i = 0; i < pn.size(); i++) {
			if (i + 1 < pn.size() && pn.get(i) != 1 && pn.get(i + 1) != 1)
				sum += pn.get(i++) * pn.get(i);
			else
				sum += pn.get(i);
		} // 양수 계산

		for (int i = 0; i < mn.size(); i++) {
			if (i + 1 < mn.size())
				sum += mn.get(i++) * mn.get(i);
			else
				sum += mn.get(i);
		} // 음수 계산

		System.out.println(sum);

	}
}
