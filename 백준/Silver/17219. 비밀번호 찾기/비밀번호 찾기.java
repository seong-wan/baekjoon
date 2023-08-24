import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static HashMap<String, String> hashmap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			String value = st.nextToken();
			hashmap.put(key, value);
		} // 사이트 주소와 비밀번호 입력
		for (int i = 0; i < M; i++) {
			sb.append(hashmap.get(br.readLine()) + "\n");
		}
		System.out.println(sb);
	}

}
