import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int curIdx;
	static List<Integer> nodes = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		String input = " ";

		while ((input = br.readLine()) != null && !input.isEmpty()) {
			int value = Integer.parseInt(input);
			nodes.add(value);
		}

		postOrder(Integer.MIN_VALUE, Integer.MAX_VALUE);

		System.out.print(sb);
	}

	static void postOrder(int min, int max) {
		if (curIdx == nodes.size())
			return;

		int value = nodes.get(curIdx);

		if (min > value || max < value)
			return;

		curIdx++;

		postOrder(min, value);
		postOrder(value, max);

		sb.append(value).append("\n");
	}
}