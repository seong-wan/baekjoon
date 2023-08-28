import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T, N, min, maxcore,size;
	static int[][] map;
	static ArrayList<int[]> cores;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
  
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1) {// 가장자리에 있는 코어들은 계산할 필요 x
						cores.add(new int[] { i, j });
					}
				}
			}
            size=cores.size();

			min = Integer.MAX_VALUE;
			maxcore = 0;

			dfs(0, 0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}

	static void dfs(int coreidx, int corecnt, int length) {
        if(corecnt+(size-coreidx)<maxcore)
           return;
        if (coreidx == size) {// 가장자리를 제외한 코어들을 모두 연결해봤을 때 종료
			if (corecnt > maxcore) {
				maxcore = corecnt;
				min = length;// 현재까지 작동한 코어 수보다 많은 수가 작동했다면 변경하면서 min값도 바꿔줌
			} else if (corecnt == maxcore) {// 같은 코어 수가 작동했을 때는 연결된 전선의 최소값을 찾아줌
				min = Math.min(min, length);
			}
			return;
		}

		int[] core = cores.get(coreidx);
		int r = core[0], c = core[1];

		for (int d = 0; d < 4; d++) {// 코어별로 4방향으로 전선 연결 시도
			int len = wiretoedge(r, c, d);
			if (len > 0) {
				setwire(r, c, d, 2);// 전선이 설치되는 곳은 2로 표시
				dfs(coreidx + 1, corecnt + 1, length + len);
				setwire(r, c, d, 0);// 원복
			}
		}

		dfs(coreidx + 1, corecnt, length);// 해당 코어를 연결하지 않고 넘어가보는 경우(모든 경우를 확인하기 위해)
	}

	static int wiretoedge(int r, int c, int dir) {// 전선이 연결이 되는 방향인지 확인
		int nr = dr[dir], nc = dc[dir];
		int len = 0;

		while (true) {
			r += nr;
			c += nc;
			if (r < 0 || r >= N || c < 0 || c >= N)
				break;// 가장자리까지 연결가능 현재까지 저장된 len값 리턴
			if (map[r][c] != 0)// 중간에 다른 코어가 있는 경우 연결 불가
				return 0;
			len++;
		}

		return len;
	}

	static void setwire(int r, int c, int dir, int val) {
		int nr = dr[dir], nc = dc[dir];

		while (true) {
			r += nr;
			c += nc;
			if (r < 0 || r >= N || c < 0 || c >= N)
				break;
			map[r][c] = val;
		}
	}// 연결이 되는 방향에 전선을 연결해보고 다른 코어들 연결 진행(가로지르는 상황을 피하기 위해)
}
