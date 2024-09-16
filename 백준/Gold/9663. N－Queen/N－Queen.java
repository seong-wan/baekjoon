import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int[][] board;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {1, 0, -1, -1, -1, 0, 1, 1};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            dfs(0, i, 1);
        }

        System.out.println(ans);
    }

    static void dfs(int r, int c, int count) {
        if (count == N) {
            ans += 1;
            return;
        }

        create(r, c);
        for (int j = 0; j < N; j++) {
            if (board[r + 1][j] == 0) {
                dfs(r + 1, j, count + 1);
            }
        }

        remove(r, c);
    }

    static void create(int r, int c) {
        board[r][c] += 1;

        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            while (canGo(nr, nc)) {
                board[nr][nc] += 1;
                nr += dr[dir];
                nc += dc[dir];
            }
        }
    }

    static void remove(int r, int c) {
        board[r][c] -= 1;

        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            while (canGo(nr, nc)) {
                board[nr][nc] -= 1;
                nr += dr[dir];
                nc += dc[dir];
            }
        }
    }

    static boolean canGo(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}