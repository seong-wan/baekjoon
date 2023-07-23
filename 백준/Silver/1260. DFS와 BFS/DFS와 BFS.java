
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private int numVertices;
	private List<Integer>[] adjacencyList;

	public Main(int numVertices) {
		this.numVertices = numVertices;
		adjacencyList = new ArrayList[numVertices + 1]; // 정점 번호가 1부터 시작하므로 배열 크기를 numVertices + 1로 설정
		for (int i = 1; i <= numVertices; i++) {
			adjacencyList[i] = new ArrayList<>();
		}
	}

	public void addEdge(int source, int destination) {
		adjacencyList[source].add(destination);
		adjacencyList[destination].add(source); // 양방향 간선 추가
	}

	public void dfs(int startVertex) {
		boolean[] visited = new boolean[numVertices + 1];
		dfsRecursive(startVertex, visited);
	}

	private void dfsRecursive(int vertex, boolean[] visited) {
		visited[vertex] = true;
		System.out.print(vertex + " ");

		Collections.sort(adjacencyList[vertex]);
		for (int neighbor : adjacencyList[vertex]) {
			if (!visited[neighbor]) {
				dfsRecursive(neighbor, visited);
			}
		}
	}

	public void bfs(int startVertex) {
		boolean[] visited = new boolean[numVertices + 1];
		Queue<Integer> queue = new LinkedList<>();

		visited[startVertex] = true;
		queue.add(startVertex);

		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			System.out.print(vertex + " ");

			for (int neighbor : adjacencyList[vertex]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numVertices = scanner.nextInt();
		int numEdges = scanner.nextInt();
		int startVertex = scanner.nextInt();

		Main graph = new Main(numVertices);

		for (int i = 0; i < numEdges; i++) {
			int source = scanner.nextInt();
			int destination = scanner.nextInt();
			graph.addEdge(source, destination);
		}

		graph.dfs(startVertex);
		System.out.println();

		graph.bfs(startVertex);
		System.out.println();

		scanner.close();
	}
}
