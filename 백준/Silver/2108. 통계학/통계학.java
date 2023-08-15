import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] num;
	static double sum;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static int[] chk = new int[8001];// -4000~4000
	static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		for (int i = 0; i < N; i++) {
			sum += num[i] = Integer.parseInt(br.readLine());
			chk[num[i] + 4000] += 1;

		}
		result();
		System.out.println(sb);
	}

	static void result() {
		sb.append(Math.round(sum / N) + "\n");//산술평균
		Arrays.sort(num);
		sb.append(num[N / 2] + "\n");//중앙값
		int[] chk1 = Arrays.copyOf(chk, 8001);
		Arrays.sort(chk1);
		for (int i = 0; i < 8001; i++) {
			if (chk[i] == chk1[8000])
				list.add(i - 4000);
		}
		list.sort((n1, n2) -> n1 - n2);
		if (list.size() >= 2)
			sb.append(list.get(1) + "\n");//최빈값이 2개 이상일 때 2번째 수 출력
		else
			sb.append(list.get(0) + "\n");//최반값이 유일할 때
		sb.append(num[N - 1] - num[0] + "\n");//max-min
	}

}
