import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, L, hunter[], animal[][]; // 사대의 수, 동물의 수, 사정거리

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		hunter = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			hunter[i] = Integer.parseInt(st.nextToken());
		} // 사대의 위치 저장

		animal = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			animal[i] = new int[] { x, y };
		} // 동물 위치 저장

		Arrays.sort(hunter);// 사대의 위치를 오름차순으로 정렬

		int cnt = 0; // 동물이 사거리에 들어오면 ++
		for (int i = 0; i < N; i++) {
			cnt += bs(i);
		}
		System.out.println(cnt);
	}

	static int bs(int idx) {
		int left = 0;
		int right = M;
		int half = 0;

		int x = animal[idx][0];
		int y = animal[idx][1];

		while (left <= right) {
			half = (left + right) / 2;

			if (half >= M)
				return 0; // 사대 개수를 초과한 경우

			int dis = Math.abs(x - hunter[half]) + y;

			if (L < dis && x < hunter[half])
				right = half - 1;
			else if (L < dis && x >= hunter[half])
				left = half + 1;
			else if (L >= dis)
				return 1;
		}
		return 0;
	}

}
