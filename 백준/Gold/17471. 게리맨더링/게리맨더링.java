import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] population;
	static List<Integer>[] adlist;
	static int N, total, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		population = new int[N];
		adlist = new List[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			total += population[i];
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				adlist[i].add(to);
				adlist[to].add(i);
			}
		}

		for (int i = 1; i <= N / 2; i++) {
			boolean[] select = new boolean[N];
			// A 선거구의 조합을 구함
			comb(select, 0, i, 0);
		}

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void comb(boolean[] select, int idx, int tgtSize, int tgtIdx) {
		if (tgtIdx == tgtSize) {
			// A와 B 선거구로 나누기
			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (select[i])
					aList.add(i);
				else
					bList.add(i);
			}
			// A와 B 선거구가 연결되어 있는지 확인
			if (isConnected(aList) && isConnected(bList)) {
				int aPopulation = 0, bPopulation = 0;
				for (int a : aList)
					aPopulation += population[a];
				for (int b : bList)
					bPopulation += population[b];
				min = Math.min(min, Math.abs(aPopulation - bPopulation));
			}
			return;
		}
		if (idx == N)
			return;
		select[idx] = true;
		comb(select, idx + 1, tgtSize, tgtIdx + 1);
		select[idx] = false;
		comb(select, idx + 1, tgtSize, tgtIdx);
	}

	static boolean isConnected(List<Integer> list) {
		boolean[] visited = new boolean[N];
		for (int node : list) {
			if (!visited[node]) {
				dfs(node, visited, list);
				break;
			}
		}
		for (int node : list) {
			if (!visited[node])
				return false;
		}
		return true;
	}

	static void dfs(int node, boolean[] visited, List<Integer> list) {
		visited[node] = true;
		for (int next : adlist[node]) {
			if (list.contains(next) && !visited[next]) {
				dfs(next, visited, list);
			}
		}
	}
}