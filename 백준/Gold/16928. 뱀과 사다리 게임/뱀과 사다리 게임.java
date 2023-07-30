import java.util.*;

public class Main {

    static int[] board;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 사다리의 수
        int M = scanner.nextInt(); // 뱀의 수

        board = new int[101]; // 보드판 정보 (인덱스 1부터 사용)
        visited = new boolean[101]; // 방문 여부

        // 사다리 + 뱀 정보 입력
        for (int i = 0; i < N+M; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            board[x] = y;
        }

        int answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 시작점은 1번 칸
        visited[1] = true;

        int count = 0; // 주사위 굴린 횟수

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                for (int dice = 1; dice <= 6; dice++) {
                    int next = current + dice;

                    if (next <= 100 && !visited[next]) {
                        if (board[next] != 0) { // 사다리 또는 뱀이 있는 경우
                            next = board[next];
                        }

                        if (next == 100) {
                            return count; // 도착점에 도달한 경우 최단 경로 반환
                        }

                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return 0; 
    }
}
