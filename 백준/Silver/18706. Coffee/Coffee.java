import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, C, P;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Map<String, int[]> coffee;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			coffee = new HashMap<>();

			int deliveryFee = 100 / P;

			for (int i = 0; i < C; i++) {
				st = new StringTokenizer(br.readLine());
				String coffeeName = st.nextToken();

				int[] price = new int[3];
				for (int j = 0; j < 3; j++) {
					price[j] = Integer.parseInt(st.nextToken());
				}

				coffee.put(coffeeName, price);
			}

			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String sizeName = st.nextToken();
				String coffeeName = st.nextToken();

				int size = 0;
				if (sizeName.equals("medium"))
					size = 1;
				else if (sizeName.equals("large"))
					size = 2;

				int fee = coffee.get(coffeeName)[size] + deliveryFee;

				int rest = fee % 10;
				if (rest == 1)
					fee--;
				else if (rest == 9)
					fee++;
				else if (rest == 4)
					fee++;
				else if (rest == 6)
					fee--;

				sb.append(name).append(" ").append(fee).append("\n");
			}
		}

		System.out.print(sb);
	}
}