import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static boolean[][][] visit;
    static int N, M, K;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        map = new int[N][M];
        visit = new boolean[N][M][K + 1];

        //맵 입력
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        //r,c,breakCount, day/night(0,1);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        visit[0][0][0] = true;
        int depth = 1;

        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cr = cur[0];
                int cc = cur[1];
                int bCount = cur[2];
                int night = cur[3];

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cr + dr[dir];
                    int nc = cc + dc[dir];

                    if (canGo(nr, nc)) {
                        int nNight = night == 1 ? 0 : 1;
                        if (map[nr][nc] == 0 && !visit[nr][nc][bCount]) {
                            if (nr == N - 1 && nc == M - 1)
                                return depth;

                            visit[nr][nc][bCount] = true;
                            queue.add(new int[]{nr, nc, bCount, nNight});
                        }
                        //다음 상황이 벽일 때 벽을 부술 수 있는 상황
                        else if (map[nr][nc] == 1 && bCount < K && night == 0 && !visit[nr][nc][bCount + 1]) {
                            if (nr == N - 1 && nc == M - 1)
                                return depth;

                            visit[nr][nc][bCount + 1] = true;
                            queue.add(new int[]{nr, nc, bCount + 1, 1});
                        }

                        //밤이라서 벽을 위해 제자리에 있는 상황
                        else if (map[nr][nc] == 1 && bCount < K && night == 1) {
                            queue.add(new int[]{cr, cc, bCount, 0});
                        }
                    }
                }
            }
        }
        return -1;
    }

    static boolean canGo(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}