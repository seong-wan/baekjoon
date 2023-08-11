import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, min, houseSize, srcSize;
	static List<int[]> house = new ArrayList<int[]>(), src = new ArrayList<int[]>(), tgt = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		input();
		process();

	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1)
					house.add(new int[] { i, j });
				else if (n == 2)
					src.add(new int[] { i, j });

			}
		}
	}

	static void process() {
		min = Integer.MAX_VALUE;
		houseSize = house.size();
		srcSize = src.size();

		comb(0, 0);
		System.out.println(min);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			// 치킨집 M개를 조합으로 뽑은 상태
			// 이 조합의 치킨 거리의 합을 구하고 최솟값이면 갱신
			// 모든 집 각각에 대해서 뽑힌 M개의 치킨집 거리 중 최소인 것을 찾아 합을 구해야 함
			int sum = 0; // 현재 조합의 치킨 거리의 총 합
			for (int i = 0; i < houseSize; i++) {
				int dist = Integer.MAX_VALUE;
				int[] h = house.get(i);
				for (int j = 0; j < M; j++) {
					int[] c = tgt.get(j);
					dist = Math.min(dist, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
				}
				sum += dist;
			}
			min = Math.min(min, sum);

			return;
		}
		if (srcIdx == srcSize)
			return;

		tgt.add(src.get(srcIdx));
		comb(srcIdx + 1, tgtIdx + 1); // 선택 o
		tgt.remove(src.get(srcIdx));// 선택 원복
		comb(srcIdx + 1, tgtIdx);// 선택 x <=배열은 자연스럽게 다음 index를 덮어 쓰는 구조(원복 필요)
	}
}
