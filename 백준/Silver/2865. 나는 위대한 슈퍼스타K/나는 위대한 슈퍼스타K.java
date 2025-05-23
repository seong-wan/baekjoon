import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, K;
	static Map<Integer, Double> map = new HashMap<>();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				double score = Double.parseDouble(st.nextToken());

				if (!map.containsKey(num))
					map.put(num, score);
				else {
					if (map.get(num) < score)
						map.put(num, score);
				}
			}
		}

		List<Double> scoreList = new ArrayList<>(map.values());
		scoreList.sort((e1, e2) -> e2.compareTo(e1));

		double sum = 0;
		for (int i = 0; i < K; i++) {
			sum += scoreList.get(i);
		}

		System.out.printf("%.1f", sum);
	}
}