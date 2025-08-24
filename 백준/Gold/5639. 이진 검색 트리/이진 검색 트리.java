import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static class Node {
		Node left;
		Node right;
		int value;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Node root = new Node();
	static Deque<Node> stack = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		//루트값
		String input = br.readLine();
		Node curNode = root;
		curNode.value = Integer.parseInt(input);

		//후에 오른쪽 자식 노드를 채우기 위해 스택에 저장
		stack.push(curNode);

		while ((input = br.readLine()) != null && !input.isEmpty()) {
			int value = Integer.parseInt(input);

			//왼쪽을 도는 경우
			if (value < curNode.value) {
				curNode = curNode.left = new Node();
				curNode.value = value;
				stack.push(curNode);
			}

			//오른쪽을 순회하는 경우
			else {
				Node curStack = stack.pop();

				while (!stack.isEmpty() && value > stack.peek().value) {
					curStack = stack.pop();
				}

				curNode = curStack.right = new Node();
				curNode.value = value;
				stack.push(curNode);
			}
		}

		postOrder(root);
	}

	static void postOrder(Node node) {
		Node left = node.left;
		Node right = node.right;

		//왼쪽 노드가 있다면 먼저 탐색
		if (left != null)
			postOrder(left);

		//오른쪽 탐색
		if (right != null)
			postOrder(right);

		System.out.println(node.value);
	}
}