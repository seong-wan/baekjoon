public class Main {
	// 스트링 배열에 일단 넣기
	// 트라이로 문자열 관리
	// 문자열 끝에 스트링 배열 인덱스 값 기입 (1부터 넣어서 관리 0은 문자열 끝 아님)
	// 스트링 배열에 맞는 boolean 배열 만듬
	// 만들어지지 않았던 거면 점수처리 이미 만들어져있으면 continue
	// 점수 처리 - 점수 늘리기, 개수 늘리기, 출력해야 할 단어랑 비교 더 길거나 사전 순으로 앞서면 갱신
	static int w, b;
	static String[] words;
	static boolean[] check;
	static int[] scores = new int[] {0, 0, 0, 1, 1, 2, 3, 5, 11}; //문자 길이별 점수
	static char[][] board = new char[4][4];
	static boolean[][] visited = new boolean[4][4];
	static String outputWord;
	static int outputScore;
	static int outputCount;

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static StringBuilder sb = new StringBuilder();
	static Node trie = new Node();

	static class Node {
		Node[] child = new Node[26];
		int end = 0;//0이면 문자열 끝 아님, 끝일 경우 배열의 인덱스로 해당 문자열 구분
	}

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		w = in.nextInt();

		words = new String[w + 1];
		for (int i = 1; i <= w; i++) {
			words[i] = in.nextString();
		}
		
		initTrie();

		b = in.nextInt();
		for (int i = 0; i < b; i++) {
			outputWord = "";
			outputScore = 0;
			outputCount = 0;
			check = new boolean[w + 1];

			for (int j = 0; j < 4; j++) {
				String s = in.nextString();
				board[j] = s.toCharArray();
			}

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					//탐색 가능한 문자
					if (trie.child[board[j][k] - 'A'] != null) {
						visited[j][k] = true;
						dfs(j, k, trie.child[board[j][k] - 'A']);
						visited[j][k] = false;
					}
				}
			}

			sb.append(outputScore).append(" ").append(outputWord).append(" ").append(outputCount).append("\n");
		}

		System.out.print(sb);
	}

	static void initTrie() {
		for (int i = 1; i <= w; i++) {
			String s = words[i];
			Node temp = trie;

			for (int j = 0; j < s.length(); j++) {
				int idx = s.charAt(j) - 'A';

				if (temp.child[idx] == null) {
					temp.child[idx] = new Node();
				}

				temp = temp.child[idx];

				if (j == s.length() - 1) {
					temp.end = i; //문자열 끝에 해당하는 인덱스 저장
				}
			}
		}
	}

	static void dfs(int r, int c, Node node) {
		//문자열의 끝인 경우
		if (node.end != 0) {
			if (!check[node.end]) {
				check[node.end] = true;

				String word = words[node.end];
				outputScore += scores[word.length()];
				outputCount++;

				if (outputWord.length() < word.length()) {
					outputWord = word;
				} else if (outputWord.length() == word.length()) {
					if (outputWord.compareTo(word) > 0) {
						outputWord = word;
					}
				}
			}
		}

		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (canGo(nr, nc) && node.child[board[nr][nc] - 'A'] != null) {
				visited[nr][nc] = true;
				dfs(nr, nc, node.child[board[nr][nc] - 'A']);
				visited[nr][nc] = false;
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4 && !visited[r][c];
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		String nextString() throws Exception {
			StringBuilder sb = new StringBuilder();
			byte c;
			while ((c = read()) < 32) {
				if (size < 0)
					return "endLine";
			}
			do
				sb.appendCodePoint(c);
			while ((c = read()) > 32);
			return sb.toString();
		}

		char nextChar() throws Exception {
			byte c;
			while ((c = read()) < 32)
				;
			return (char)c;
		}

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}