import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] sort = new int[3];

	public static void main(String[] args) throws IOException {
		while (true) {
			st = new StringTokenizer(br.readLine());
			sort[0] = Integer.parseInt(st.nextToken());
			sort[1] = Integer.parseInt(st.nextToken());
			sort[2] = Integer.parseInt(st.nextToken());
			if (sort[0] == 0)
				break;
			right_check();

		}
	}

	static void right_check() {
		Arrays.sort(sort);
		if (Math.pow(sort[0], 2) + Math.pow(sort[1], 2) == Math.pow(sort[2], 2))
			System.out.println("right");
		else
			System.out.println("wrong");
	}

}
