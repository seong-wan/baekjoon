import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans = 1;
	static Map<Integer, List<Integer>> map = new HashMap<>();
	static boolean[] isDeleted;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(br.readLine());

			if (!map.containsKey(height))
				map.put(height, new ArrayList<>());

			map.get(height).add(i);
		}

		isDeleted = new boolean[N + 2];//0,N+1은 더미
		isDeleted[0] = true;
		isDeleted[N + 1] = true;

		List<Integer> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys);

		int temp = 1;
		for (Integer key : keys) {
			for (Integer idx : map.get(key)) {
				isDeleted[idx] = true;

				//양쪽에 섬이 있던 상황
				if (!isDeleted[idx - 1] && !isDeleted[idx + 1]) {
					temp++;
					continue;
				}

				//양쪽에 물인 상황
				if (isDeleted[idx - 1] && isDeleted[idx + 1]) {
					temp--;
				}
			}

			ans = Math.max(ans, temp);
		}

		System.out.print(ans);
	}
}