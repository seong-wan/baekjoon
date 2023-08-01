import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringBuilder sb = new StringBuilder();
		int count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		} // 배열에 스위치 초기 값 입력
		int s_num = Integer.parseInt(br.readLine());
		for (int i = 0; i < s_num; i++) {
			st = new StringTokenizer(br.readLine());

			switch (Integer.parseInt(st.nextToken())) {
			case 1: {
				int m = Integer.parseInt(st.nextToken());
				for (int j = m - 1; j < N; j += m) {
					arr[j] = swap(arr[j]);

				}
				break;
			} // 남학생일 때
			case 2:
				int g = Integer.parseInt(st.nextToken());
				int sx = g - 1;
				arr[sx] = swap(arr[sx]);

				int px = sx - 1;
				int nx = sx + 1;
				if (px == -1 || nx == N)
					break;
				while (arr[px] == arr[nx]) {
					arr[px] = swap(arr[px]);
					arr[nx] = swap(arr[nx]);
					px--;
					nx++;
					if (px == -1 || nx == N)
						break;

				}

			}
		}
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
			count++;
			if (count == 20) {
				sb.append("\n");
				count = 0;
			}
		}
		System.out.println(sb);

	}

	static int swap(int i) {
		if (i == 0) {
			return 1;
		} else {
			return 0;
		}
	}
}