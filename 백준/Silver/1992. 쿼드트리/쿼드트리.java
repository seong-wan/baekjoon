import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String s;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int spaces[][];
	static int black, white;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		spaces = new int[N][N];
		for (int r = 0; r < N; r++) {
			s = br.readLine();
			for (int c = 0; c < N; c++) {
				spaces[r][c] = s.charAt(c) - 48;//문자로 받으므로 48빼기
			}

		} // spaces배열에 공간 정보 채우기
		makeSpace(0, 0, N);
		System.out.println(sb);

	}

	static void makeSpace(int sr, int sc, int size) {// 영역의 좌상단r,c 영역 크기size
		int sum = 0;
		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				sum += spaces[r][c];

			}

		}
		if (sum == 0) {// 모두 하얀색인 공간(기저조건)
			sb.append(0);
		} else if (sum == size * size) {// 모두가 검은색인 공간(기저조건)
			sb.append(1);
		} else {// 나누는 경우
			int half = size / 2;
			sb.append("(");//나누면서 괄호 열기
			makeSpace(sr, sc, half);
			makeSpace(sr, sc + half, half);
			makeSpace(sr + half, sc, half);
			makeSpace(sr + half, sc + half, half);
			sb.append(")");//다 나누고 괄호 닫기
		}

	}

}
