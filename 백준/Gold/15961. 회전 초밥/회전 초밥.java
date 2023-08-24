import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, d, k, c;
	static int[] belt, visited;// 벨트 위의 초밥 정보, 종류별 먹은 수 체크
	static int kind = 1;// 먹은 접시,종류(쿠폰 초밥이 있으니 1부터 설정)
	static int ans;// 가짓수 최대

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		visited = new int[d + 1];// 0은 더미
		visited[c] += 1;
		belt = new int[N + k - 1];

		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());

		} // 벨트 위 초밥 정보 입력
		for (int i = N; i < N + k - 1; i++) {
			belt[i] = belt[i - N];
		} // 레일이 한 바퀴 돈 후 처음과 이어져야 하므로 더 추가

		find();
		System.out.println(ans);
	}

	static void find() {
		for (int i = 0; i < k; i++) {
			if (visited[belt[i]] == 0) {
				kind++;// 먹어 보지 못했다면 종류 수 +1
			}
			visited[belt[i]]++; // 종류별 먹은 수 체크
		}
		ans = Math.max(ans, kind);
		for (int j = 0; j < N - 1; j++) {
			int sub = belt[j];
			int add = belt[j + k];

			visited[sub]--;
			if (visited[sub] == 0) {
				kind--;
			}
			if (visited[add] == 0) {
				kind++;// 먹어 보지 못했다면 종류 수 +1
			}
			visited[add]++;// 먹었으니 체크
			ans = Math.max(ans, kind);// kind의 최대값을 매번 체크
		}

	}

}