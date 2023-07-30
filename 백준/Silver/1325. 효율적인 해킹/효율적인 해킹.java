import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean[] visited;
    static List<Integer> hackingList;
    static int maxCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            graph[B].add(A); 
        }

        hackingList = new ArrayList<>();
        maxCount = 0;

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : hackingList) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    count++;
                }
            }
        }

        if (count > maxCount) {
            hackingList.clear();
            hackingList.add(start);
            maxCount = count;
        } else if (count == maxCount) {
            hackingList.add(start);
        }
    }
}
