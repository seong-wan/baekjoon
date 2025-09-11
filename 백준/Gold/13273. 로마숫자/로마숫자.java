import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder sb = new StringBuilder();
	static Map<Integer, String> map = new HashMap<>();
	static Map<Character, Integer> seq = new HashMap<>();

	public static void main(String[] args) throws Exception {
		init();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			int firstValue = s.charAt(0) - '0';

			//숫자인 경우
			if (isNum(firstValue)) {
				for (int j = 0; j < s.length(); j++) {
					int value = (s.charAt(j) - '0') * (int)Math.pow(10, s.length() - 1 - j);

					if (value == 0)
						continue;

					sb.append(map.get(value));
				}

				sb.append("\n");
			}
			//문자인 경우
			else {
				int num = 0;
				for (int j = 0; j < s.length(); j++) {
					int value = seq.get(s.charAt(j));

					if (j < s.length() - 1 && value < seq.get(s.charAt(j + 1))) {
						num -= value;
					} else {
						num += value;
					}
				}

				sb.append(num).append("\n");
			}
		}

		System.out.print(sb);
	}

	static boolean isNum(int c) {
		return c >= 0 && c <= 9;
	}

	static void init() {
		map.put(1, "I");
		map.put(2, "II");
		map.put(3, "III");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(6, "VI");
		map.put(7, "VII");
		map.put(8, "VIII");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(20, "XX");
		map.put(30, "XXX");
		map.put(40, "XL");
		map.put(50, "L");
		map.put(60, "LX");
		map.put(70, "LXX");
		map.put(80, "LXXX");
		map.put(90, "XC");
		map.put(100, "C");
		map.put(200, "CC");
		map.put(300, "CCC");
		map.put(400, "CD");
		map.put(500, "D");
		map.put(600, "DC");
		map.put(700, "DCC");
		map.put(800, "DCCC");
		map.put(900, "CM");
		map.put(1000, "M");
		map.put(2000, "MM");
		map.put(3000, "MMM");

		seq.put('I', 1);
		seq.put('V', 5);
		seq.put('X', 10);
		seq.put('L', 50);
		seq.put('C', 100);
		seq.put('D', 500);
		seq.put('M', 1000);
	}
}