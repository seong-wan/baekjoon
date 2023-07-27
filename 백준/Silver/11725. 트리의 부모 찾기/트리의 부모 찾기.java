import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

       
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>()); // 인접 리스트 생성
        }

       
        for (int i = 0; i < N - 1; i++) {
            String[] nodes = br.readLine().split(" ");
            int node1 = Integer.parseInt(nodes[0]);
            int node2 = Integer.parseInt(nodes[1]); // 트리 상에서 연결된 두 정점 입력 받아 인접 리스트에 추가

            
            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);  // 양방향 간선 추가
        }

       
        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);  // 부모 노드를 저장할 배열

        
        bfs(adjList, parent); // BFS를 통해 각 노드의 부모 노드 구하기

        // 결과 출력
        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }

    private static void bfs(List<List<Integer>> adjList, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 루트 노드인 1부터 시작
        parent[1] = 0; // 루트 노드의 부모는 0으로 표시

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adjList.get(current)) {
                if (parent[neighbor] == -1) {
                    parent[neighbor] = current; // 인접한 노드의 부모를 현재 노드로 설정
                    queue.add(neighbor);
                }
            }
        }
    }
}
