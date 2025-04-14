public class Main {
	static class Node {
		char alphabet;
		Node pre;
		Node next;
	}

	static int N, R, Q;
	static StringBuilder sb = new StringBuilder();
	static Node headA, headB, tailA, tailB, grabA, grabB;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		R = in.nextInt();
		Q = in.nextInt();

		String one = in.nextString();
		String two = in.nextString();

		headA = new Node();
		headB = new Node();
		headA.alphabet = one.charAt(0);
		headB.alphabet = two.charAt(0);

		Node curA = headA;
		Node curB = headB;

		if (R == 1) {
			grabA = headA;
			grabB = headB;
		}

		for (int i = 1; i < N - 1; i++) {
			Node tempA = new Node();
			Node tempB = new Node();

			tempA.alphabet = one.charAt(i);
			tempB.alphabet = two.charAt(i);

			curA.next = tempA;
			curB.next = tempB;
			tempA.pre = curA;
			tempB.pre = curB;

			curA = tempA;
			curB = tempB;

			if (i == R - 1) {
				grabA = curA;
				grabB = curB;
			}
		}

		if (N == 1) {
			tailA = headA;
			tailB = headB;
		} else {
			tailA = new Node();
			tailB = new Node();
			tailA.alphabet = one.charAt(N - 1);
			tailB.alphabet = two.charAt(N - 1);
			curA.next = tailA;
			curB.next = tailB;
			tailA.pre = curA;
			tailB.pre = curB;

			if (R == N) {
				grabA = tailA;
				grabB = tailB;
			}
		}

		for (int i = 0; i < Q; i++) {
			char cmd = in.nextChar();

			if (cmd == 'I') {
				grabSizeUp();
				R++;
			} else if (cmd == 'D') {
				grabSizeDown();
				R--;
			} else if (cmd == 'S') {
				grabChange();
			} else if (cmd == 'L') {
				int idx = in.nextInt();

				toLeft(idx);
			} else {
				int idx = in.nextInt();

				toRight(idx);
			}
		}

		Node temp = headA;
		while (temp != null) {
			sb.append(temp.alphabet);
			temp = temp.next;
		}

		sb.append("\n");

		temp = headB;
		while (temp != null) {
			sb.append(temp.alphabet);
			temp = temp.next;
		}

		System.out.println(sb);
	}

	static void grabSizeUp() {
		grabA = grabA.next;
		grabB = grabB.next;
	}

	static void grabSizeDown() {
		grabA = grabA.pre;
		grabB = grabB.pre;
	}

	static void grabChange() {
		Node temp;

		if (R == N) {
			temp = tailA;
			tailA = tailB;
			tailB = temp;
		} else {
			Node grabANext = grabA.next;
			Node grabBNext = grabB.next;

			grabB.next = grabANext;
			grabA.next = grabBNext;
			grabANext.pre = grabB;
			grabBNext.pre = grabA;
		}

		temp = headA;
		headA = headB;
		headB = temp;

		temp = grabA;
		grabA = grabB;
		grabB = temp;
	}

	static void toLeft(int idx) {
		if (idx == 1) {
			tailA.next = headA;
			headA.pre = tailA;
			tailA = headA;
			headA = headA.next;

			headA.pre = null;
			tailA.next = null;

			if (R == 1)
				grabA = headA;
			else if (R == N)
				grabA = tailA;
			else
				grabA = grabA.next;
		} else {
			tailB.next = headB;
			headB.pre = tailB;
			tailB = headB;
			headB = headB.next;

			headB.pre = null;
			tailB.next = null;

			if (R == 1)
				grabB = headB;
			else if (R == N)
				grabB = tailB;
			else
				grabB = grabB.next;
		}
	}

	static void toRight(int idx) {
		if (idx == 1) {
			tailA.next = headA;
			headA.pre = tailA;
			headA = tailA;
			tailA = tailA.pre;

			headA.pre = null;
			tailA.next = null;

			if (R == 1)
				grabA = headA;
			else if (R == N)
				grabA = tailA;
			else
				grabA = grabA.pre;
		} else {
			tailB.next = headB;
			headB.pre = tailB;
			headB = tailB;
			tailB = tailB.pre;

			headB.pre = null;
			tailB.next = null;

			if (R == 1)
				grabB = headB;
			else if (R == N)
				grabB = tailB;
			else
				grabB = grabB.pre;
		}
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
			while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
			return sb.toString();
		}

		char nextChar() throws Exception {
			byte c;
			while ((c = read()) < 32)
				; // SPACE 분리라면 <=로, SPACE 무시라면 <로
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