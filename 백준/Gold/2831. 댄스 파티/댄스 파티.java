import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static int ans;
	static List<Integer> wantSmallMan = new ArrayList<>();
	static List<Integer> wantTallMan = new ArrayList<>();
	static List<Integer> wantSmallWoman = new ArrayList<>();
	static List<Integer> wantTallWoman = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			int temp = in.nextInt();

			if (temp < 0)
				wantSmallMan.add(-temp);
			else
				wantTallMan.add(temp);
		}

		for (int i = 0; i < N; i++) {
			int temp = in.nextInt();

			if (temp < 0)
				wantSmallWoman.add(-temp);
			else
				wantTallWoman.add(temp);
		}

		wantSmallMan.sort(Integer::compareTo);
		wantTallMan.sort(Integer::compareTo);
		wantSmallWoman.sort(Integer::compareTo);
		wantTallWoman.sort(Integer::compareTo);

		int manIdx = 0;
		int womanIdx = 0;
		while (manIdx < wantSmallMan.size() && womanIdx < wantTallWoman.size()) {
			if (wantSmallMan.get(manIdx) > wantTallWoman.get(womanIdx)) {
				ans++;
				manIdx++;
				womanIdx++;
			} else
				manIdx++;
		}

		manIdx = 0;
		womanIdx = 0;
		while (manIdx < wantTallMan.size() && womanIdx < wantSmallWoman.size()) {
			if (wantSmallWoman.get(womanIdx) > wantTallMan.get(manIdx)) {
				ans++;
				manIdx++;
				womanIdx++;
			} else
				womanIdx++;
		}

		System.out.print(ans);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

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