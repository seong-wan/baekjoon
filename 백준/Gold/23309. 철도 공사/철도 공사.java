import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, x, y, prev, next;
	static int[] input;
	static int[] prevlist = new int[1000001];
	static int[] nextlist = new int[1000001];
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		} // 공사 시작 전 역들의 정보 입력

		{
			prevlist[input[0]] = input[N - 1];
			nextlist[input[0]] = input[1];
			prevlist[input[N - 1]] = input[N - 2];
			nextlist[input[N - 1]] = input[0];

			for (int i = 1; i < N - 1; i++) {
				prevlist[input[i]] = input[i - 1];
				nextlist[input[i]] = input[i + 1];
			}
		} // 각 고유번호별 전의 역 값과 다음 역값 저장

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if (cmd.equals("BN")) {// BN의 경우
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				next = nextlist[x];
				sb.append(next + "\n");
				prevlist[y] = x;
				nextlist[y] = next;// 들어온 y 값의 전,후 값 처리

				nextlist[x] = y;// x의 다음 값 y로 처리
				prevlist[next] = y;// x의 원래 다음 값의 전 값을 y로 처리
			}

			else if (cmd.equals("BP")) {// BP의 경우
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				prev = prevlist[x];
				sb.append(prev + "\n");
				prevlist[y] = prev;
				nextlist[y] = x;// 들어온 y 값의 전,후 값 처리

				prevlist[x] = y;// x의 이전 값 y로 처리
				nextlist[prev] = y;// x의 원래 이전 값의 다음 값을 y로 처리
			}

			else if (cmd.equals("CN")) {// CN의 경우
				x = Integer.parseInt(st.nextToken());

				next = nextlist[x];
				sb.append(next + "\n");
				nextlist[x] = nextlist[next];// x의 다음 값을 삭제되는 값 대신 그 다음 값으로 변경
				prevlist[nextlist[next]] = x;// 삭제되는 값의 다음 값의 이전 값을 삭제되는 값 대신 x로 변경
			}

			else {// CP의 경우
				x = Integer.parseInt(st.nextToken());

				prev = prevlist[x];
				sb.append(prev + "\n");
				prevlist[x] = prevlist[prev];// x의 이전 값을 삭제되는 값 대신 그 이전 값으로 변경
				nextlist[prevlist[prev]] = x;// 삭제되는 값의 이전 값의 다음 값을 삭제되는 값 대신 x로 변경
			}
		}

		System.out.println(sb);
	}
}