import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] gameBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                gameBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][N];
        System.out.println(canWin(gameBoard, visited, 0, 0) ? "HaruHaru" : "Hing");
    }

    private static boolean canWin(int[][] gameBoard, boolean[][] visited, int row, int col) {
        int N = gameBoard.length;

        // 끝 점(오른쪽 맨 아래 칸)에 도달한 경우, 승리로 종료
        if (row == N - 1 && col == N - 1) {
            return true;
        }

        // 현재 위치 방문 처리
        visited[row][col] = true;

        int jumpSize = gameBoard[row][col];

        // 오른쪽으로 이동
        if (col + jumpSize < N && !visited[row][col + jumpSize]) {
            if (canWin(gameBoard, visited, row, col + jumpSize)) {
                return true;
            }
        }

        // 아래로 이동
        if (row + jumpSize < N && !visited[row + jumpSize][col]) {
            if (canWin(gameBoard, visited, row + jumpSize, col)) {
                return true;
            }
        }

        return false;
    }
}
