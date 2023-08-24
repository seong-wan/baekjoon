import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class c_room implements Comparable<c_room> {
		int start, end;

		public c_room(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(c_room o) {

			return this.end - o.end == 0 ? this.start - o.start : this.end - o.end;
		}// 끝 시간 기준으로 오름차순 정렬(같으면 시작 시간 기준)

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, cnt;// 회의의 수, 사용할 수 있는 회의의 개수
	static c_room[] rooms;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		rooms = new c_room[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			rooms[i] = new c_room(start, end);
		}
		Arrays.sort(rooms);// 회의 정보 입력받고 끝 시간 기준으로 정렬

		cnt = 1;
		int end = rooms[0].end;// 정렬된 배열의 1번 회의는 바로 시작
		for (int i = 1; i < N; i++) {
			if (rooms[i].start >= end) {
				cnt++;
				end = rooms[i].end;
				// 회의가 끝난 뒤에 최대한 빠르게 시작하는 회의를 시작
				// 끝 시간 기준으로 정렬했으므로 가장 빠르게 시작하면서 빠르게 끝나는 회의임
			}
		}
		System.out.println(cnt);

	}

}
