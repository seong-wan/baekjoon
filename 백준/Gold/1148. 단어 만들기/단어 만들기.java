import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<String> dict = new ArrayList<>();
	static List<String> puzzles = new ArrayList<>();
	static int[][] dictCount, puzzlesCount;
	static int[] result;
	static int minCount, maxCount;
	static List<Character> minList = new ArrayList<>(), maxList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		while (true) {
			String s = br.readLine();

			if (s.equals("-"))
				break;

			dict.add(s);
		}

		dictCount = new int[dict.size()][26];

		for (int i = 0; i < dict.size(); i++) {
			for (int j = 0; j < dict.get(i).length(); j++) {
				char temp = dict.get(i).charAt(j);

				dictCount[i][temp - 'A']++;
			}
		}

		while (true) {
			String s = br.readLine();

			if (s.equals("#"))
				break;

			puzzles.add(s);
		}

		puzzlesCount = new int[puzzles.size()][26];

		for (int i = 0; i < puzzles.size(); i++) {
			for (int j = 0; j < puzzles.get(i).length(); j++) {
				char temp = puzzles.get(i).charAt(j);

				puzzlesCount[i][temp - 'A']++;
			}
		}

		for (int i = 0; i < puzzles.size(); i++) {
			result = new int[26];
			for (int j = 0; j < dict.size(); j++) {
				boolean check = true;

				for (int k = 0; k < 26; k++) {
					if (dictCount[j][k] > puzzlesCount[i][k]) {
						check = false;
						break;
					}
				}

				if (check)
					for (int k = 0; k < 26; k++) {
						if (dictCount[j][k] >= 1)
							result[k]++;
					}
			}
			minCount = Integer.MAX_VALUE;
			maxCount = 0;
			minList.clear();
			maxList.clear();

			for (int j = 0; j < 26; j++) {
				if (puzzlesCount[i][j] == 0)
					continue;

				if (result[j] > maxCount) {
					maxCount = result[j];
					maxList.clear();
					maxList.add((char)(j + 'A'));
				} else if (result[j] == maxCount) {
					maxList.add((char)(j + 'A'));
				}

				if (result[j] < minCount) {
					minCount = result[j];
					minList.clear();
					minList.add((char)(j + 'A'));
				} else if (result[j] == minCount) {
					minList.add((char)(j + 'A'));
				}
			}

			for (Character c : minList) {
				sb.append(c);
			}

			sb.append(" ").append(minCount).append(" ");

			for (Character c : maxList) {
				sb.append(c);
			}

			sb.append(" ").append(maxCount).append("\n");
		}

		System.out.println(sb);
	}
}